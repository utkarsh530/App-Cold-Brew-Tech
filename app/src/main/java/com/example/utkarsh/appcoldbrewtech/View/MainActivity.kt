package com.example.utkarsh.appcoldbrewtech.View

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.utkarsh.appcoldbrewtech.R

var imageView: Array<ImageView?> = arrayOfNulls(4)
var spinner: Array<ProgressBar?> = arrayOfNulls(4)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindImageView()
        bindProgressBarView()
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
}
