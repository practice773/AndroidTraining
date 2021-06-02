package com.example.exampleworkmanager

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class CustomPeriodicRequestClass(context: Context, parameters: WorkerParameters) : Worker(context, parameters){

    override fun doWork(): Result {

        try {

            val time = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = time.format(Date())

            var data : Data =
                Data.Builder().putString("notification", currentDate).build()


            Log.v("NIHAR LOG : ", "CHECKING DATA OBJECT AFTER BUILDING IT " + data.getString("notification"))

            return Result.success(data)

        }catch (exception: Exception){

            return Result.failure()

        }

    }

}