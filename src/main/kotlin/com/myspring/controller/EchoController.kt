package com.myspring.controller

import com.myspring.common.Echo
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/echo")
class EchoController(){

    @GetMapping("{arg}")
    fun echoOnce(@PathVariable arg: String) {
        println(arg)
        //return Json.encodeToString(Echo(arg, 1))
    }

    @PostMapping
    fun post(@RequestBody echo: Echo) {
        for(i in 1..echo.times) {
            println(echo.text)
            //delay(1000)
        }
    }
}