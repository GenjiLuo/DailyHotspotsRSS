package org.gallon.rss.controller

import com.google.gson.Gson
import org.gallon.rss.downloader.HttpClientDownloader
import org.gallon.rss.entity.common.JsonResult
import org.gallon.rss.entity.gson.FastNews
import org.gallon.rss.entity.mongo.Article
import org.gallon.rss.entity.mongo.RSS
import org.gallon.rss.pipeline.BaiduTopPipeline
import org.gallon.rss.processor.BaiduTopProcessor
import org.gallon.rss.util.Const
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import us.codecraft.webmagic.Spider
import java.util.*


@RestController()
@RequestMapping("refresh")
class RefreshAction(val restTemplate: RestTemplate, val mongoTemplate: MongoTemplate,
                    val baiduTopPipeline: BaiduTopPipeline) {

    companion object {
        var baidutop: String = ""
    }

    @GetMapping("baidutop")
    fun baidutop(usage: Int?): String {
        baidutop = ""
        Spider.create(BaiduTopProcessor(usage))
                .setDownloader(HttpClientDownloader())
//                .addUrl("https://top.baidu.com/buzz?b=341") //今日热点
                .addUrl("https://top.baidu.com/buzz?b=1") //实时热点
                .addPipeline(baiduTopPipeline)
                .thread(1)
                .run()
        return baidutop
    }

    @GetMapping("jisu")
    fun xinlang(usage: Int?): String {
        val body = restTemplate.getForEntity("https://api.jisuapi.com/news/get?channel=头条&start=0&num=10&appkey=c46797350d31f9c0", String::class.java).body ?: return "failed"
        val fastNews = Gson().fromJson(body, FastNews::class.java)
        var count = 0
        val rss = RSS()
        rss.src = RSS.SRC_XINLANG
        rss.usage = if (usage != null) usage else RSS.USAGE_TEST
        val sb = StringBuilder()
        fastNews!!.result.list.forEach {
            val article = Article()
            article.title = it.title.replace(Regex(Const.REGEX_IGNORE_2), "")
            article.news_time = it.time
            article.src = it.src
            article.category = it.category
            article.pic = it.pic
            article.html = it.content
            article.url = it.url
            article.weburl = it.weburl
            rss.list.add(article)
            count++
            println(count.toString() + "、" + article.title)
            sb.append(count).append("、").append(article.title).append("\n")
        }
        mongoTemplate.save(rss)
        return sb.toString()
    }

}