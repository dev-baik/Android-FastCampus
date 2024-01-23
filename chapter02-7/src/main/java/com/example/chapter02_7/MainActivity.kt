package com.example.chapter02_7

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                updateLocation()
            }

            else -> {
                Toast.makeText(this, "위치 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", packageName, null)
                }
                startActivity(intent)
                finish()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION))


    }

    private fun transformRainType(forecast: ForecastEntity): String {
        return when (forecast.forecastValue.toInt()) {
            0 -> "없음"
            1 -> "비"
            2 -> "비/눈"
            3 -> "눈"
            4 -> "소나기"
            else -> ""
        }
    }

    private fun transformSky(forecast: ForecastEntity): String {
        return when (forecast.forecastValue.toInt()) {
            1 -> "맑음"
            2 -> "구름많음"
            3 -> "흐림"
            else -> ""
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION))
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://apis.data.go.kr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(WeatherService::class.java)

            val baseDateTime = BaseDataTime.getBaseDateTime()
            val converter = GeoPointConverter()
            val point = converter.convert(lat = it.latitude, lon = it.longitude)

            service.getVillageForecast(
                serviceKey = "BdCsor5QNjxKxkTcr9XR+jmv3CDzcN9+vgLq2GTTkI6e9L0XVBCJPaEESxkZFTE0+v8Fzh8YBlahP82mmXjpiw==",
                baseData = baseDateTime.baseData,
                baseTime = baseDateTime.baseTime,
                nx = point.nx,
                ny = point.ny
            ).enqueue(object : Callback<WeatherEntity> {
                override fun onResponse(
                    call: Call<WeatherEntity>,
                    response: Response<WeatherEntity>
                ) {
                    val forecastDateTimeMap = mutableMapOf<String, Forecast>()
                    val forecastList =
                        response.body()?.response?.body?.items?.forecastEntities.orEmpty()

                    for (forecast in forecastList) {
                        if (forecastDateTimeMap["${forecast.forecastDate}/${forecast.forecastTime}"] == null) {
                            forecastDateTimeMap["${forecast.forecastDate}/${forecast.forecastTime}"] =
                                Forecast(
                                    forecastDate = forecast.forecastDate,
                                    forecastTime = forecast.forecastTime
                                )
                        }

                        forecastDateTimeMap["${forecast.forecastDate}/${forecast.forecastTime}"]?.apply {
                            when (forecast.category) {
                                Category.POP -> precipitation = forecast.forecastValue.toInt()
                                Category.PTY -> precipitationType = transformRainType(forecast)
                                Category.SKY -> sky = transformSky(forecast)
                                Category.TMP -> temperature = forecast.forecastValue.toDouble()
                                else -> {}
                            }
                        }
                    }
                    Log.e("태그", forecastDateTimeMap.toString())
                }

                override fun onFailure(call: Call<WeatherEntity>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }
}