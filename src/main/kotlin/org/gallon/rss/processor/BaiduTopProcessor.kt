package org.gallon.rss.processor

import org.gallon.rss.downloader.HttpClientDownloader
import org.gallon.rss.entity.BaiduTop
import us.codecraft.webmagic.Page
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.Spider
import us.codecraft.webmagic.pipeline.FilePipeline
import us.codecraft.webmagic.pipeline.JsonFilePipeline
import us.codecraft.webmagic.processor.PageProcessor
import java.util.regex.Matcher
import java.util.regex.Pattern

class BaiduTopProcessor: PageProcessor {

    private val site = Site.me().setRetryTimes(3).setSleepTime(100)

    override fun getSite(): Site {
        return site
    }

    override fun process(page: Page) {
        val list_table = page.html.css("table.list-table")
//        println("table.list-table:\n" + list_table)
        val result = ArrayList<BaiduTop>()
        // &amp; -> &
        val compile = Pattern.compile(".*?(top|normal)\">(.*?)<.*?" +
                "href=\"(.*?)\".*?" +
                ">(.*?)<.*?" +
                "(rise|fall)\">(.*?)<.*?", Pattern.DOTALL)
        val matcher = compile.matcher(list_table.toString())
        matcher.run {
            while (find()) {
                val top = BaiduTop(num = group(2).toInt(),
                        keywords = group(4),
                        url = group(3).replace("amp;", ""),
                        rise = group(6).toInt())
                result.add(top)
            }
        }
        page.putField("result", result)
        println("result:\n" + result.joinToString(", "))
    }

}

fun main(args: Array<String>) {
    Spider.create(BaiduTopProcessor())
            .setDownloader(HttpClientDownloader())
            .addUrl("https://top.baidu.com/buzz?b=341")
            .addUrl("https://top.baidu.com/buzz?b=1")
            .addPipeline(JsonFilePipeline("./output"))
            .thread(1)
            .run()
}