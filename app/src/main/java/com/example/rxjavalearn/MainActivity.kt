package com.example.rxjavalearn

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.reactivex.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

import io.reactivex.schedulers.Schedulers
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val observable = Observable.just(1, 2, 3, 4)
//        val dispose: Disposable = observable
//            .buffer(4)
//
//            .subscribe({ println("next1111111111111111") }, {}, {})

//            _____________
        //Completable
        //Single
        //Maybe
        //Observable
        //Flowable
//______________________________________________
        val dispose = dataSource()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e(TAG,"next int $it")
            }, {
                Toast.makeText(applicationContext, "it ${it.localizedMessage}", Toast.LENGTH_LONG).show()

            }, {

            })
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}

//fun dataSource(): Observable<Int> {
//    return Observable.create { subscriber ->
//
//        for (i in 0..100) {
//            Thread.sleep(1000)
//            subscriber.onNext(i)
//        }
//    }
//}
fun dataSource(): Flowable<Int> {
    return Flowable.create({ subscriber ->

        for (i in 0..10000000) {

//            Thread.sleep(10000000)

            subscriber.onNext(i)
        }
        subscriber.onComplete()
    },BackpressureStrategy.LATEST)
}

