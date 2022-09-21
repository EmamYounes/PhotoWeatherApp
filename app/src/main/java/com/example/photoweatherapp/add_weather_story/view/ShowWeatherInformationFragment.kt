package com.example.photoweatherapp.add_weather_story.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import com.example.photoweatherapp.R
import com.example.photoweatherapp.add_weather_story.data_classes.WeatherModel
import com.example.photoweatherapp.add_weather_story.view_model.AddWeatherViewModel
import com.example.photoweatherapp.base.BaseFragment
import com.example.photoweatherapp.data_base.entity.WeatherEntity
import kotlinx.android.synthetic.main.bottom_button.view.*
import kotlinx.android.synthetic.main.fragment_show_weather_information.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ShowWeatherInformationFragment : BaseFragment(), KodeinAware {

    override val kodein by kodein()

    private val viewModel: AddWeatherViewModel by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_weather_information, container, false)
    }


    override fun bindView(view: View) {
        super.bindView(view)
        manageFinishBtn(view)
        manageCancelBtn(view)
        bindData(view)
    }

    private fun bindData(view: View) {
        view.add_place_name.text = viewModel.addPlaceName.value
        view.temperature.text = viewModel.temperature.value
        view.weather_condition.text = viewModel.weatherCondition.value
        viewModel.imageByteArray.value?.let { setImageByteArray(view.image_iv, it) }
    }

    private fun manageCancelBtn(view: View) {
        view.cancel_btn.text = getString(R.string.previous)
        view.cancel_btn.setOnClickListener {
            navigateUp()
        }
    }

    private fun manageFinishBtn(view: View) {
        view.finish_btn.text = getString(R.string.submit)
        view.finish_btn.setOnClickListener {

            viewModel.viewModelScope.launch(Dispatchers.IO) {
                val model = WeatherModel(
                    imageByteArray = viewModel.imageByteArray.value!!,
                    addPlaceName = viewModel.addPlaceName.value!!,
                    temperature = viewModel.temperature.value!!,
                    weatherCondition = viewModel.weatherCondition.value!!
                )
                viewModel.saveWeatherInfo(model)
                withContext(Dispatchers.Main) {
                    navigateToHome()
                }
            }

        }
        enableSubmitBtn(true, view.finish_btn)
    }

    override fun getPageTitle(): String {
        return getString(R.string.Show_Weather_Info)
    }
}