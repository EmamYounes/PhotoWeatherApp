package com.example.photoweatherapp.add_weather_story.view

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.photoweatherapp.R
import com.example.photoweatherapp.add_weather_story.adapter.OnCLick
import com.example.photoweatherapp.add_weather_story.adapter.WeatherAdapter
import com.example.photoweatherapp.add_weather_story.data_classes.WeatherModel
import com.example.photoweatherapp.add_weather_story.view_model.AddWeatherViewModel
import com.example.photoweatherapp.base.BaseFragment
import com.example.photoweatherapp.commons.ui.CameraCallBack
import com.example.photoweatherapp.commons.ui.ChooseFileFragment
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import java.io.ByteArrayOutputStream

open class HomeFragment : BaseFragment(), CameraCallBack, KodeinAware, OnCLick {


    override val kodein by kodein()

    private val viewModel: AddWeatherViewModel by instance()

    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun bindView(view: View) {
        super.bindView(view)
        viewModel.initHomeFragment()
        weatherAdapter = WeatherAdapter(mutableListOf(), requireContext())
        weatherAdapter.onCLick = this
        initRecyclerview(view)
        handleAddPhotoBtnAction(view)
        handleFabAddPhotoBtnAction(view)
        callWeatherList(view)
    }

    private fun callWeatherList(view: View) {
        val list = viewModel.getWeatherList()
        list?.let {
            if (it.isNotEmpty()) {
                weatherAdapter.addList(it.toMutableList())
                manageDataListView(view)
            } else {
                manageEmptyListView(view)
            }
        }
    }

    private fun manageDataListView(view: View) {
        view.weather_rv.visibility = View.VISIBLE
        view.fab_add_photo.visibility = View.VISIBLE
        view.add_photo_Btn.visibility = View.GONE
    }

    private fun manageEmptyListView(view: View) {
        view.weather_rv.visibility = View.GONE
        view.fab_add_photo.visibility = View.GONE
        view.add_photo_Btn.visibility = View.VISIBLE
    }

    private fun initRecyclerview(view: View) {
        view.weather_rv.adapter = weatherAdapter
        view.weather_rv.layoutManager = LinearLayoutManager(activity)
        view.weather_rv.setHasFixedSize(false)
    }

    private fun handleAddPhotoBtnAction(view: View) {
        view.add_photo_Btn.setOnClickListener {
            manageChooseFileFragment()
        }
    }

    private fun handleFabAddPhotoBtnAction(view: View) {
        view.fab_add_photo.setOnClickListener {
            manageChooseFileFragment()
        }
    }

    private fun manageChooseFileFragment() {
        val fragment =
            ChooseFileFragment.newInstance()
        fragment.listenr = this
        val fm = fragmentManager
        fragment.show(fm!!, "look")
    }

    override fun onCameraClicked() {
        startActivityForResult(
            getFileChooserIntent(),
            IMAGE_PICK_CODE
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK && (requestCode == IMAGE_PICK_CODE || requestCode == PICK_CAMERA)) {
            if (requestCode == PICK_CAMERA) {
                //Check if the size of image is less than 5MB
                //5 MB = 5242880 B
                val tempBitmap = data!!.extras?.get("data") as Bitmap?
                if (!checkAcceptedSizeOfBitmap(tempBitmap))
                    return

                viewModel.imageByteArray.accept(prepareBitmap(data.extras?.get("data") as Bitmap?))

                navigateTo(R.id.PhotoFragment)
            }
        }
    }

    private fun prepareBitmap(bitmap: Bitmap?): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap!!.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        return byteArrayOutputStream.toByteArray()
    }


    private fun getFileChooserIntent(): Intent {
        val mimeTypes =
            arrayOf("image/png", "image/jpg", "image/jpeg", "application/pdf")
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = if (mimeTypes.size == 1) mimeTypes[0] else "*/*"
        if (mimeTypes.isNotEmpty()) {
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        }
        return intent
    }

    override fun onItemClickListener(model: WeatherModel?) {
        model?.let {
            viewModel.weatherModelClicked.accept(it)
            navigateTo(R.id.ShowWeatherItemFragment)
        }

    }
}