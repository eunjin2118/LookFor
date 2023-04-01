package com.example.lookfor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class ChooseMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_menu)
        supportActionBar?.hide()

        val humanWriteFragment = HumanWriteFragment()

        val btn_human = findViewById<Button>(R.id.btn_human)

        btn_human.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.human_fragment, humanWriteFragment)
                .commit()

            Log.d("mytqg","사람버튼 클릭")
        }


    }


}