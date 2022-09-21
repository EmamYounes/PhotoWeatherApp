package com.example.photoweatherapp.add_weather_story.model

import android.util.Log
import com.example.photoweatherapp.data_base.AppDatabase
import com.example.photoweatherapp.data_base.entity.WeatherEntity

class AddWeatherLocalData(private val db: AppDatabase) :
    AddWeatherDataContract.Local {

    override suspend fun insert(model: WeatherEntity) {
        val text = db.getWeatherDao().insert(model)
        Log.d("eeeeeeeeee", text.toString())
        return text
    }

    override suspend fun getWeatherList(): List<WeatherEntity> {
        return db.getWeatherDao().getWeatherList()
    }

}