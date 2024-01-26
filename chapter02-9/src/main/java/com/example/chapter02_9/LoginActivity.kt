package com.example.chapter02_9

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chapter02_9.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            // 로그인 실패
            showErrorToast()
            error.printStackTrace()
        } else if (token != null) {
            // 로그인 성공
            getKakaoAccountInfo()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        KakaoSdk.init(this, "8c2bb2fbf2ab9ac07c289b3b760fadc1")

        binding.kakaoTalkLoginButton.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                // 카카오톡 로그인
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    if (error != null) {
                        // 카카오톡 로그인 실패
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                    } else if (token != null) {
                        if (Firebase.auth.currentUser == null) {
                            // 카카오톡에서 정보를 가져와서 파이어베이스 로그인
                            getKakaoAccountInfo()
                        } else {
                            navigateToMapActivity()
                        }
                        Log.e("태그", token.toString())
                    }
                }
            } else {
                // 카카오계정 로그인
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }

    private fun showErrorToast() {
        Toast.makeText(applicationContext, "사용자 로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun getKakaoAccountInfo() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                showErrorToast()
                error.printStackTrace()
            } else if (user != null) {
                // 사용자 정보 요청 성공
                Log.e(
                    "태그",
                    "${user.id}, ${user.kakaoAccount?.email}, ${user.kakaoAccount?.profile?.nickname}, ${user.kakaoAccount?.profile?.profileImageUrl}"
                )

                checkKakaoUserData(user)
            }
        }
    }

    private fun checkKakaoUserData(user: User) {
        val kakaoEmail = user.kakaoAccount?.email.orEmpty()

        if (kakaoEmail.isEmpty()) {
            // TODO 추가로 이메일을 받는 작업
            return
        }

        signInFirebase(user, kakaoEmail)
    }

    private fun signInFirebase(user: User, email: String) {
        val uid = user.id.toString()

        Firebase.auth.createUserWithEmailAndPassword(email, uid)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    updateFirebaseDatabase(user)
                } else {
                    showErrorToast()
                }
            }
            .addOnFailureListener {
                if (it is FirebaseAuthUserCollisionException) {
                    Firebase.auth.signInWithEmailAndPassword(email, uid)
                        .addOnCompleteListener { result ->
                            if (result.isSuccessful) {

                            } else {
                                showErrorToast()
                            }
                        }
                        .addOnFailureListener { error ->
                            error.printStackTrace()
                            showErrorToast()
                        }
                } else {
                    showErrorToast()
                }
            }
    }

    private fun updateFirebaseDatabase(user: User) {
        val uid = Firebase.auth.currentUser?.uid.orEmpty()

        val personMap = mutableMapOf<String, Any>()
        personMap["uid"] = uid
        personMap["name"] = user.kakaoAccount?.profile?.nickname.orEmpty()
        personMap["profilePhoto"] = user.kakaoAccount?.profile?.thumbnailImageUrl.orEmpty()

        Firebase.database.reference.child("Person").child(uid).updateChildren(personMap)

        navigateToMapActivity()
    }

    private fun navigateToMapActivity() {
        startActivity(Intent(this, MapActivity::class.java))
    }
}