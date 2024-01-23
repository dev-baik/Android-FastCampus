package com.example.chapter02_7

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices

class UpdateWeatherService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // notification channel
        createChannel()
        startForeground(1, createNotification())

        val appWidgetManager: AppWidgetManager = AppWidgetManager.getInstance(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val pendingIntent: PendingIntent = Intent(this, SettingActivity::class.java).let {
                PendingIntent.getActivity(this, 2, it, PendingIntent.FLAG_IMMUTABLE)
            }
            RemoteViews(packageName, R.layout.widget_weather).apply {
                setTextViewText(R.id.temperatureTextView, "권한없음")
                setOnClickPendingIntent(R.id.temperatureTextView, pendingIntent)
                setTextViewText(R.id.weatherTextView, "")
            }.also { removeViews ->
                val appWidgetName = ComponentName(this, WeatherAppWidgetProvider::class.java)
                appWidgetManager.updateAppWidget(appWidgetName, removeViews)
            }

            stopSelf()

            return super.onStartCommand(intent, flags, startId)
        }

        LocationServices.getFusedLocationProviderClient(this).lastLocation.addOnSuccessListener {
            WeatherRepository.getVillageForecast(
                longitude = it.longitude,
                latitude = it.latitude,
                successCallback = { forecastList ->
                    val pendingServiceIntent: PendingIntent =
                        Intent(this, UpdateWeatherService::class.java).let { intent ->
                            PendingIntent.getService(this, 1, intent, PendingIntent.FLAG_IMMUTABLE)
                        }

                    val currentForecast = forecastList.first()

                    RemoteViews(packageName, R.layout.widget_weather).apply {
                        setTextViewText(
                            R.id.temperatureTextView,
                            getString(R.string.temperature_text, currentForecast.temperature)
                        )
                        setTextViewText(
                            R.id.weatherTextView,
                            currentForecast.weather
                        )
                        setOnClickPendingIntent(R.id.temperatureTextView, pendingServiceIntent)
                    }.also { remoteViews ->
                        val appWidgetName =
                            ComponentName(this, WeatherAppWidgetProvider::class.java)
                        appWidgetManager.updateAppWidget(appWidgetName, remoteViews)
                    }
                    stopSelf()
                },
                failureCallback = {
                    val pendingServiceIntent: PendingIntent =
                        Intent(this, UpdateWeatherService::class.java).let { intent ->
                            PendingIntent.getService(this, 1, intent, PendingIntent.FLAG_IMMUTABLE)
                        }

                    RemoteViews(packageName, R.layout.widget_weather).apply {
                        setTextViewText(
                            R.id.temperatureTextView,
                            "에러"
                        )
                        setTextViewText(
                            R.id.weatherTextView,
                            ""
                        )
                        setOnClickPendingIntent(R.id.temperatureTextView, pendingServiceIntent)
                    }.also { remoteViews ->
                        val appWidgetName =
                            ComponentName(this, WeatherAppWidgetProvider::class.java)
                        appWidgetManager.updateAppWidget(appWidgetName, remoteViews)
                    }

                    stopSelf()
                }
            )
        }
        return super.onStartCommand(intent, flags, startId)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL,
            "날씨앱",
            NotificationManager.IMPORTANCE_LOW
        )

        channel.description = "위젯을 업데이트하는 채널"

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotification(): Notification {
        return Notification.Builder(this, NOTIFICATION_CHANNEL)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("날씨앱")
            .setContentText("날씨 업데이트")
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()

        stopForeground(STOP_FOREGROUND_REMOVE)
    }

    companion object {
        const val NOTIFICATION_CHANNEL = "widget_refresh_channel"
    }
}