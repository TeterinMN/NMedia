package ru.netology.nmedia

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var like: Long = 0L,
    var likedByMe: Boolean,
    var share: Long = 0L,
    var views: Long = 0L
)
