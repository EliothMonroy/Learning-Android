package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.Matchers.`is`
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest{
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero(){
        // Create an active task
        val tasks = listOf(
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
    @Test
    fun getActiveAndCompletedStats_completed_returnsZeroHundred(){
        // Create an active task
        val tasks = listOf(
                Task("title", "desc", isCompleted = true)
        )
        // Call your function
        val result = getActiveAndCompletedStats(tasks)
        // Check the result with JUnit
        //assertEquals(result.completedTasksPercent, 0f)
        //assertEquals(result.activeTasksPercent, 100f)

        //Hamcrest
        assertThat(result.activeTasksPercent,`is`(0f))
        assertThat(result.completedTasksPercent, `is`(100f))
    }

    @Test
    fun getActiveAndCompletedStats_mixedCompleted_returnsSixtyForty(){
        // Create an active task
        val tasks = listOf(
                Task("title", "desc", isCompleted = true),
                Task("title", "desc", isCompleted = true),
                Task("title", "desc", isCompleted = false),
                Task("title", "desc", isCompleted = false),
                Task("title", "desc", isCompleted = false)
        )
        // Call your function
        val result = getActiveAndCompletedStats(tasks)
        // Check the result with JUnit
        //assertEquals(result.completedTasksPercent, 0f)
        //assertEquals(result.activeTasksPercent, 100f)

        //Hamcrest
        assertThat(result.activeTasksPercent,`is`(60f))
        assertThat(result.completedTasksPercent, `is`(40f))
    }

    @Test
    fun getActiveAndCompletedStats_emptyList_returnsZeros(){
        // Create an active task
        val tasks = emptyList<Task>()
        // Call your function
        val result = getActiveAndCompletedStats(tasks)
        // Check the result with JUnit
        //assertEquals(result.completedTasksPercent, 0f)
        //assertEquals(result.activeTasksPercent, 100f)

        //Hamcrest
        assertThat(result.activeTasksPercent,`is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_nullList_returnsZeros(){
        // Create an active task
        val tasks : List<Task>? = null
        // Call your function
        val result = getActiveAndCompletedStats(tasks)
        // Check the result with JUnit
        //assertEquals(result.completedTasksPercent, 0f)
        //assertEquals(result.activeTasksPercent, 100f)

        //Hamcrest
        assertThat(result.activeTasksPercent,`is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

}