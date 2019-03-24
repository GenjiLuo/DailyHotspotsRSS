package org.gallon.rss.scheduler

import org.gallon.rss.controller.RefreshAction
import org.gallon.rss.entity.mongo.RSS
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component
class CommonScheduler(val mongoTemplate: MongoTemplate, val refreshAction: RefreshAction) {

    @Scheduled(cron = "0 0 0 * * *")
    @Async
    fun runTask() {
        println("runTask: " + Thread.currentThread())
        val date = SimpleDateFormat("yyyyMMdd").format(Date())
        val baiduTopCount = mongoTemplate.count(Query.query(Criteria.where("usage").`is`(RSS.USAGE_DAILY)
                .and("create_date").`is`(date).and("src").`is`(RSS.SRC_BAIDUTOP)), RSS::class.java)
        if (baiduTopCount == 0L) {
            println("refresh baidutop")
//            refreshAction.baidutop()
        }

        val xinlangCount = mongoTemplate.count(Query.query(Criteria.where("usage").`is`(RSS.USAGE_DAILY)
                .and("create_date").`is`(date).and("src").`is`(RSS.SRC_XINLANG)), RSS::class.java)
        if (xinlangCount == 0L) {
            println("refresh xinlang")
//            refreshAction.xinlang()
        }

        mongoTemplate.find(Query.query(Criteria.where("usage").`is`(RSS.USAGE_TEST)), RSS::class.java).forEach {
            mongoTemplate.remove(it)
        }
    }

}