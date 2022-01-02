package com.example.notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notificationsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    // notification variables
    lateinit var notificationManager: NotificationManager
    lateinit var builder: Notification.Builder
    private val notificationId = 1111
    private val channelId = "myapp.notifications"
    private val description = "my Notification App"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.button.setOnClickListener {

            notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
                var notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this, channelId)
                    .setSmallIcon(R.drawable.circle_green)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.circle_green))
                    .setContentTitle("Notification")
                    .setContentText(binding.editText.text.toString())

            }
            else{
                builder = Notification.Builder(this)
                    .setSmallIcon(R.drawable.circle_green)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.circle_green))
                    .setContentTitle("Notification")
                    .setContentText(binding.editText.text.toString())
            }

            notificationManager.notify(notificationId, builder.build())


        }





    }
}