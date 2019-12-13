package com.example.xymmetryprototype_0.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.xymmetryprototype_0.R
import com.example.xymmetryprototype_0.helper.Post

class FeedListAdapter(val listOfPosts: ArrayList<Post?>): RecyclerView.Adapter<FeedListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_post_view, parent, false))

    override fun getItemCount(): Int = listOfPosts.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(listOfPosts[position]!!.imgUrl).into(holder.imageIv)

        holder.captionTv.setText(listOfPosts[position]!!.caption)
    }

    public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageIv: ImageView = itemView.findViewById(R.id.images_iv)
        val captionTv: TextView = itemView.findViewById(R.id.caption_tv)
    }


}