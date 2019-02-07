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

        val work = OneTimeWorkRequestBuilder<CompressWork>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().let {
//            it.beginWith(work).then(work).enqueue()
            it.enqueue(work)

            it.getWorkInfoByIdLiveData(work.id)
                .observe(this, Observer {workInfo ->
                if(workInfo != null && workInfo.state.isFinished){
                    Log.d("hoho", "끝")
                    Log.d("hoho", "$workInfo")
                }
            })
        }
    }
}
