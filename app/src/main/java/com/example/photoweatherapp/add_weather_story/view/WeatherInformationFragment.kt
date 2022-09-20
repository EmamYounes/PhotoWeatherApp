package com.example.photoweatherapp.add_weather_story.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.photoweatherapp.R
import com.example.photoweatherapp.add_weather_story.view_model.AddWeatherViewModel
import com.example.photoweatherapp.base.BaseFragment
import kotlinx.android.synthetic.main.bottom_button.view.*
import kotlinx.android.synthetic.main.fragment_weather_information.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class WeatherInformationFragment : BaseFragment(), KodeinAware {

    override val kodein by kodein()

    private val viewModel: AddWeatherViewModel by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_information, container, false)
    }


    override fun bindView(view: View) {
        super.bindView(view)
        viewModel.initWeatherInformationFragment()
        manageFinishBtn(view)
        manageCancelBtn(view)
        setInputEditTextListener(view)
        bindEditTextView(view)

    }

    private fun bindEditTextView(view: View) {
        bindEditText(view.add_place_name, viewModel.addPlaceName)
        bindEditText(view.temperature, viewModel.temperature)
        bindEditText(view.weather_condition, viewModel.weatherCondition)
    }

    private fun setInputEditTextListener(view: View) {
        setInputEditTextFocusListener(view.add_place_name_input_layout, view.add_place_name)
        setInputEditTextFocusListener(view.temperature_input_layout, view.temperature)
        setInputEditTextFocusListener(view.weather_condition_input_layout, view.weather_condition)
    }

    private fun manageCancelBtn(view: View) {
        view.cancel_btn.text = getString(R.string.previous)
        view.cancel_btn.setOnClickListener {
            navigateUp()
        }
    }

    private fun manageFinishBtn(view: View) {
        view.finish_btn.text = getString(R.string.next)
        view.finish_btn.setOnClickListener {
            navigateTo(R.id.ShowWeatherInformationFragment)
        }
        viewModel.addCompositeDisposable(
            viewModel.buttonEnabled.subscribe {
                enableSubmitBtn(it, view.finish_btn)
            }
        )
    }
}