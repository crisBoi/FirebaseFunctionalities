package com.example.xymmetryprototype_0


import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.net.URL

class Upload(
    private val databseRef: DatabaseReference = FirebaseDatabase.getInstance().reference,
    private val storage: FirebaseStorage = FirebaseStorage.getInstance()
) {

    fun uploadPost(post: Post, uniqueId: String) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userName: String = if (currentUser!!.email != null) currentUser.email.toString() else ""

        if (!userName.equals("")) {
            val time = System.currentTimeMillis()
            databseRef.child("posts").child(uniqueId).setValue(post)
            databseRef.child("users").child(uniqueId).setValue(post)
        }


    }

     fun uploadImage(post: Post, imageView: ImageView, imgName: String) {
        val storageRef = storage.reference
        val imageRef: StorageReference? = storageRef.child("images").child("posts")


        val spcaeRef = storageRef.child("images/posts/${imgName}.jpg")


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
//            return@continueWith Tasks.whenAllSuccess(task)
        }.addOnCompleteListener { task ->


            if (task.isSuccessful) {

                if (task.isSuccessful) {
                    val downloadUri = task.result

                    if (task.isComplete) {
                        try {
                            val url = downloadUri!!.result
                            post.imgUrl = url.toString()
                            uploadPost(post, imgName)
                        } catch (e: Exception) {

                        }
                    }
                    //URL

                }
            } else {
                //handle failure here
            }
        }



        if (urlTask.isComplete) {
            val url = urlTask.result!!.result
            post.imgUrl = url.toString()
        }
     }


}
