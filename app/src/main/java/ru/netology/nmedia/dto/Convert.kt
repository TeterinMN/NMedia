package ru.netology.nmedia.dto

object Convert {
    fun longIntToString(x: Long): String {
        when {
            x in 1000..9999 -> return "1." + (x % 1000 / 100).toString() + "K"
            x in 10000..999999 ->
                return (x / 10000).toString() + "." + (x % 10000 / 1000).toString() + "K"
            x >= 1000000 ->
                return (x / 1000000).toString() + "." + (x % 1000000 / 100000).toString() + "M"
        }
        return x.toString()
    }
}