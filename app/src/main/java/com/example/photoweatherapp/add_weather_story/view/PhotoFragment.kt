package com.example.photoweatherapp.add_weather_story.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.photoweatherapp.R
import com.example.photoweatherapp.add_weather_story.view_model.AddWeatherViewModel
import com.example.photoweatherapp.base.BaseFragment
import kotlinx.android.synthetic.main.bottom_button.view.*
import kotlinx.android.synthetic.main.photo_fragment.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class PhotoFragment : BaseFragment(), KodeinAware {

    override val kodein by kodein()

    private val viewModel: AddWeatherViewModel by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.photo_fragment, container, false)
    }


    override fun bindView(view: View) {
        super.bindView(view)
        viewModel.imageByteArray.value?.let { setImageByteArray(view.image_iv, it) }
        manageFinishBtn(view)
        manageCancelBtn(view)
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
            navigateTo(R.id.FillWeatherInformationFragment)
        }
    }
}