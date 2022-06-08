package com.example.galleryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class splashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      val time : Long = 100
      Handler().postDelayed({
            val intent = Intent(splashScreen@this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },time)

    }
}