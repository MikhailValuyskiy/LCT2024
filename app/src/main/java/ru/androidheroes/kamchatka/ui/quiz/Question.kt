package ru.androidheroes.kamchatka.ui.quiz

import ru.androidheroes.kamchatka.ui.feed.LEVEL


data class Question(
    val id: Int,
    val questionText: String,
    val image: Int,
    val alternatives: ArrayList<String>,
    val correctAnswerIndex: Int,
    val tip:String = "",
    val level:LEVEL = LEVEL.LITE
)
