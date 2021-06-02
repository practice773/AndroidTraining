package com.example.exampleworkmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var btnStartManager: Button
    lateinit var mWorkManager: WorkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStartManager = findViewById(R.id.start_work_manager)
        mWorkManager = WorkManager.getInstance(applicationContext)

        btnStartManager.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                startPeriodicWork()
            }
        })

    }

    private fun startPeriodicWork(){

        var periodicRequest = PeriodicWorkRequest.Builder(CustomPeriodicRequestClass::class.java,
        15, TimeUnit.MINUTES).build()

        mWorkManager.enqueue(periodicRequest)

        mWorkManager.getWorkInfoByIdLiveData(periodicRequest.id)
            .observe(this, Observer {
                    Log.v("NIHAR LOG : ", "INSIDE OBSERVER FOR LIVE DATA")
                    var data = it.outputData
                    var message = data.getString("notification")
                    Log.v("NIHAR LOG : ", "RECEIVED DATA IS : " + message)
                    Toast.makeText(applicationContext,
                    "Current Time = " + message ,
                    Toast.LENGTH_LONG).show()
            })
    }
}