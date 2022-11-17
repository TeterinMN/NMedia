package ru.netology.nmedia.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.viewmodel.PostViewModel



class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val viewModel: PostViewModel by viewModels()
    private val interactionListener = object: OnInteractionListener {
        override fun onEdit(post: Post) {viewModel.edit(post) }
        override fun onLike(post: Post){viewModel.likeById(post)}
        override fun onShare(post: Post){viewModel.shareById(post)}
        override fun onRemove(post: Post){viewModel.removeById(post)}
    }
    val adapter = PostsAdapter(interactionListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        subscribe()
        setupListener()
    }
    private fun initView() {
        binding.list.adapter = adapter
    }
    private fun subscribe(){
        viewModel.data.observe(this) {posts ->
            adapter.submitList(posts)
        }
        viewModel.edited.observe(this){posts ->
            if (posts.id != 0L) {
                with(binding.contentEditText) {

                    requestFocus()
                    setText(posts.content)
                }
            }
        }
    }
    private fun setupListener(){
        binding.save.setOnClickListener {
            with(binding.contentEditText) {
                if (text!!.isEmpty()) {
                    Toast.makeText(
                        this@MainActivity,
                        context.getString(R.string.error_empty_content),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.changeContent(text.toString())
                    viewModel.save()
                    setText("")
                    clearFocus()
                    AndroidUtils.hideKeyboard(this)
                }
            }
        }
    }
}