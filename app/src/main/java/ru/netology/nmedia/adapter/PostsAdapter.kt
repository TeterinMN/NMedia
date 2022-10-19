package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Convert
import ru.netology.nmedia.dto.Post


typealias ClickListener = (Post) -> Unit

class PostsAdapter(
    private val likeClickListener: ClickListener,
    private val shareClickListener: ClickListener
) : ListAdapter<Post, PostViewHolder>(PostDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(
            binding = CardPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            likeClickListener = likeClickListener,
            shareClickListener = shareClickListener
        )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val likeClickListener: ClickListener,
    private val shareClickListener: ClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likeCount.text = Convert.longIntToString(post.like)
            shareText.text = Convert.longIntToString(post.share)
            viewsText.text = Convert.longIntToString(post.views)
            if (post.likedByMe) {
                like.setImageResource(R.drawable.ic_like_red)
            } else {
                like.setImageResource(R.drawable.ic_like_gray)
            }
            like.setOnClickListener {
                likeClickListener(post)
            }
            share.setOnClickListener {
                shareClickListener(post)
            }
        }
    }
}

class PostDiffItemCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem == newItem
}