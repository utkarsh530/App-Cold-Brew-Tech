package com.example.utkarsh.appcoldbrewtech.View

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.example.utkarsh.appcoldbrewtech.Model.Image
import com.example.utkarsh.appcoldbrewtech.R
import com.example.utkarsh.appcoldbrewtech.ViewModel.ViewModel


class MainActivity : AppCompatActivity() {

    private var imageView: Array<ImageView?> = arrayOfNulls(4)
    private var spinner: Array<ProgressBar?> = arrayOfNulls(4)
    private var viewModel: ViewModel = ViewModel()
    private var message: Array<String?> = arrayOfNulls(4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindImageView()
        bindProgressBarView()
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        val imageObserver = Observer<Array<Image?>> { it ->
            // Update the UI, in this case, a TextView.
            for (i in 0..3) {
                spinner[i]?.visibility = View.GONE
                it!![i]!!.bmp = Bitmap.createScaledBitmap(
                    it!![i]!!.bmp!!, width / 2,
                    height / 2, true
                )
                imageView[i]?.setImageBitmap(it!![i]!!.bmp)
                message[i] = it[i]!!.message
            }
        }
        viewModel.NetworkCall().observe(this, imageObserver)
        tooltipInflater(message, height, width)
    }

    fun bindImageView() {
        imageView[0] = findViewById(R.id.imageView1)
        imageView[1] = findViewById(R.id.imageView2)
        imageView[2] = findViewById(R.id.imageView3)
        imageView[3] = findViewById(R.id.imageView4)
    }

    fun bindProgressBarView() {

        spinner[0] = findViewById(R.id.progressBar1)
        spinner[1] = findViewById(R.id.progressBar2)
        spinner[2] = findViewById(R.id.progressBar3)
        spinner[3] = findViewById(R.id.progressBar4)

        for (i in 0..3) {
            spinner[i]?.visibility = View.VISIBLE
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun tooltipInflater(message: Array<String?>, height: Int, width: Int) {
        for (i in 0..3) {
            imageView[i]?.setOnTouchListener { v, event ->
                val x = event.rawX.toInt()
                val y = event.rawY.toInt()
                val toast = Toast.makeText(
                    applicationContext,
                    message[i], Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.START or Gravity.TOP, x, y)
                toast.show()
                true
            }
        }
    }
}
