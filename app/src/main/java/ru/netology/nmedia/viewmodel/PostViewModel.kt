package ru.netology.nmedia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

private val empty = Post(
    id = 0,
    author = "",
    published = "",
    content = "",
    like = 0,
    likedByMe = false,
    sharedById = false,
    share = 0,
    views = 0
)
class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }
    fun edit(post: Post){
        edited.value = post

    }
    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }
    fun likeById(post: Post) = repository.likeById(post.id)
    fun shareById(post: Post) = repository.sharedById(post.id)
    fun removeById(post: Post) = repository.removeById(post.id)
}