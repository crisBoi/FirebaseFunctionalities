package com.example.xymmetryprototype_0

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class LoginPageActivity : AppCompatActivity() {

    val login : Login = Login()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkCurrentUser()
        onClickListener()
    }

    private fun checkCurrentUser() {
        val user = login.getCurrentUser()
        if (user == null) Toast.makeText(this, "Please login or sign up", Toast.LENGTH_LONG).show()
        else {
            openHomePage()
        }
    }

    private fun onClickListener() {
        login_btn.setOnClickListener {

        }

        sign_up_btn.setOnClickListener {

        }

        sign_in_with_google_btn.setOnClickListener {
            startActivityForResult(login.signInWithGoogle(), login.RC_SIGN_IN)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (login.validateGoogleSignIn(requestCode, resultCode)) {
            val user = login.getCurrentUser()
            Toast.makeText(this, "Welcome ${user} !", Toast.LENGTH_LONG).show()
            openHomePage()
        }
        else {
            Toast.makeText(this, "Sign in failed!", Toast.LENGTH_LONG).show()
        }

    }

    private fun openHomePage() {
        val intent = Intent(this, HomePageActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}
