package com.food.foodhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class cart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        var actionBar = supportActionBar
        actionBar?.hide()

    }
}