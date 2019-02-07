package com.kotlin.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadWork(context: Context, parameters: WorkerParameters):Worker(context,parameters) {
    override fun doWork(): Result {
        val fileName = inputData.getString("compress_file_name")
        Log.d("hoho", "upload $fileName")
        return Result.success()
    }
}