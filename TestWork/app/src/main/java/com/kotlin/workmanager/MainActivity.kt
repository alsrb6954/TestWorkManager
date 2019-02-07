package com.kotlin.workmanager

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()

        val inputData = Data.Builder().putAll(mapOf("file_name" to "sdcard/user_choice_picture.jpg")).build()
        val compressWork = OneTimeWorkRequestBuilder<CompressWork>()
            .setInputData(inputData)
            .build()
        val uploadWork = OneTimeWorkRequestBuilder<UploadWork>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().let {
            it.beginWith(compressWork).then(uploadWork).enqueue()
//            it.enqueue(compressWork)

            it.getWorkInfoByIdLiveData(uploadWork.id)
                .observe(this, Observer {workInfo ->
                if(workInfo != null && workInfo.state.isFinished){
                    Log.d("hoho", "끝")
                    Log.d("hoho", "$workInfo")
                    Log.d("hoho", "${workInfo.outputData}")
                }
            })
        }
    }
}
