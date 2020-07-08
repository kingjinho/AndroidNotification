package com.example.pushnotificaiton.utils.push

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.renderscript.RenderScript
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.pushnotificaiton.MainActivity
import com.example.pushnotificaiton.R

/**
 * Created by KING JINHO on 2020-06-23
 */
class PushNotification {

    private lateinit var mStyle: Notification.Style

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
        setDefaultTabAction()
        return this
    }

    private fun setDefaultTabAction() {
        val intent = Intent(CONTEXT, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(CONTEXT, 0, intent, 0)
        mNotificationBuilder.setContentIntent(pendingIntent)
            .setAutoCancel(true)
    }

    //priority API < 26
    //create channel and set channels importance if API >= 26
    fun createChannel(name: String, descriptionText: String, importance: IMPORTANCE, priority: IMPORTANCE): PushNotification {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val notificationManager =
                CONTEXT.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val importance = setImportance(importance)
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        } else {
            mNotificationBuilder.priority = setPriority(priority)
        }
        return this
    }

    fun setSmallIcon(smallIconRes: Int): PushNotification {
        mNotificationBuilder.setSmallIcon(smallIconRes)
        return this
    }

    fun setTitle(titleRes: Int): PushNotification {
        mNotificationBuilder.setContentTitle(CONTEXT.getString(titleRes))
        return this
    }

    fun setContent(content: Int): PushNotification {
        mNotificationBuilder.setContentText(CONTEXT.getText(content))
        return this
    }

    fun setTimeStamp(): PushNotification {
        return this
    }

    fun setStyle(style: Notification.Style): PushNotification {
        mStyle = style
        return this
    }

    fun setLargeIcon(largeIconRes: Int): PushNotification {
        val bitmap = BitmapFactory.decodeResource(CONTEXT.resources, largeIconRes)
        mNotificationBuilder.setLargeIcon(bitmap)
        return this
    }

    fun setExpandable(@NonNull yesOrNo: Boolean, @Nullable picture: Int): PushNotification {
        val bitmap = BitmapFactory.decodeResource(CONTEXT.resources, picture)
        if (yesOrNo) {
            mNotificationBuilder.setStyle(
                NotificationCompat.BigPictureStyle().bigLargeIcon(null).bigPicture(bitmap)
            )
        }
        return this
    }

    fun setActionButton(btnTitle: String): PushNotification {
        val intent = Intent(CONTEXT, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(CONTEXT, 0, intent, 0)
        mNotificationBuilder.addAction(R.drawable.ic_launcher_playstore, btnTitle, pendingIntent)
        return this
    }


    fun notifyUser() {
        mNotificationManagerCompat = NotificationManagerCompat.from(CONTEXT)
        mNotificationManagerCompat.notify(1, mNotificationBuilder.build())
    }


    private fun setImportance(importance: IMPORTANCE) : Int {
        return when (importance) {
            IMPORTANCE.LOW ->  NotificationManager.IMPORTANCE_LOW
            IMPORTANCE.DEFAULT ->  NotificationManager.IMPORTANCE_DEFAULT
            IMPORTANCE.HIGH ->  NotificationManager.IMPORTANCE_HIGH
            IMPORTANCE.MAX ->  NotificationManager.IMPORTANCE_MAX
            IMPORTANCE.MIN ->  NotificationManager.IMPORTANCE_MIN
            IMPORTANCE.NONE ->  NotificationManager.IMPORTANCE_NONE
        }
    }

    private fun setPriority(priority: IMPORTANCE): Int {
        return when (priority) {
            IMPORTANCE.LOW ->  NotificationCompat.PRIORITY_LOW
            IMPORTANCE.HIGH ->  NotificationCompat.PRIORITY_HIGH
            IMPORTANCE.MAX ->  NotificationCompat.PRIORITY_MAX
            IMPORTANCE.MIN ->  NotificationCompat.PRIORITY_MIN
            else ->  NotificationCompat.PRIORITY_DEFAULT
        }
    }

    enum class IMPORTANCE {
        LOW,
        DEFAULT,
        HIGH,
        MAX,
        MIN,
        NONE
    }
}