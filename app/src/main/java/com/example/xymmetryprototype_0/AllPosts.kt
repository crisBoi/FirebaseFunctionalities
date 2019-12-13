package com.example.xymmetryprototype_0

import com.example.xymmetryprototype_0.firebaseCalls.Post
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class AllPosts(var listOfPosts: List<Post> = listOf()) {
}