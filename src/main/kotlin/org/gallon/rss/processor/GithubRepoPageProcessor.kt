package org.gallon.rss.processor

import org.gallon.rss.downloader.HttpClientDownloader
import us.codecraft.webmagic.Page
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.Spider
import us.codecraft.webmagic.processor.PageProcessor

class GithubRepoPageProcessor: PageProcessor {

    private val site = Site.me().setRetryTimes(3).setSleepTime(100)

    override fun getSite(): Site {
        return site
    }

    override fun process(page: Page) {
        page.addTargetRequests(page.html.links().regex("(https://github\\.com/\\w+/\\w+)").all())
        page.putField("author", page.url.regex("https://github\\.com/(\\w+)/.*").toString())
        page.putField("name", page.html.xpath("//h1[@class='entry-title public']/strong/a/text()").toString())
        if (page.resultItems.get<Any>("name") == null) {
            //skip this page
            page.setSkip(true)
        }
        page.putField("readme", page.html.xpath("//div[@id='readme']/tidyText()"))
    }



}