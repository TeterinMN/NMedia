package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Convert
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    private val viewModel: PostViewModel by viewModels()

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        subsribe()
        setupListeners()
    }

    private fun subsribe() {
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likeCount.text = Convert.longIntToString(post.like)
                shareText.text = Convert.longIntToString(post.share)
                viewsText.text = Convert.longIntToString(post.views)
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_like_red else R.drawable.ic_like_gray
                )

            }
        }
    }

    private fun setupListeners() {
        binding.like.setOnClickListener {
            viewModel.like()
        }
        binding.share.setOnClickListener {
            viewModel.share()
        }
    }


}

