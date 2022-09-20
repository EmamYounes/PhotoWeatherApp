package com.example.photoweatherapp.commons.application

import android.app.Application
import com.example.photoweatherapp.add_weather_story.model.AddWeatherLocalData
import com.example.photoweatherapp.add_weather_story.model.AddWeatherRepository
import com.example.photoweatherapp.add_weather_story.view_model.AddWeatherViewModel
import com.example.photoweatherapp.add_weather_story.view_model.AddWeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class MyApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))

        bind() from singleton { AddWeatherLocalData() }
        bind() from singleton { AddWeatherRepository(instance()) }
        bind() from singleton { AddWeatherViewModelFactory(instance()) }
        bind() from singleton { AddWeatherViewModel(instance()) }
    }
}