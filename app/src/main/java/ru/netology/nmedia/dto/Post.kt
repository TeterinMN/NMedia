package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var like: Long,
    val likedByMe: Boolean,
    var share: Long,
    var views: Long
)
