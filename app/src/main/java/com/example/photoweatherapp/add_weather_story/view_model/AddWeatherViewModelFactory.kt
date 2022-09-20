package com.example.photoweatherapp.add_weather_story.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.photoweatherapp.add_weather_story.model.AddWeatherRepository

class AddWeatherViewModelFactory(
    private val repository: AddWeatherRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddWeatherViewModel(repository) as T
    }

}