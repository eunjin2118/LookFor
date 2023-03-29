package com.example.lookfor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = Firebase.auth

        val db = Firebase.firestore
        val signUpBtn = findViewById<Button>(R.id.siqn_up_btn)

        signUpBtn.setOnClickListener {
            val email = findViewById<EditText>(R.id.email_input).text.toString()
            val password = findViewById<EditText>(R.id.password_input).text.toString()
            val nickname = findViewById<EditText>(R.id.nickname_input).text.toString()

            val user = hashMapOf(
                "email" to email,
                "nickname" to nickname
            )

            db.collection("users").document(email)
                .set(user)
                .addOnCanceledListener {
                    Log.d("mytag", "DocumentSnapshot successfully written!")
                }
                .addOnFailureListener {
                        e -> Log.d("mytag", "Error writing document", e)
                }

            signUpBtn.isEnabled = false
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this, "회원가입 되었습니다", Toast.LENGTH_SHORT).show()
                        val cu = auth.currentUser
                        Log.d("mytag", "회원가입 성공 ${cu.toString()}")
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Log.w("mytag", "회원 가입 실패 (사유 : ${it.exception})")
                        Toast.makeText(baseContext, "회원 가입 실패", Toast.LENGTH_SHORT).show()
                        signUpBtn.isEnabled = true // 버튼 다시 누르기
                    }
                }
        }



        supportActionBar?.hide()


    }
}