package com.example.pushnotificaiton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pushnotificaiton.utils.push.NotificationBuilder
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
            NotificationBuilder.from(this)
                .createNotificationBuilder("HelloWorld")
                .setTitle(R.string.app_name)
                .setContent(R.string.app_name)
                .setSmallIcon(R.drawable.ic_baseline_add_circle_24)
                .createChannel("name", "description", NotificationBuilder.IMPORTANCE.DEFAULT, NotificationBuilder.IMPORTANCE.DEFAULT)
                .notifyUser(1)
        }

        btn_display_bigPicture_notification.setOnClickListener {
            NotificationBuilder.from(this)
                .createNotificationBuilder("HelloWorld")
                .setTitle(R.string.app_name)
                .setExpandable(true, R.drawable.ic_launcher_playstore)
                .setLargeIcon(R.drawable.ic_launcher_playstore)
                .setSmallIcon(R.drawable.ic_baseline_add_circle_24)
                .createChannel("name", "description", NotificationBuilder.IMPORTANCE.DEFAULT, NotificationBuilder.IMPORTANCE.DEFAULT)
                .notifyUser(2)
        }

        btn_display_notification_with_action.setOnClickListener {
            NotificationBuilder.from(this)
                .createNotificationBuilder("HelloWorld")
                .setTitle(R.string.app_name)
                .setContent(R.string.common_random)
                .setSmallIcon(R.drawable.ic_baseline_add_circle_24)
                .createChannel("name", "description", NotificationBuilder.IMPORTANCE.DEFAULT, NotificationBuilder.IMPORTANCE.DEFAULT)
                .setActionButton(getString(R.string.app_name))
                .notifyUser(3)
        }

        btn_display_notification_with_large_block_of_text.setOnClickListener {
            NotificationBuilder.from(this)
                .createNotificationBuilder("HelloWorld")
                .setTitle(R.string.app_name)
                .setContent(R.string.common_random)
                .setSmallIcon(R.drawable.ic_baseline_add_circle_24)
                .createChannel("name", "description", NotificationBuilder.IMPORTANCE.DEFAULT, NotificationBuilder.IMPORTANCE.DEFAULT)
                .setBlockOfText(getString(R.string.common_random))
                .setActionButton(getString(R.string.app_name))
                .notifyUser(4)
        }

        btn_display_notification_with_picture_and_action.setOnClickListener {
            NotificationBuilder.from(this)
                .createNotificationBuilder("Hello World")
                .setTitle(R.string.app_name)
                .setContext("this is how it's done")
                .setSmallIcon(R.drawable.ic_baseline_add_circle_24)
                .setLargeIcon(R.drawable.ic_launcher_playstore)
                .setExpandable(true, R.drawable.ic_launcher_playstore)
                .setActionButton(getString(R.string.app_name))
                .createChannel("name", "description", NotificationBuilder.IMPORTANCE.DEFAULT, NotificationBuilder.IMPORTANCE.DEFAULT)
                .notifyUser(5)
        }
    }
}