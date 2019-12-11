package com.example.xymmetryprototype_0


import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import java.io.File
import java.lang.Exception
import java.net.URL

class Upload(
    private val databseRef: DatabaseReference = FirebaseDatabase.getInstance().reference,
    private val storage: FirebaseStorage = FirebaseStorage.getInstance()
) {



    fun uploadPost(post: Post, uniqueId: String) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userName: String = if (currentUser!!.email != null) currentUser.email.toString() else ""

        try {
            if (!userName.equals("")) {
//                val time = System.currentTimeMillis()
                databseRef.child("posts").child(uniqueId).setValue(post)
                databseRef.child("users").child(uniqueId).setValue(post)
            }
        } catch (e: Exception) {
            val exception = e.toString()
            Log.d("Exception: ", exception)

        } catch (f: FirebaseException) {
            try {
                throw f
            } catch (e: Exception) {
                val excep = e.toString()
                Log.d("Exception: ", excep)
            }
        }


    }

    fun uploadImage(post: Post, uri: Uri, imgName: String) {
        val storageRef = storage.reference
        val imageRef: StorageReference? = storageRef.child("images").child("posts")


        val spcaeRef = storageRef.child("images/posts/${imgName}.jpg")


        /*imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache()

        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

        val data = baos.toByteArray()*/


//        val uploadTask = spcaeRef.putBytes(data)
        val file = uri

        val uploadTask = spcaeRef.putFile(file)

        uploadTask.addOnFailureListener {

        }.addOnSuccessListener {taskSnapshot: UploadTask.TaskSnapshot? ->

        }



        val urlTask = uploadTask.continueWithTask { task: Task<UploadTask.TaskSnapshot> ->  task
            spcaeRef.downloadUrl
        }.addOnCompleteListener {task ->

            if (task.isComplete) {
                post.imgUrl = task.result.toString()

                uploadPost(post, imgName)
            }
        }


    }



}
