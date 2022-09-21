package com.example.photoweatherapp.add_weather_story.model

import com.example.photoweatherapp.add_weather_story.data_classes.WeatherModel
import com.example.photoweatherapp.data_base.entity.WeatherEntity

class AddWeatherRepository(
    private val local: AddWeatherDataContract.Local
) : AddWeatherDataContract.Repository {

    override suspend fun insert(model: WeatherModel) {
        val item = WeatherEntity(
            imageByteArray = model.imageByteArray.toString(),
            addPlaceName = model.addPlaceName,
            temperature = model.temperature,
            weatherCondition = model.weatherCondition
        )
        local.insert(item)
    }

    override suspend fun getWeatherList(): List<WeatherModel> {

        return local.getWeatherList().map {
            WeatherModel(
                imageByteArray = it.imageByteArray.toByteArray(),
                addPlaceName = it.addPlaceName,
                temperature = it.temperature,
                weatherCondition = it.weatherCondition,
            )
        }.toList()
    }
}