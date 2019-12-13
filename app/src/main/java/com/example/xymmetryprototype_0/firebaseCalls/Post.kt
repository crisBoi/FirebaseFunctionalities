package com.example.xymmetryprototype_0.firebaseCalls

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Post(var imgUrl: String? = "", val caption: String? = "")