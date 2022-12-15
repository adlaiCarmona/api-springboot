package com.myspring.controller

import com.myspring.common.Reminder
import com.myspring.service.ReminderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/reminder")
class ReminderController(val service: ReminderService){

    @GetMapping
    fun index(): Iterable<Reminder> = service.findAll()

    @PostMapping
    fun post(@RequestBody reminder: Reminder) = service.post(reminder)

    @GetMapping("/{id}")
    fun find(@PathVariable id: Int) =
        service.findById(id)

    @GetMapping("/start/{id}")
    fun init(@PathVariable id: Int) =
        service.initReminderById(id)

}