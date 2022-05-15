package com.food.foodhub

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class EditProfie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profie)


        var actionBar = supportActionBar
        actionBar?.hide()

        val userimg = findViewById<ImageButton>(R.id.userimg)
        userimg.setOnClickListener{

            var intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,0)

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if(requestCode==0 && resultCode == Activity.RESULT_OK && data != null){

            val uri = data.data
            val bitmap=MediaStore.Images.Media.getBitmap(contentResolver,uri)
            val bitmapDrawable =BitmapDrawable(bitmap)
            val userimg =findViewById<ImageButton>(R.id.userimg)
            userimg.setBackgroundDrawable(bitmapDrawable)
        }
    }
}