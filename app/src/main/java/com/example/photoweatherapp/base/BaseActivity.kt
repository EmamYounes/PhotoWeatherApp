package com.example.photoweatherapp.base

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.navOptions
import com.example.photoweatherapp.R
import kotlinx.android.synthetic.main.activity_base.*

open class BaseActivity : AppCompatActivity() {

    val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    override fun setContentView(layoutResID: Int) {
        val constraintLayout: ConstraintLayout =
            layoutInflater.inflate(R.layout.activity_base, null) as ConstraintLayout
        val activityContainer: FrameLayout = constraintLayout.findViewById(R.id.layout_container)
        layoutInflater.inflate(layoutResID, activityContainer, true)

        super.setContentView(constraintLayout)
    }

    fun showAttentionLayout(message: String, visible: Boolean, showClose: Boolean = true) {

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