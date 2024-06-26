package ru.androidheroes.kamchatka.ui.feed

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ru.androidheroes.kamchatka.R

object NotificationHelper {

    /**
     * Sets up the notification channels for API 26+.
     * Note: This uses package name + channel name to create unique channelId's.
     *
     * @param context     application context
     * @param importance  importance level for the notificaiton channel
     * @param showBadge   whether the channel should have a notification badge
     * @param name        name for the notification channel
     * @param description description for the notification channel
     */
    fun createNotificationChannel(
        context: Context,
        importance: Int,
        showBadge: Boolean,
        name: String,
        description: String
    ) {

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channelId = "${context.packageName}-$name"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)

            // Register the channel with the system
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    /**
     * Helps issue the default application channels (package name + app name) notifications.
     * Note: this shows the use of [NotificationCompat.BigTextStyle] for expanded notifications.
     *
     * @param context    current application context
     * @param title      title for the notification
     * @param message    content text for the notification when it's not expanded
     * @param bigText    long form text for the expanded notification
     * @param autoCancel `true` or `false` for auto cancelling a notification.
     * if this is true, a [PendingIntent] is attached to the notification to
     * open the application.
     */
    fun createSampleDataNotification(
        context: Context, title: String, message: String,
        bigText: String, autoCancel: Boolean,
        intent: Intent
    ) {

        // 1
        val channelId = "${context.packageName}-${context.getString(R.string.app_name)}"
        // 2
        val notificationBuilder = NotificationCompat.Builder(context, channelId).apply {
            setSmallIcon(R.mipmap.ic_launcher) // 3
            setContentTitle(title) // 4
            setContentText(message) // 5
            setAutoCancel(true) // 6
            setOngoing(true)
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setStyle(NotificationCompat.BigTextStyle().bigText(bigText)) // 7
            priority = NotificationCompat.PRIORITY_HIGH // 8

            val random =(0 until Integer.MAX_VALUE).random()
            val pendingIntent = PendingIntent.getActivity(context, random, intent, 0)
            setContentIntent(pendingIntent)
        }

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify((0 until Integer.MAX_VALUE).random(), notificationBuilder.build())
    }
}