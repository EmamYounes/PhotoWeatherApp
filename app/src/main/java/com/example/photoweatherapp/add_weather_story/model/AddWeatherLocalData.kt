package com.example.photoweatherapp.add_weather_story.model

import com.example.photoweatherapp.data_base.AppDatabase
import com.example.photoweatherapp.data_base.WeatherDao
import com.example.photoweatherapp.data_base.entity.WeatherEntity

class AddWeatherLocalData(private val db: AppDatabase) :
    AddWeatherDataContract.Local {
    override fun getWeatherDao(): WeatherDao {
        return db.getWeatherDao()
    }

    override fun saveAllWeather(list: List<WeatherEntity>) {
        return db.getWeatherDao().saveAllWeather(list)
    }

    override fun getWeatherList(): List<WeatherEntity>? {
        return db.getWeatherDao().getWeatherList().value
    }

}