package com.example.photoweatherapp.commons.ui

import android.app.Dialog
import android.content.Context
import com.example.photoweatherapp.R

class LoadingDialog(context: Context?) {

    var dialog: Dialog? = null

    init {
        dialog = Dialog(context!!, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        dialog!!.setContentView(R.layout.loading_lottie_dialog)
        dialog!!.window?.setBackgroundDrawableResource(R.color.transparent)
        dialog!!.setCancelable(false)
    }

    fun dismiss() {
        if (dialog != null && dialog!!.isShowing)
            dialog!!.dismiss()
    }

    fun show() {
        if (dialog != null && !dialog!!.isShowing)
            dialog!!.show()
    }

    fun isShowing(): Boolean {
        return dialog!!.isShowing
    }
}