package com.example.lastfmuselessapp.time

import com.example.lastfmuselessapp.domain.time.TaskScheduler
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class TaskSchedulerImpl : TaskScheduler {

    // TODO Refactor 4 to something more dynamic
    private val executor = Executors.newScheduledThreadPool(4)

    private val taskMap: HashMap<String, ScheduledFuture<*>> = hashMapOf()

    override fun executeDelayed(tag: String, delay: Long, timeUnit: TimeUnit, task: () -> Unit) {
        cancel(tag)

        taskMap[tag] = executor.schedule(task, delay, timeUnit)
    }

    override fun cancel(tag: String, interruptIfRunning: Boolean): Boolean {
        return taskMap[tag]?.cancel(interruptIfRunning) ?: false
    }

    override fun cancelAll(interruptIfRunning: Boolean) {
        taskMap.forEach { task ->
            task.value.cancel(interruptIfRunning)
        }
    }
}
