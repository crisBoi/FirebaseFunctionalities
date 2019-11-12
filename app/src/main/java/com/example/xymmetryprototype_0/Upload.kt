package com.example.xymmetryprototype_0


import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream
import java.net.URL

class Upload(
    private val databseRef: DatabaseReference = FirebaseDatabase.getInstance().reference,
    private val storage: FirebaseStorage = FirebaseStorage.getInstance()
) {

    fun uploadPost(post: Post) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userName: String = if (currentUser!!.email != null) currentUser.email.toString() else ""

        if (!userName.equals("")) {
            val time = System.currentTimeMillis()
            databseRef.child("posts").child(time.toString()).setValue(post)
            databseRef.child("users").child(time.toString()).setValue(post)
        }


    }

    fun uploadImage(post: Post, imageView: ImageView, imgName: String) {
        val storageRef = storage.reference
        val imageRef: StorageReference? = storageRef.child("images").child("posts")

        val spcaeRef = storageRef.child("images/posts/ioi.jpg")


        imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache()

        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

        val data = baos.toByteArray()

        val uploadTask = spcaeRef.putBytes(data)

        uploadTask.addOnFailureListener {

        }.addOnSuccessListener {

        }


        val urlTask = uploadTask.continueWith { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }

            spcaeRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result

            } else {
                //handle failure here
            }
        }

        



    }
}