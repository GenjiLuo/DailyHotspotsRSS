package org.gallon.rss.controller

import com.google.gson.Gson
import org.gallon.rss.entity.gson.FastNews
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate


@RestController
class RefreshAction(val restTemplate: RestTemplate) {

    @GetMapping("refreshBaiduTop")
    fun refreshBaiduTop(): String {
        val body = restTemplate.getForEntity("http://api.jisuapi.com/news/get?channel=头条&start=0&num=10&appkey=c46797350d31f9c0", String::class.java).body
        if (body != null) {
            val fastNews = Gson().fromJson(body, FastNews::class.java)
            println("body: " + body)
            println("news: " + fastNews)
            println("news: " + fastNews.result)
            println("news: " + fastNews.result.channel)
        }
        return "refreshBaiduTop ok"
    }

}