package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.Matchers.`is`
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest{
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero(){
        // Create an active task
        val tasks = listOf<Task>(
                Task("title", "desc", isCompleted = false)
        )
        // Call your function
        val result = getActiveAndCompletedStats(tasks)
        // Check the result with JUnit
        //assertEquals(result.completedTasksPercent, 0f)
        //assertEquals(result.activeTasksPercent, 100f)

        //Hamcrest
        assertThat(result.activeTasksPercent,`is`(100f))
        assertThat(result.completedTasksPercent, `is`(0f))

    }
}