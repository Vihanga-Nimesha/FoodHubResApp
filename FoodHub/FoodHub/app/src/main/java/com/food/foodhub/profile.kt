package com.food.foodhub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        var actionBar = supportActionBar
        actionBar?.hide()


        val cartbtn = findViewById<ImageButton>(R.id.cartbtn)
        cartbtn.setOnClickListener {
            val intent = Intent(this, cart::class.java)
            startActivity(intent)
        }

            val likebtn = findViewById<ImageButton>(R.id.likebtn)
            likebtn.setOnClickListener {
                val intent = Intent(this, Like::class.java)
                startActivity(intent)


        }

        val editprofilebtn = findViewById<ImageButton>(R.id.editprofilebtn)
        editprofilebtn.setOnClickListener {
            val intent = Intent(this, EditProfie::class.java)
            startActivity(intent)
        }
    }
}