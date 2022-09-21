package com.example.photoweatherapp.add_weather_story.view

import android.os.Bundle
import android.util.Log
import com.example.photoweatherapp.R
import com.example.photoweatherapp.base.BaseActivity
import com.example.photoweatherapp.data_base.AppDatabase
import com.example.photoweatherapp.data_base.entity.WeatherEntity

class AddWeatherActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_weather)
    }
}