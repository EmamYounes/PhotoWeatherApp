package com.example.photoweatherapp.base

import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.photoweatherapp.R
import kotlinx.android.synthetic.main.activity_base.*

open class BaseActivity : AppCompatActivity() {


    override fun setContentView(layoutResID: Int) {
        val constraintLayout: ConstraintLayout =
            layoutInflater.inflate(R.layout.activity_base, null) as ConstraintLayout
        val activityContainer: FrameLayout = constraintLayout.findViewById(R.id.layout_container)
        layoutInflater.inflate(layoutResID, activityContainer, true)

        super.setContentView(constraintLayout)
    }

    fun setPageTitle(pageTitle: String) {
        page_title.text = pageTitle
    }

    fun handleBackBtnAction() {
        page_title.setOnClickListener {
            this.onBackPressed()
        }
    }

    fun hideBackBtn() {
        back_btn.visibility = View.GONE
    }

    fun showBackBtn() {
        back_btn.visibility = View.VISIBLE
    }

    fun showAttentionLayout(message: String = "", visible: Boolean, showClose: Boolean = true) {

        if (orange_layout != null) {
            if (visible)
                orange_layout.visibility = View.VISIBLE
            else
                orange_layout.visibility = View.GONE
        }

        if (attention_text != null) {
            attention_text.setContent(message)
            attention_text.setTextMaxLength(80)

            attention_text.setSeeMoreText(
                getString(R.string.read_more),
                getString(R.string.read_less)
            )
        }

        if (closereadmoreimg != null) {
            if (showClose)
                closereadmoreimg.visibility = View.VISIBLE
            else
                closereadmoreimg.visibility = View.GONE

        }

    }

}