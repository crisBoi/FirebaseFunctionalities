package com.example.xymmetryprototype_0

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_upload_image.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class UploadImageActivity: AppCompatActivity() {

    val SELECT_PIC = 123
    val upload = Upload()
    val login = Login()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_image)

        setOnClickListener()
    }

    private fun setOnClickListener() {
        upload_image_iv.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT

            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PIC)
        }

        upload_btn.setOnClickListener {

            val post = Post("imgUrl", caption_text_tv.text.toString())
            val name = login.getCurrentUser() + System.currentTimeMillis().toString()
            val thread = Thread(Runnable { kotlin.run { upload.uploadImage(post, upload_image_iv, name) } }).start()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELECT_PIC) {
            if (resultCode == Activity.RESULT_OK) {
                val selectedImageUri : Uri = data!!.data
                val selectedImagePath = selectedImageUri.path

                setImage(selectedImageUri)
            }
        }
    }


    private fun setImage(uri: Uri) {
        val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
        upload_image_iv.setImageBitmap(bitmap)
    }
}

