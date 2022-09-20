package com.example.photoweatherapp.add_weather_story.Models

data class WeatherModel(
    var imageByteArray: ByteArray,
    var addPlaceName: String,
    var temperature: String,
    var weatherCondition: String

)