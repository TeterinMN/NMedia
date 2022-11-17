package ru.netology.nmedia.viewmodel

import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun sharedById(id: Long)
    fun save (post: Post)
    fun removeById(id: Long)

}