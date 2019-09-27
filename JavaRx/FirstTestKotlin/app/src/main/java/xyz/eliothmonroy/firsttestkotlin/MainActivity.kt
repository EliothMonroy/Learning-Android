package xyz.eliothmonroy.firsttestkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Observable.just("Hello reactive world").subscribe {
            value-> Log.d("TAG",value)
        }
        Observable.just("Apple","Orange","Banana").subscribe(
            {value -> Log.d("TAG","Received: $value")}, //onNext
            { error->Log.d("TAG","Error: $error")}, //onError
            {Log.d("TAG","Completed!")} //onComplete
        )
    }
}
