package com.example.photoweatherapp.commons.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.example.photoweatherapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_choose_picker.view.*

class ChooseFileFragment() : BottomSheetDialogFragment() {

    var listenr: CameraCallBack? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_picker, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        with(dialog) {
            window?.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return dialog
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            with(dialog) {
                window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleCameraPickerAction(view)
    }

    private fun handleCameraPickerAction(view: View) {
        view.camera_picker_tv.setOnClickListener {
            listenr!!.onCameraClicked()
            dismiss()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ChooseFileFragment()

    }

}
