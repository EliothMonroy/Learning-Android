package xyz.eliothmonroy.firsttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    //Implementing CoroutineScope we can define an scope of just this activitu
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main+job
    private lateinit var job:Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        job=Job() //create the job

        //Calling coroutines
        GlobalScope.launch(Dispatchers.Main){
            fetchAndShowUser()
        }
        GlobalScope.launch(Dispatchers.IO){
            fetchUserAndSaveInDatabase()
        }
        GlobalScope.launch(Dispatchers.Main){
            val userOne=async(Dispatchers.IO){fetchFirstUser()}
            val userTwo=async(Dispatchers.IO){fetchSeconeUser()}
            showUsers(userOne.await(),userTwo.await())
        }
        //Now we can use launch like this, scope is just the activity
        launch {
            val userOne=async(Dispatchers.IO){fetchFirstUser()}
            val userTwo=async(Dispatchers.IO){fetchSeconeUser()}
            showUsers(userOne.await(),userTwo.await())
        }

    }

    suspend fun fetchUser():User{
        return GlobalScope.async(Dispatchers.IO){
            //We go for the user in a db
            User("Elioth")
        }.await()
    }

    //We can also use withContext, which is the same as async
    suspend fun fetchUser2(): User {
        return withContext(Dispatchers.IO) {
            // make network call
            // return user
            User("Elioth 4")
        }
    }

    suspend fun fetchAndShowUser(){
        val user=fetchUser() // fetch on IO thread
        showUser(user)
    }

    fun showUser(user: User) {
        //Show user
        Log.d("MainActivity", "User name: "+user.name)
    }

    fun fetchUserAndSaveInDatabase(){
        //fetch user from network
        //save user in db
        //do not return anything
        Log.d("MainActivity", "fetchUserAndSaveInDatabase")
    }

    //We don't need to make the functions suspend as we are not calling any other coroutine
    fun fetchFirstUser(): User {
        // make network call
        // return user
        return User("Elioth 2")
    }

    fun fetchSeconeUser(): User {
        // make network call
        // return user
        return User("Elioth 3")
    }

    fun showUsers(user1: User, user2:User) {
        //Show user
        Log.d("MainActivity", "User name: "+user1.name)
    }

}
