package com.example.xymmetryprototype_0.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.xymmetryprototype_0.AllPosts
import com.example.xymmetryprototype_0.R
import com.example.xymmetryprototype_0.firebaseCalls.Post
import com.example.xymmetryprototype_0.firebaseCalls.Upload
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class FeedActivity : AppCompatActivity() {

    val read = Upload()
    lateinit var reference : DatabaseReference
    lateinit var listOfPosts: ArrayList<Post?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_feed)

        reference = read.dataChangeListener()
        readData()
    }

    fun readData() {

        listOfPosts = ArrayList()

        val readListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {

                for (dataSnap in p0.children) {
                    val post: Post? = dataSnap.getValue(Post::class.java)
                        listOfPosts.add(post)
                }


                Toast.makeText(this@FeedActivity, "caption: " + listOfPosts[0]!!.caption + "\nurl: " + listOfPosts[0]!!.imgUrl, Toast.LENGTH_LONG).show()
            }
        }

        reference.addValueEventListener(readListener)

    }




}