package org.gallon.rss.scheduler

import org.gallon.rss.entity.mongo.RSS
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class BaiduTopScheduler(val mongoTemplate: MongoTemplate) {

    @Scheduled(cron = "0/15 * * * * *")
    @Async
    fun runTask() {
        println("runTask: " + Thread.currentThread())
    }

}