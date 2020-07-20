package com.example.pushnotificaiton.utils.push

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.pushnotificaiton.MainActivity
import com.example.pushnotificaiton.R

/**
 * Created by KING JINHO on 2020-06-23
 */
class NotificationBuilder {


    companion object {
        private lateinit var CHANNEL_ID: String
        private lateinit var CONTEXT: Context
        private lateinit var mNotificationBuilder: NotificationCompat.Builder
        private lateinit var mNotificationManagerCompat: NotificationManagerCompat

        fun from(context: Context): NotificationBuilder {
            CONTEXT = context
            return NotificationBuilder()
        }
    }

    fun createNotificationBuilder(channel_id: String): NotificationBuilder {
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

    fun setSmallIcon(smallIconRes: Int): NotificationBuilder {
        mNotificationBuilder.setSmallIcon(smallIconRes)
        return this
    }

    fun setTitle(titleRes: Int): NotificationBuilder {
        mNotificationBuilder.setContentTitle(CONTEXT.getString(titleRes))
        return this
    }

    fun setContent(content: Int): NotificationBuilder {
        mNotificationBuilder.setContentText(CONTEXT.getString(content))
        return this
    }

    fun setContext(context: String): NotificationBuilder {
        mNotificationBuilder.setContentText(context)
        return this
    }

    fun setBlockOfText(blockOfText: String): NotificationBuilder {
        mNotificationBuilder.setStyle(
            NotificationCompat.BigTextStyle()
                //.setBigContentTitle("this is big content title") overrides setContentTitle()
                //.setSummaryText("this is summary text")  right next to app name
                .bigText(blockOfText)
        )
        return this
    }

    fun setBlockOfText(blockOfText: Int): NotificationBuilder {
        mNotificationBuilder.setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(CONTEXT.getString(blockOfText))
        )
        return this
    }

    fun setLargeIcon(largeIconRes: Int): NotificationBuilder {
        val bitmap = BitmapFactory.decodeResource(CONTEXT.resources, largeIconRes)
        mNotificationBuilder.setLargeIcon(bitmap)
        return this
    }

    fun setExpandable(@NonNull yesOrNo: Boolean, @Nullable picture: Int): NotificationBuilder {
        val bitmap = BitmapFactory.decodeResource(CONTEXT.resources, picture)
        if (yesOrNo) {
            mNotificationBuilder.setStyle(
                NotificationCompat.BigPictureStyle().bigLargeIcon(null).bigPicture(bitmap)
            )
        }
        return this
    }

    fun setActionButton(btnTitle: String): NotificationBuilder {
        val intent = Intent(CONTEXT, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(CONTEXT, 0, intent, 0)
        mNotificationBuilder.addAction(R.drawable.ic_launcher_playstore, btnTitle, pendingIntent)
        return this
    }

    //priority API < 26
    //create channel and set channels importance if API >= 26
    fun createChannel(
        name: String,
        descriptionText: String,
        importance: IMPORTANCE,
        priority: IMPORTANCE
    ): NotificationBuilder {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // the handle to a system-level service by class
            //register information on service
            val notificationManager =
                CONTEXT.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val importance = setImportance(importance)
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        } else
            mNotificationBuilder.priority = setPriority(priority)
        return this
    }


    fun notifyUser(num : Int) {
        mNotificationManagerCompat = NotificationManagerCompat.from(CONTEXT)
        //id: unique in your app. otherwise, it will update to new one
        mNotificationManagerCompat.notify(num, mNotificationBuilder.build())
    }


    private fun setImportance(importance: IMPORTANCE): Int {
        return when (importance) {
            IMPORTANCE.LOW -> NotificationManager.IMPORTANCE_LOW
            IMPORTANCE.HIGH -> NotificationManager.IMPORTANCE_HIGH
            IMPORTANCE.MAX -> NotificationManager.IMPORTANCE_MAX
            IMPORTANCE.MIN -> NotificationManager.IMPORTANCE_MIN
            IMPORTANCE.NONE -> NotificationManager.IMPORTANCE_NONE
            else -> NotificationManager.IMPORTANCE_DEFAULT
        }
    }

    private fun setPriority(priority: IMPORTANCE): Int {
        return when (priority) {
            IMPORTANCE.LOW -> NotificationCompat.PRIORITY_LOW
            IMPORTANCE.HIGH -> NotificationCompat.PRIORITY_HIGH
            IMPORTANCE.MAX -> NotificationCompat.PRIORITY_MAX
            IMPORTANCE.MIN -> NotificationCompat.PRIORITY_MIN
            else -> NotificationCompat.PRIORITY_DEFAULT
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