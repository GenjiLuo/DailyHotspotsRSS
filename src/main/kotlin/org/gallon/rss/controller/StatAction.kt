package org.gallon.rss.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StatAction {

    @GetMapping("status")
    fun test(): String {
        return "status: online"
    }

}