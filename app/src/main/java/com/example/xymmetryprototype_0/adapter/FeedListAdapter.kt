package com.example.xymmetryprototype_0.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xymmetryprototype_0.R
import com.example.xymmetryprototype_0.firebaseCalls.Post

class FeedListAdapter(val listOfPosts: List<Post>): RecyclerView.Adapter<FeedListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_post_view, parent, false))

    override fun getItemCount(): Int = listOfPosts.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}


}