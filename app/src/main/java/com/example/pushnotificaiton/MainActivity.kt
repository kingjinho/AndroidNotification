package com.example.pushnotificaiton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pushnotificaiton.utils.push.PushNotification

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create builder and priority
        //create channel and set importance if API >= 26
        //add tab action with pendingIntent
        //show notification
        PushNotification.from(this)
            .createNotificationBuilder("HelloWorld")
            .setTitle(R.string.app_name)
            .setExpandable(true)
            .setLargeIcon(R.drawable.ic_baseline_add_circle_24)
            .setSmallIcon(R.drawable.ic_baseline_add_circle_24)
            .createChannel()
            .notifyUser()


    }
}