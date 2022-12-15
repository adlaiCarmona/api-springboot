package com.myspring.service

import com.myspring.common.Reminder
import com.myspring.repository.ReminderRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class ReminderService(val db: ReminderRepository) {

    fun findAll(): Iterable<Reminder> = db.findAll()

    fun findById(id: Int) = db.findById(id)

    fun post(reminder: Reminder) = db.save(reminder)

    fun initReminderById(id: Int){
        try{
            val reminder = db.findById(id).get()

            runBlocking {
                println("Reminder #${reminder.id} is due in ${reminder.time}...")
                delay(reminder.time)
                println("Reminder #${reminder.id}!!!")
            }
        } catch (e: IllegalArgumentException){
            println("Reminder with id: $id doesn't exist")
        }
    }
}