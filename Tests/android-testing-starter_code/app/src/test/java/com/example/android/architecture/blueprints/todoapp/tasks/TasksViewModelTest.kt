package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TasksViewModelTest{
    @Test
    fun addNewTask_setsNewTaskEvent() {

        // Given a fresh TasksViewModel

        //The AndroidX Test libraries include classes and methods that provide you with versions of components like Applications and Activities that are meant for tests.
        //When you have a local test where you need simulated Android framework classes (such as an Application Context), follow these steps to properly set up AndroidX Test:
        //
        //1. Add the AndroidX Test core and ext dependencies
        //2. Add the Robolectric Testing library dependency
        //3. Annotate the class with the AndroidJunit4 test runner
        //4. Write AndroidX Test code

        val tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())

        // When adding a new task


        // Then the new task event is triggered

    }
}