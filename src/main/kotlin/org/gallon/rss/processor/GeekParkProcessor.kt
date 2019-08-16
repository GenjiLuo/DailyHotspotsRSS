package org.gallon.rss.processor

import org.gallon.rss.downloader.HttpClientDownloader
import org.gallon.rss.entity.BaiduTop
import org.gallon.rss.entity.mongo.RSS
import org.gallon.rss.pipeline.BaiduTopPipeline
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
import us.codecraft.webmagic.pipeline.ConsolePipeline
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter


class GeekParkProcessor(val usage: Int?): PageProcessor {

    private val result = ArrayList<BaiduTop>()
    private val list = ArrayList<String>()
    private var count = 0

    private val site = Site.me().setRetryTimes(3).setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36")

    override fun getSite(): Site {
        return site
    }

    override fun process(page: Page) {
        println("html: " + page.html)
        val compile = Pattern.compile(".*?<h3 class=\"c-title\"> <a href=\"(.*?)\".*?" +
                "\"_blank\">(.*?)</a>", Pattern.DOTALL)
        val matcher = compile.matcher(page.html.toString())
        matcher.run {
            while (find()) {
                println("detail url:" + group(2).replace("<em>", "").replace("</em>", ""))
                list.add(group(2).replace(Regex(Const.REGEX_IGNORE_2), ""))
//                val driver = PhantomJSDriver()
////                val driver = ChromeDriver()
//                driver.get(group(1))
//
//                try {
//                    Thread.sleep(1000)
//                } catch (e: Throwable) {
//                    e.printStackTrace()
//                }
//                println("hehe: " + driver[group(1)])
                break
            }
        }
        synchronized(this) {
            if (++count == 20) {
                result.forEachIndexed { index, baiduTop ->
                    baiduTop.keywords = list[index].trim()
                    println(baiduTop)
                }
                list.forEachIndexed { index, s ->
                    println((index + 1).toString() + "、" + s)
                }
                page.putField("result", result)
                page.putField("usage", usage?:RSS.USAGE_TEST)
            }
        }
    }

}
