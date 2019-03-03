package org.gallon.rss.scheduler

import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
open class BaiduTopScheduler {

    @Scheduled(cron = "0 0 0 * * *")
    @Async
    open fun runTask() {
        println("runTask: " + Thread.currentThread())
    }

}