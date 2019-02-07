package com.kotlin.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class CompressWork(context: Context, workerParameters: WorkerParameters):Worker(context,workerParameters) {
    override fun doWork(): Result {

        Log.d("hoho", "한다")
        return Result.success()
    }
}