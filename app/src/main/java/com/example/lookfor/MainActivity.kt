package com.example.lookfor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()


        findViewById<FloatingActionButton>(R.id.btn_pencil).setOnClickListener{
            Log.d("mytag", "글쓰기 버튼 클릭")
            val intent = Intent(this, ChooseMenu::class.java)
            startActivity(intent)
        }

    }
}