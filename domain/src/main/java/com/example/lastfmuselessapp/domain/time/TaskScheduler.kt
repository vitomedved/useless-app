package com.example.lastfmuselessapp.domain.time

import java.util.concurrent.TimeUnit

/**
 * Scheduler class that can execute and cancel tasks. The tasks can be delayed, or repeatable.
 * */
interface TaskScheduler {

    /**
     * Schedules given task for execution in future. If job with the same tag is already active, it will be canceled.
     *
     * @param tag tag of the given [task]. The tag should be unique identifier of the task.
     * @param delay amount representing how long should given [task] be delayed.
     * @param timeUnit time unit of the [delay].
     * @param task task that should be executed.
     * */
    fun executeDelayed(
        tag: String,
        delay: Long,
        timeUnit: TimeUnit = TimeUnit.MILLISECONDS,
        task: () -> Unit
    )

    /**
     * Cancels currently running task by its tag name (if the task exists).
     *
     * @param tag tag of the task that should be canceled.
     * @param interruptIfRunning boolean value representing whether the task can be killed if its already running.

     * @return true if the task is successfully canceled, false if the task did not exist.
     * */
    fun cancel(tag: String, interruptIfRunning: Boolean = true): Boolean

    /**
     * Cancels all scheduled tasks.
     *
     * @param interruptIfRunning boolean value representing whether the task can be killed if its already running.
     * */
    fun cancelAll(interruptIfRunning: Boolean = true)
}
