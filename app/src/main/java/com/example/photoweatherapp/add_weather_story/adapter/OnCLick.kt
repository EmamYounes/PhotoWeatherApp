package com.example.photoweatherapp.add_weather_story.adapter

import com.example.photoweatherapp.add_weather_story.data_classes.WeatherModel

interface OnCLick {
    fun onItemClickListener(model: WeatherModel?)
}
