package com.example.xymmetryprototype_0.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xymmetryprototype_0.R
import com.example.xymmetryprototype_0.adapter.FeedListAdapter
import com.example.xymmetryprototype_0.helper.Post
import com.example.xymmetryprototype_0.firebaseCalls.Upload
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_check_feed.*

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
                Toast.makeText(this@FeedActivity, "Connection problem", Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(p0: DataSnapshot) {

                if (listOfPosts.size != 0) listOfPosts.clear()

                for (dataSnap in p0.children) {
                    val post: Post? = dataSnap.getValue(Post::class.java)
                        listOfPosts.add(post)
                }

                setAdapter()
            }
            
        }

        reference.addValueEventListener(readListener)

    }


    fun setAdapter() {
        feed_list_rv.layoutManager = LinearLayoutManager(this)
        feed_list_rv.adapter = FeedListAdapter(listOfPosts)
    }

}