package org.gallon.rss

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Component

@SpringBootApplication
class SpiderApplication

fun main(args: Array<String>) {
	runApplication<SpiderApplication>(*args)
}
