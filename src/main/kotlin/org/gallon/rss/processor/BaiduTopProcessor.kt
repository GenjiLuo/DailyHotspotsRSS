package org.gallon.rss.processor

import org.gallon.rss.downloader.HttpClientDownloader
import org.gallon.rss.entity.BaiduTop
import org.gallon.rss.util.Const
import us.codecraft.webmagic.Page
import us.codecraft.webmagic.Request
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.Spider
import us.codecraft.webmagic.pipeline.JsonFilePipeline
import us.codecraft.webmagic.processor.PageProcessor
import us.codecraft.webmagic.selector.Selectable
import java.util.ArrayList
import java.util.regex.Pattern
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter


class BaiduTopProcessor: PageProcessor {

    private val site = Site.me().setRetryTimes(3).setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36")

    override fun getSite(): Site {
        return site
    }

    override fun process(page: Page) {
        val list_table = page.html.css("table.list-table")
        if (list_table.toString() != null) {
            topBaidu(page)
        } else {
            baiduDetail(page)
        }
    }

    private val list = ArrayList<String>()

    private fun baiduDetail(page: Page) {
//        println("html: " + page.html)
        val compile = Pattern.compile(".*?<h3 class=\"c-title\"> <a href=\"(.*?)\".*?" +
                "\"_blank\">(.*?)</a>", Pattern.DOTALL)
        val matcher = compile.matcher(page.html.toString())
        matcher.run {
            while (find()) {
                println("detail url:" + group(1))
                println("detail url:" + group(2).replace("<em>", "").replace("</em>", ""))
                list.add(group(2).replace(Regex(Const.REGEX_IGNORE_2), "").replace(Regex(Const.REGEX_IGNORE_3), ""))
                val driver = PhantomJSDriver()
//                val driver = ChromeDriver()
                driver.get(group(1))

                try {
                    Thread.sleep(1000)
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
                println("hehe: " + driver[group(1)])
                break
            }
        }
        list.forEachIndexed { index, s ->
            println((index + 1).toString() + "、" + s)
        }
    }

    private fun topBaidu(page: Page) {
        val list_table = page.html.css("table.list-table")
//        println("table.list-table:\n" + list_table)
        val result = ArrayList<BaiduTop>()
        // &amp; -> &
        val compile = Pattern.compile(".*?(top|normal)\">(.*?)<.*?" +
                "href=\"(.*?)\".*?" +
                ">(.*?)<.*?" +
                "class=\"tc\".*?href=\"(.*?)\".*?" +
                "(rise|fall)\">(.*?)<.*?", Pattern.DOTALL)
        val matcher = compile.matcher(list_table.toString())
        var count = 0
        matcher.run {
            while (find()) {
                val top = BaiduTop(num = group(2).toInt(),
                        keywords = group(4),
                        url = group(5).replace(Const.REGEX_IGNORE_1, ""),
                        rise = group(7).toInt())
                result.add(top)
                println(top.num.toString() + "、" + top.keywords)
                page.addTargetRequest(top.url)
                if (++count == 10) break
//                break
            }
        }
        page.putField("result", result)
        println("result:\n" + result.joinToString(", "))
    }

}

fun main(args: Array<String>) {
    Spider.create(BaiduTopProcessor())
            .setDownloader(HttpClientDownloader())
            .addUrl("https://top.baidu.com/buzz?b=341") //今日热点
//            .addUrl("https://top.baidu.com/buzz?b=1") //实时热点
            .addPipeline(JsonFilePipeline("./output"))
            .thread(1)
            .run()
}