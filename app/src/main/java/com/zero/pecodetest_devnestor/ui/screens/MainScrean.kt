package com.zero.pecodetest_devnestor.ui.screens

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import com.zero.pecodetest_devnestor.base.BaseFragment
import com.zero.pecodetest_devnestor.databinding.ScreenMainBinding
import com.zero.pecodetest_devnestor.util.extensions.gone
import com.zero.pecodetest_devnestor.util.extensions.visible
import com.zero.pecodetest_devnestor.R
import androidx.core.app.NotificationManagerCompat
import com.zero.pecodetest_devnestor.ui.main.MainActivity
import kotlin.random.Random

class MainScreen(private val position: Int) : BaseFragment<ScreenMainBinding>() {

    companion object {
        const val CHANNEL_ID = "pecode_test_dev_nestor_channel_id"
    }

    override fun onCreate(i: LayoutInflater, c: ViewGroup?)
        = ScreenMainBinding.inflate(i, c, false)

    override fun initialize() {

        viewBinding?.numFragment?.text = "$position"

        if (position > 0) viewBinding?.btnMinus?.visible()
        else viewBinding?.btnMinus?.gone()

        viewBinding?.btnPlus?.setOnClickListener {
            mainActivity.adapter.addFrag()
            mainActivity.viewPager.currentItem = position + 1
        }
        viewBinding?.btnMinus?.setOnClickListener {
            if (position == mainActivity.adapter.itemCount - 1) {
                mainActivity.viewPager.currentItem = position - 1
            }
            mainActivity.adapter.removeFrag()
        }
        viewBinding?.centerEllipse?.setOnClickListener {

            val notificationManager = NotificationManagerCompat.from(mainActivity)

            val intent = Intent(mainActivity, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtras(Bundle().apply {
                    putInt(mainActivity.intentIdName, position)
                })
            }
            val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_center_ellipse)
            val iUniqueId = (System.currentTimeMillis() and 0xfff1919).toInt()
            val pendingIntent: PendingIntent =
                PendingIntent.getActivity(mainActivity, iUniqueId, intent, PendingIntent.FLAG_IMMUTABLE)

            val builder = NotificationCompat.Builder(mainActivity, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_rectangle)
                .setLargeIcon(largeIcon)
                .setContentTitle("title")
                .setContentText("Notification $position")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelId = CHANNEL_ID
                val channel = NotificationChannel(
                    channelId,
                    "title",
                    NotificationManager.IMPORTANCE_HIGH
                )
                notificationManager.createNotificationChannel(channel)
                builder.setChannelId(channelId)
            }

            notificationManager.notify(Random.nextInt(0, 20), builder.build())

        }
    }
}