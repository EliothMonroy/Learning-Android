package xyz.eliothmonroy.realmfirsttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import io.realm.RealmResults
import xyz.eliothmonroy.realmfirsttest.data.Dog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dog=Dog("Firulais",6)
        Realm.init(this)
        val realm=Realm.getDefaultInstance()
        // Query Realm for all dogs younger than 2 years old
        var puppies:RealmResults<Dog> = realm.where(Dog::class.java).lessThan("age",2).findAll()
        Log.d("MainActivity","${puppies.size}") // => 0 because no dogs have been added to the Realm yet
        realm.beginTransaction()


    }
}
