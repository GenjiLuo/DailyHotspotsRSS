package org.gallon.spider1.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class TestAction() {

    @GetMapping("test")
    fun test(): String {
        println("test")
        return "test ok"
    }

}