package com.example.photoweatherapp.add_weather_story.model

import com.example.photoweatherapp.add_weather_story.data_classes.WeatherModel
import com.example.photoweatherapp.data_base.entity.WeatherEntity

interface AddWeatherDataContract {

    interface Repository {
        suspend fun insert(model: WeatherModel)
        suspend fun getWeatherList(): List<WeatherModel>
    }

    interface Local {
        suspend fun insert(model: WeatherEntity)
        suspend fun getWeatherList(): List<WeatherEntity>
    }
}