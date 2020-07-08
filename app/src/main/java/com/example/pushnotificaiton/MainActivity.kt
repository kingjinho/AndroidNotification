package com.example.pushnotificaiton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pushnotificaiton.utils.push.PushNotification
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //create builder
        //priority API < 26
        //create channel and set channels importance if API >= 26
        //add tab action with pendingIntent
        //show notification

        btn_display_basic_notification.setOnClickListener {
            PushNotification.from(this)
                .createNotificationBuilder("HelloWorld")
                .setTitle(R.string.app_name)
                .setContent(R.string.app_name)
                .setSmallIcon(R.drawable.ic_baseline_add_circle_24)
                .createChannel()
                .notifyUser()
        }

        btn_display_bigPicture_notification.setOnClickListener {
            PushNotification.from(this)
                .createNotificationBuilder("HelloWorld")
                .setTitle(R.string.app_name)
                .setExpandable(true, R.drawable.ic_launcher_playstore)
                .setLargeIcon(R.drawable.ic_launcher_playstore)
                .setSmallIcon(R.drawable.ic_baseline_add_circle_24)
                .createChannel("name", "description", PushNotification.IMPORTANCE.DEFAULT, PushNotification.IMPORTANCE.DEFAULT)
                .notifyUser()
        }

        btn_display_notification_with_action.setOnClickListener {
            PushNotification.from(this)
                .createNotificationBuilder("HelloWorld")
                .setTitle(R.string.app_name)
                .setContent(R.string.common_random)
                .setSmallIcon(R.drawable.ic_baseline_add_circle_24)
                .createChannel()
                .setActionButton(getString(R.string.app_name))
                .notifyUser()
        }

        btn_display_notification_with_picture_and_action.setOnClickListener {
            PushNotification.from(this)
                .createNotificationBuilder("HelloWorld")
                .setTitle(R.string.app_name)
                .setSmallIcon(R.drawable.ic_baseline_add_circle_24)
                .createChannel()
                .notifyUser()
        }
    }
}