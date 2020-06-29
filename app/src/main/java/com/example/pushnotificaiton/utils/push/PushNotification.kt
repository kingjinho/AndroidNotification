package com.example.pushnotificaiton.utils.push

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

/**
 * Created by KING JINHO on 2020-06-23
 */
class PushNotification {

    private var mSmallIcon : Int = 0
    private lateinit var mStyle: Notification.Style
    private var mLargeIconRes: Int = 0
    private lateinit var mTitle: String
    private lateinit var mContent: String

    companion object {
        private lateinit var CHANNEL_ID: String
        private lateinit var CONTEXT: Context
        private lateinit var mNotificationBuilder: NotificationCompat.Builder
        private lateinit var mNotificationManagerCompat: NotificationManagerCompat

        fun from(context: Context): PushNotification {
            CONTEXT = context
            return PushNotification()
        }
    }

    fun createNotificationBuilder(channel_id: String): PushNotification {
        CHANNEL_ID = channel_id
        mNotificationBuilder = NotificationCompat.Builder(CONTEXT, channel_id)
        mNotificationBuilder.setWhen(System.currentTimeMillis())
        return this
    }

    //only applies to api >= 26
    fun createChannel(): PushNotification {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val notificationManager =
                CONTEXT.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val name = "channel"
            val descriptionText = "description"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }
        return this
    }

    fun notifyUser() {
        mNotificationManagerCompat = NotificationManagerCompat.from(CONTEXT)
        mNotificationManagerCompat.notify(1, mNotificationBuilder.build())
    }

    fun setSmallIcon(smallIconRes : Int): PushNotification {
        mNotificationBuilder.setSmallIcon(smallIconRes)
        return this
    }

    fun setTimeStamp(): PushNotification {
        return this
    }

    fun setStyle(style: Notification.Style): PushNotification {
        mStyle = style
        return this
    }

    fun setContent(content: Int) {
        mNotificationBuilder.setContentText(CONTEXT.getText(content))
    }

    fun setTitle(titleRes: Int): PushNotification {
        mNotificationBuilder.setContentTitle(CONTEXT.getString(titleRes))
        return this
    }

    fun setLargeIcon(largeIconRes: Int): PushNotification {
        mLargeIconRes = largeIconRes
        return this
    }

    fun setExpandable(yesOrNo: Boolean) : PushNotification {
        if (yesOrNo) {
            return this
        }
        return this
    }


}