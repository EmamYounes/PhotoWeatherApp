package com.example.photoweatherapp.add_weather_story.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.photoweatherapp.R
import com.example.photoweatherapp.add_weather_story.view_model.AddWeatherViewModel
import com.example.photoweatherapp.base.BaseFragment
import kotlinx.android.synthetic.main.bottom_button.view.*
import kotlinx.android.synthetic.main.fragment_show_chosen_weather_item.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ShowChosenWeatherItemFragment : BaseFragment(), KodeinAware {

    override val kodein by kodein()

    private val viewModel: AddWeatherViewModel by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_chosen_weather_item, container, false)
    }


    override fun bindView(view: View) {
        super.bindView(view)
        manageFinishBtn(view)
        manageCancelBtn(view)
        bindData(view)
    }

    private fun bindData(view: View) {
        view.add_place_name.text = viewModel.weatherModelClicked.value?.addPlaceName
        view.temperature.text = viewModel.weatherModelClicked.value?.temperature
        view.weather_condition.text = viewModel.weatherModelClicked.value?.weatherCondition
        viewModel.weatherModelClicked.value?.imageByteArray.let {
            it?.let { byteArray ->
                setImageByteArray(
                    view.image_iv,
                    byteArray
                )
            }
        }
    }

    private fun manageCancelBtn(view: View) {
        view.cancel_btn.setOnClickListener {
            navigateUp()
        }
    }

    private fun manageFinishBtn(view: View) {
        view.finish_btn.visibility = View.GONE
    }

    override fun getPageTitle(): String {
        return getString(R.string.chosen_item)
    }
}