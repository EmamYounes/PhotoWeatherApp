package com.example.photoweatherapp.base

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.example.photoweatherapp.R
import com.example.photoweatherapp.commons.Utilities.Utilities
import com.example.photoweatherapp.commons.ui.LoadingDialog
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.disposables.Disposable

open class BaseFragment : Fragment() {

    private var dialog: LoadingDialog? = null
    protected val IMAGE_PICK_CODE = 1000
    protected val PICK_CAMERA = 0


    val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = LoadingDialog(requireContext())
        bindView(view)
    }

    open fun bindView(view: View) {


    }

    private fun findMyNavController(@NonNull fragment: Fragment): NavController {
        return Navigation.findNavController(fragment.requireView())
    }

    fun showLoading() {
        val baseActivity = activity as BaseActivity

        if (!baseActivity.isFinishing && !baseActivity.isDestroyed) {
            dialog?.show()
        }
    }

    fun dismissLoading() {
        val baseActivity = activity as BaseActivity
        if (!baseActivity.isFinishing && !baseActivity.isDestroyed) {
            dialog?.dismiss()
        }
    }

    fun checkAcceptedSizeOfBitmap(sourceBitmap: Bitmap?): Boolean {
        //5 MB = 5242880 B
        if (sourceBitmap!!.byteCount > 5242880) {
            Toast.makeText(
                requireContext(),
                getString(R.string.check_max_size),
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        return true
    }

    fun navigateTo(destinationId: Int, bundle: Bundle = bundleOf()) {
        findMyNavController(this).navigate(destinationId, bundle, options)
    }

    fun navigateUp() {
        findMyNavController(this).navigateUp()
    }

    fun navigateToHome() {
        findMyNavController(this).popBackStack(R.id.HomeFragment, false)
    }

    fun setInputEditTextFocusListener(
        textInputLayout: TextInputLayout,
        textInputEditText: TextInputEditText,
    ) {

        textInputEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                textInputEditText.background =
                    ContextCompat.getDrawable(
                        view?.context!!, R.drawable.edittext_background
                    )
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                textInputEditText.background =
                    ContextCompat.getDrawable(
                        view?.context!!, R.drawable.edittext_background
                    )
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (
                    textInputEditText.text?.trim().toString().isEmpty()
                ) {
                    textInputLayout.error = getString(R.string.enter_valid_data)
                    textInputEditText.background =
                        ContextCompat.getDrawable(
                            view?.context!!, R.drawable.edittext_background_error
                        )

                } else {
                    textInputLayout.error = null
                    textInputEditText.background =
                        ContextCompat.getDrawable(
                            view?.context!!, R.drawable.edittext_background
                        )
                }
            }
        })

    }

    fun bindEditText(
        edittext: EditText,
        behavior: BehaviorRelay<String?>
    ): Disposable? {
        return Utilities.rxTextViewObservable(edittext)!!
            .subscribe { t ->
                behavior.accept(
                    t.trim().toString()
                )
            }
    }

    internal fun enableSubmitBtn(enable: Boolean, view: Button) {
        view.isEnabled = enable
        if (enable) {
            view.alpha = 1F
        } else {
            view.alpha = 0.4F
        }
    }

    fun setImageByteArray(imageView: ImageView, byteArray: ByteArray) {
        val decodedByte =
            BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        imageView.setImageBitmap(
            decodedByte
        )
    }
}