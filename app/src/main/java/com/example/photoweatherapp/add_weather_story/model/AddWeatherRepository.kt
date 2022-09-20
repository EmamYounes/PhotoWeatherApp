package com.example.photoweatherapp.add_weather_story.model

import com.example.photoweatherapp.add_weather_story.data_classes.WeatherModel
import com.example.photoweatherapp.data_base.WeatherDao
import com.example.photoweatherapp.data_base.entity.WeatherEntity

class AddWeatherRepository(
    private val local: AddWeatherDataContract.Local
) : AddWeatherDataContract.Repository {

    override fun getWeatherDao(): WeatherDao {
        return local.getWeatherDao()
    }

    override fun saveWeatherInfo(model: WeatherModel) {
        val item = WeatherEntity(
            imageByteArray = model.imageByteArray,
            addPlaceName = model.addPlaceName,
            temperature = model.temperature,
            weatherCondition = model.weatherCondition
        )
        val list = mutableListOf<WeatherEntity>()
        list.add(item)
        local.getWeatherDao().getWeatherList().value?.let { list.addAll(it) }
        local.getWeatherDao().saveAllWeather(list)
    }

    override fun getWeatherList(): List<WeatherModel>? {

        return local.getWeatherList()?.map {
            WeatherModel(
                imageByteArray = it.imageByteArray,
                addPlaceName = it.addPlaceName,
                temperature = it.temperature,
                weatherCondition = it.weatherCondition,
            )
        }?.toList()
    }
}