package ru.androidheroes.kamchatka.ui.quiz

import ru.androidheroes.kamchatka.R
import ru.androidheroes.kamchatka.ui.feed.LEVEL

object Constants {
    val USER_NAME: String = "user_name"
    val TOTAL_QUESTIONS: String = "total_questions"
    val SCORE: String = "Ваш счёт: "

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        // 1
        val questionOne = Question(
            1,
            "Кто сочиняет музыку?",
            R.drawable.ic_avatar,
            arrayListOf("дирижёр", "композитор", "концертмейстер", "вокалист"),
            1,
            tip = "Композитор – автор, создатель музыкальных произведений.",
            level = LEVEL.LITE
        )
        questionsList.add(questionOne)

        // 2
        val questionTwo = Question(
            2,
            "В каком городе находится Государственный академический Большой театр России?",
            R.drawable.ic_avatar,
            arrayListOf(
                "Санкт-Петербург", "Екатеринбург",
                "Москва", "Нижний Новгород"
            ),
            2,
            tip = "Государственный академический Большой театр России расположен в Москве по адресу: Театральная площадь, д. 1.",
            level = LEVEL.LITE
        )
        questionsList.add(questionTwo)

        // 3
        val questionThree = Question(
            3,
            "Чем занимаются участники хора?",
            R.drawable.ic_avatar,
            arrayListOf(
                "танцуют", "поют",
                "дирижируют", "играют на струнных музыкальных инструментах"
            ),
            1,
            tip = "Хор – певческий коллектив, исполняющий вокальную музыку.",
            level = LEVEL.LITE
        )
        questionsList.add(questionThree)

        // 4
        val questionFour = Question(
            4,
            "Как называется инструмент, в котором одна из клавиатур напоминает клавиатуру фортепиано?",
            R.drawable.ic_avatar,
            arrayListOf(
                "баян", "бандонеон",
                "аккордеон", "гармонь \"Хромка\""
            ),
            2,
            tip = "Аккордеон – музыкальный инструмент, в котором  правая клавиатура фортепианного типа, то есть, напоминает клавиатуру фортепиано.",
            level = LEVEL.INTERMEDIATE
        )
        questionsList.add(questionFour)

        // 5
        val questionFive = Question(
            5,
            "Сколько струн на виолончели?",
            R.drawable.ic_avatar,
            arrayListOf(
                "4", "5",
                "6", "8"
            ),
            0,
            tip = "Виолончель – струнный смычковый музыкальный инструмент, который имеет 4 струны.",
            level = LEVEL.INTERMEDIATE
        )
        questionsList.add(questionFive)

        // 6
        val questionSix = Question(
            6,
            "Денис Мацуев – ",
            R.drawable.ic_avatar,
            arrayListOf(
                "композитор", "трубач",
                "скрипач", "пианист"
            ),
            3,
            tip = "Денис Мацуев – российский пианист, Народный артист РФ, победитель XI Международного конкурса имени П.И. Чайковского.",
            level = LEVEL.INTERMEDIATE
        )

        questionsList.add(questionSix)

        // 7
        val questionSeven = Question(
            7,
            "Как называется музыкальный спектакль, содержание которого воплощается через музыку и танец?",
            R.drawable.ic_avatar,
            arrayListOf(
                "опера", "балет",
                "симфония", "соната"
            ),
            1,
            tip = "Балет – вид сценического искусства, содержание которого выражается в музыкально-хореографических образах.",
            level = LEVEL.HARD
        )
        questionsList.add(questionSeven)

        // 8
        val questionEight = Question(
            8,
            "Какого музыкального интервала не существует?",
            R.drawable.ic_avatar,
            arrayListOf(
                "прима", "секунда",
                "минута", "октава"
            ),
            2,
            tip = "Музыкальный интервал – расстояние между двумя различными по высоте звуками. Прима, секунда, октава – музыкальные интервалы. Минута не является музыкальным интервалом, минута – единица измерения времени",
            level = LEVEL.HARD
        )
        questionsList.add(questionEight)

        // 9
        val questionNine = Question(
            9,
            "Выберите вариант ответа, где перечислены струнные музыкальные инструменты.",
            R.drawable.ic_avatar,
            arrayListOf(
                "скрипка, контрабас, домра, арфа", "балалайка, гитара, гобой, виолончель",
                "контрабас, виолончель, тромбон, укулеле", "арфа, скрипка, домра, кларнет"
            ),
            0,
            tip = "Скрипка, контрабас, домра, арфа, балалайка, гитара, виолончель, укулеле – струнные музыкальные инструменты. Гобой, тромбон, кларнет – духовые музыкальные инструменты.",
            level = LEVEL.HARD
        )
        questionsList.add(questionNine)

        return questionsList
    }
}