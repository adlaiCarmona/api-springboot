package com.myspring

import com.fasterxml.jackson.databind.util.JSONPObject
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@SpringBootApplication
class MySpringApplication

fun main(args: Array<String>) {
	runApplication<MySpringApplication>(*args)
}

// Message Service

@RestController
@RequestMapping("/api/message")
class MessageController(val service: MessageService){
	@GetMapping
	fun index(): List<Message> = service.findMessages()

	@PostMapping
	fun post(@RequestBody message: Message){
		service.post(message)
	}
}

@Service
class MessageService(val db: MessageRepository) {
	fun findMessages(): List<Message> = db.findMessages()

	fun post(message: Message){
		db.save(message)
	}
}

interface MessageRepository : CrudRepository<Message, String> {
	@Query("select m from Message m")
	fun findMessages(): List<Message>
}

// My Reminder Service

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

interface ReminderRepository : CrudRepository<Reminder, Int> {
}

// My Echo Service

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

@Serializable
data class Echo(
	val text: String,
	val times: Int
)
