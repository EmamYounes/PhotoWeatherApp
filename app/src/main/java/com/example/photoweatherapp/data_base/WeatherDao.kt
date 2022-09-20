package com.example.photoweatherapp.data_base

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.photoweatherapp.data_base.entity.WeatherEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllWeather(list: List<WeatherEntity>)

    @Query("SELECT * FROM WeatherEntity")
    fun getWeatherList(): LiveData<List<WeatherEntity>>

}