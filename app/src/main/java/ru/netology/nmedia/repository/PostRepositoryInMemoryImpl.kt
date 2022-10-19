package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostRepository

class PostRepositoryInMemoryImpl : PostRepository {
    private var post = listOf(
        Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 октября в 18:00",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов " +
                    "по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, " +
                    "аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков " +
                    "до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в " +
                    "каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать " +
                    "быстрее. Наша миссия — помочь встать на путь роста и начать цепочку " +
                    "перемен → http://netolo.gy/fyb\"",
            like = 999,
            likedByMe = false,
            share = 980,
            views = 999,
            sharedById = false
        ),

        Post(
            id = 2,
            author = "Мир анекдотов",
            published = "3 июля в 11:32",
            content = "Подруга жалуется подруге:\n" +
                    "– Меня преследуют неудачи: стоит только мне познакомиться с хорошим \n" +
                    "человеком, как оказывается, что он женат или я уже замужем.\n" +
                    "\n" +
                    "https://www.anekdotovmir.ru/korotkie-samye-smeshnye-anekdoty/3/\"",
            like = 9,
            likedByMe = false,
            share = 980,
            views = 44,
            sharedById = false
        ),

        Post(
            id = 3,
            author = "Мир анекдотов",
            published = "21 сентября в 22:03",
            content = "– Когда вы заметили что у вас угнали машину?\n" +
                    "– Вчера вечером – вышел из ресторана, открыл дверцу, сел за руль, хочу тронуться \n" +
                    "– смотрю, а машины–то нет!\n" +
                    "\n" +
                    "https://www.anekdotovmir.ru/korotkie-samye-smeshnye-anekdoty/3/\"",
            like = 50,
            likedByMe = false,
            share = 3,
            views = 22,
            sharedById = false
        ),

        Post(
            id = 4,
            author = "Мир анекдотов",
            published = "12 сентября в 09:00",
            content = "Два Новых Русских разговаривают:\n" +
                    "– Я на днях вернулся с Канар, так классно отдохнул, \n " +
                    "а ты где был?– В упор не помню, мля, а пленку еще не проявил.\n" +
                    "\n" +
                    "https://www.anekdotovmir.ru/korotkie-samye-smeshnye-anekdoty/3/\"",
            like = 999,
            likedByMe = false,
            share = 980,
            views = 555,
            sharedById = false
        ),

        Post(
            id = 5,
            author = "Анекдоты.ру",
            published = "16 сентября в 14:00",
            content = "Долго думали с женой, чем бы таким заняться на майские праздники…?\n" +
                    "Сынуля – настоящий мужик – выручил…. из детского сада принёс кишечный вирус….\n" +
                    "Просрали все выходные!!!\n" +
                    "\n" +
                    "© https://anekdoty.ru/korotkie/page/2/\"",
            like = 999,
            likedByMe = false,
            share = 980,
            views = 555,
            sharedById = false
        ),

        Post(
            id = 6,
            author = "Анекдоты.ру",
            published = "3 октября в 14:00",
            content = "- Дорогая! Я вот тут прочитал, что во время секса мужчина сжигает \n " +
                    "столько же калорий, как будто он пробежал 9 километров!\n" +
                    "- Да ты, прям, чемпион мира! 9 км за 2 минуты!!\n" +
                    "\n" +
                    "© https://anekdoty.ru/korotkie/page/2/\"",
            like = 999,
            likedByMe = false,
            share = 980,
            views = 555,
            sharedById = false
        ),

        Post(
            id = 7,
            author = "Анекдоты.ру",
            published = "16 сентября в 14:00",
            content = "– У меня компьютер сам выключается. Что делать? \n" +
                    "– У тебя системный блок внизу стоит? \n" +
                    "– Да.\n" +
                    "– Поменяй носки… Он, походу, сознание теряет.\n" +
                    "\n" +
                    "© https://anekdoty.ru/korotkie/page/2/\"",
            like = 999,
            likedByMe = false,
            share = 980,
            views = 555,
            sharedById = false
        )
    )
    private val data = MutableLiveData(post)
    override fun getAll(): LiveData<List<Post>> = data
    override fun likeById(id: Long) {
        post = post.map { post ->
            if (post.id == id) {
                post.copy(
                    likedByMe = !post.likedByMe,
                    like = if (post.likedByMe) post.like - 1 else post.like + 1
                )
            } else {
                post
            }
        }
        data.value = post
    }

    override fun sharedById(id: Long) {
        post = post.map { post ->
            if (post.id == id) {
                post.copy(
                    share = post.share + 10
                )
            } else {
                post
            }
        }
        data.value = post
    }
}