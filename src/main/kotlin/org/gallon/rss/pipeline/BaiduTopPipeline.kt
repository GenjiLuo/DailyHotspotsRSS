package org.gallon.rss.pipeline

import org.gallon.rss.entity.BaiduTop
import org.gallon.rss.entity.mongo.Article
import org.gallon.rss.entity.mongo.RSS
import org.gallon.rss.util.Const
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component
import us.codecraft.webmagic.ResultItems
import us.codecraft.webmagic.Task
import us.codecraft.webmagic.pipeline.Pipeline

@Component
class BaiduTopPipeline: Pipeline {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    override fun process(resultItems: ResultItems, task: Task) {
        val list = resultItems.get<List<BaiduTop>>("result") ?: return
//        println("resultItems.result: " + resultItems.get("result"))
        val usage = resultItems.get<Int>("usage") ?: RSS.USAGE_TEST
        val rss = RSS()
        rss.src = RSS.SRC_BAIDUTOP
        rss.usage = usage
        var count = 0
        list.forEach {
            val article = Article()
            article.title = it.keywords.replace(Regex(Const.REGEX_IGNORE_2), "")
            article.rise = it.rise
            article.url = it.url
            rss.list.add(article)
            count++
            println(count.toString() + "、" + article.title)
        }
        mongoTemplate.save(rss)
    }

}