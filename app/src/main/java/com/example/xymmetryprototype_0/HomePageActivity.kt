package com.example.xymmetryprototype_0

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
    }
}