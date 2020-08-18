package com.example.newsapp.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article (
    val title:String,
    var author:String?="",
    val urlToImage:String?="",
    val description:String?="",
    val publishedAt:String,
    val content:String?=""): Parcelable {
}

