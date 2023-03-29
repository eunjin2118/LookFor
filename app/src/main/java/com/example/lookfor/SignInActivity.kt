package com.example.lookfor

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        supportActionBar?.hide()
        auth = Firebase.auth

        val signInBtn = findViewById<Button>(R.id.sign_in_btn)

        // 로그인 된 사용자가 있는 경우
        //val currentUser = auth.currentUser

        signInBtn.setOnClickListener {
            val email = findViewById<EditText>(R.id.email_input).text.toString()
            val password = findViewById<EditText>(R.id.password_input).text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        Log.d("mytag", "로그인 성공 ${it.toString()}")
                        Toast.makeText(baseContext, "로그인 셩굥", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Log.w("mytag", "로그인 실패 (사유 : ${it.exception})")
                        Toast.makeText(baseContext, "로그인 실패", Toast.LENGTH_SHORT).show()

                        signInBtn.isEnabled = true
                    }
                }
        }

        val signUpBtn = findViewById<Button>(R.id.siqn_up_btn)

        signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}