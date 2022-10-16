package ru.netology.nmedia

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов " +
                    "по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, " +
                    "аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков " +
                    "до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в " +
                    "каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать " +
                    "быстрее. Наша миссия — помочь встать на путь роста и начать цепочку " +
                    "перемен → http://netolo.gy/fyb\"",
            like = 999,
            likedByMe = false,
            share = 999,
            views = 0
        )
        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likeCount.text = post.like.toString()

            like.setOnClickListener {
                post.likedByMe = !post.likedByMe
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_like_red else R.drawable.ic_like_gray
                )
                if (post.likedByMe) post.like++ else post.like--
                likeCount.text = longIntToString(post.like)
            }

            shareText.text = post.share.toString()

            share.setOnClickListener {
                post.share = post.share + 10
                shareText.text = longIntToString(post.share)
            }
        }


    }
}

