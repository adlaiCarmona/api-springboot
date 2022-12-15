package com.myspring.repository

import com.myspring.common.Reminder
import org.springframework.data.repository.CrudRepository

interface ReminderRepository : CrudRepository<Reminder, Int> {
}