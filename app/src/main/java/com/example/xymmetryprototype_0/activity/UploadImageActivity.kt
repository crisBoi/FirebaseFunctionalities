package com.example.xymmetryprototype_0.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_upload_image.*
import com.example.xymmetryprototype_0.firebaseCalls.Login
import com.example.xymmetryprototype_0.helper.Post
import com.example.xymmetryprototype_0.R
import com.example.xymmetryprototype_0.firebaseCalls.Upload


class UploadImageActivity: AppCompatActivity() {



    val SELECT_PIC = 123
    val upload = Upload()
    val login = Login()
    var uri: Uri? = null
    lateinit var selectedImageUri : Uri

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

            val post =
                Post(
                    "imgUrl",
                    caption_text_tv.text.toString()
                )
            val name = login.getCurrentUser() + System.currentTimeMillis().toString()
//            val thread = Thread(Runnable { kotlin.run { upload.uploadImage(post, upload_image_iv, name) } }).start()


//            upload.uploadPost(post, caption_text_tv.text.toString())
//            upload.uploadImage(post, uri a, caption_text_tv.text.toString())

            upload.uploadImage(post, selectedImageUri, caption_text_tv.text.toString())

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELECT_PIC) {
            if (resultCode == Activity.RESULT_OK) {
                selectedImageUri = data!!.data
                val selectedImagePath = selectedImageUri.path

                val post =
                    Post(
                        "imgUrl",
                        caption_text_tv.text.toString()
                    )
                setImage(selectedImageUri)

            }


        }
    }


    private fun setImage(uri: Uri) {
        val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
        upload_image_iv.setImageBitmap(bitmap)
    }



}

