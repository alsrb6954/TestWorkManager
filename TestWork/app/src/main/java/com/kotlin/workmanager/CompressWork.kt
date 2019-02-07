package com.kotlin.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class CompressWork(context: Context, workerParameters: WorkerParameters):Worker(context,workerParameters) {
    override fun doWork(): Result {
        val fileName = inputData.getString("file_name")
        Log.d("hoho", "한다 $fileName")

        val outputData = Data.Builder().putAll(mapOf("compress_file_name" to "compress.jpg")).build()

        return Result.success(outputData)
    }
}