package com.example.xymmetryprototype_0


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Upload (val databseRef: DatabaseReference = FirebaseDatabase.getInstance().reference){

    fun uploadImage(post: Post) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userName : String = if (currentUser!!.email != null) currentUser.email.toString() else ""

        if (!userName.equals("")) {
            val time = System.currentTimeMillis()
            databseRef.child("posts").child(time.toString()).setValue(post)
            databseRef.child("users").child(time.toString()).setValue(post)
        }


    }
}