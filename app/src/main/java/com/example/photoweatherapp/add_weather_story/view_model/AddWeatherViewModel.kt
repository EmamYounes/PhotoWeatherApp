package com.example.photoweatherapp.add_weather_story.view_model

import androidx.lifecycle.ViewModel
import com.example.photoweatherapp.add_weather_story.Models.WeatherModel
import com.example.photoweatherapp.add_weather_story.model.AddWeatherRepository
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class AddWeatherViewModel(
    private val repository: AddWeatherRepository,
) : ViewModel() {
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    var imageByteArray = BehaviorRelay.create<ByteArray>()
    var addPlaceName = BehaviorRelay.create<String>()
    var temperature = BehaviorRelay.create<String>()
    var weatherCondition = BehaviorRelay.create<String>()


    var weatherModelClicked = BehaviorRelay.create<WeatherModel>()

    var buttonEnabled = BehaviorRelay.createDefault(false)

    fun initHomeFragment() {
        imageByteArray = BehaviorRelay.create()
        addPlaceName = BehaviorRelay.create()
        temperature = BehaviorRelay.create()
        weatherCondition = BehaviorRelay.create()
        buttonEnabled = BehaviorRelay.createDefault(false)
    }

    fun initWeatherInformationFragment() {

        val myList = arrayOf(
            addPlaceName,
            temperature,
            weatherCondition

        )
        addCompositeDisposable(Observable.combineLatest(myList) {
            val addPlaceName = it[0] as String
            val temperature = it[1] as String
            val weatherCondition = it[2] as String
            addPlaceName.isNotEmpty()
                    && temperature.isNotEmpty()
                    && weatherCondition.isNotEmpty()

        }
            .subscribe {
                buttonEnabled.accept(it)
            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun addCompositeDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}