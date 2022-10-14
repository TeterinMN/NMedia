package ru.netology.nmedia

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var like: Int = 0,
    var likedByMe: Boolean
)
