package com.example.my.randommaizer

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.my.randommaizer.name_number.Name

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageButtonName = findViewById<ImageButton>(R.id.start_button_name)
        imageButtonName.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.button_click)
            imageButtonName.startAnimation(animation)
            val intent = Intent(this, Name::class.java)
            startActivity(intent)

        }
        val imageButtonNumber = findViewById<ImageButton>(R.id.start_button_number)
        imageButtonNumber.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.button_click)
            imageButtonNumber.startAnimation(animation)
            val intent = Intent(this, Number::class.java)
            startActivity(intent)
        }
    }

}