package com.example.chapter02_6

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chapter02_6.Key.Companion.DB_USERS
import com.example.chapter02_6.databinding.ActivityLoginBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(applicationContext, "이메일 또는 패스워드가 입력되지 않았습니다.", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            Firebase.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(applicationContext, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(applicationContext, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT)
                            .show()
                        // Log.w("태그", "createUserWithEmail:failure", task.exception)
                    }
                }
        }

        binding.signInButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(applicationContext, "이메일 또는 패스워드가 입력되지 않았습니다.", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            Firebase.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    val currentUser = Firebase.auth.currentUser
                    if (task.isSuccessful && currentUser != null) {
                        val userId = currentUser.uid

                        Firebase.messaging.token.addOnCompleteListener {
                            val token = it.result
                            val user = mutableMapOf<String, Any>()
                            user["userId"] = userId
                            user["username"] = email
                            user["fcmToken"] = token

                            // Firebase.database(DB_URL)
                            Firebase.database.reference.child(DB_USERS).child(userId)
                                .updateChildren(user)

                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                    } else {
                        Log.e("태그", task.exception.toString())
                        Toast.makeText(applicationContext, "로그인에 실패했습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }
    }
}