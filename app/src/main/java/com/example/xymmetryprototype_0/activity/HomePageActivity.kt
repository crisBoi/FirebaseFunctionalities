package com.example.xymmetryprototype_0.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.xymmetryprototype_0.firebaseCalls.Login
import com.example.xymmetryprototype_0.R
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePageActivity : AppCompatActivity(){

    val login = Login()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        listener()
    }

    private fun listener() {
        sign_out_btn.setOnClickListener {
            login.signOut(this)
            finish()
        }

        upload_post_btn.setOnClickListener {
            val intent = Intent(this, UploadImageActivity::class.java)
            startActivity(intent)

        }

        check_feed_btn.setOnClickListener {
            val intent = Intent(this, FeedActivity::class.java)
            startActivity(intent)
        }
    }
}