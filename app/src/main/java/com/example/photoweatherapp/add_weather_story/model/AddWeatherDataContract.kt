package com.example.photoweatherapp.add_weather_story.model

import com.example.photoweatherapp.add_weather_story.data_classes.WeatherModel
import com.example.photoweatherapp.data_base.WeatherDao
import com.example.photoweatherapp.data_base.entity.WeatherEntity

interface AddWeatherDataContract {

    interface Repository {
        fun getWeatherDao(): WeatherDao
        fun saveWeatherInfo(model: WeatherModel)
        fun getWeatherList(): List<WeatherModel>?
    }

    interface Local {
        fun getWeatherDao(): WeatherDao
        fun saveAllWeather(list: List<WeatherEntity>)
        fun getWeatherList(): List<WeatherEntity>?
    }
}