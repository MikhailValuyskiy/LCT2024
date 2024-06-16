package ru.androidheroes.kamchatka.ui.feed

import android.view.View
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_preview.*
import ru.androidheroes.kamchatka.R

// Налычево = 1
data class RouteDetails(
    // Уникальный ID мероприятия
    var id: Int,
    // Название мероприятия
    val title: String,
    // Краткое описание
    val desc: String = "",
    // Дата проведения
    val date: String = "",
    // Фотография обложки
    val imageUrl: String,
    val webUrl: String,
    var isInFavorite: Boolean = false,
    // Полное описание
    val full_description: String = "",
    val distance: String = "",
    val passportUrl: String = "",
    val routeType: String = "",
    var routeId: Int=0,
    var kmlUrl:String? = "https://trekkingmania.ru/assets/files/kml/nalyuchevo.kml",
    var workLoad:String = "Средняя",
    var season:String = "Лето-Осень",
    var level:String = "Некатегорийный"
)

data class EducationDetails(
    // Уникальный ID задания
    var id: Int,
    // Название задания
    val title: String,
    // Краткое описание
    val desc: String = "",
    // Фотография обложки
    val imageUrl: String,
    val level: LEVEL
)

enum class LEVEL(val level: String) {
    LITE("Лёгкий"),
    INTERMEDIATE("Средний"),
    HARD("Сложный")
}


data class Interview(
    val name: String,
    val bio: String,
    val imageUrl: String,
    val url: String
)

data class Person(
    var id: Int,
    val name: String,
    val age: Int,
    val bio: String,
    val status: String,
    val imageUrl: String
)

data class Project(
    var id: Int,
    val title: String,
    val category: String,
    val desc: String,
    val imageUrl: String,
    val image: Int? = null,
    val ownerId: Int
)

class PreviewItem(
    private val eventInfo: RouteDetails,
    private val onClick: (RouteDetails) -> Unit
) : Item() {
    override fun getLayout() = R.layout.item_preview

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.content.setOnClickListener {
            onClick.invoke(eventInfo)
        }
        viewHolder.stock_name.text = eventInfo.title
        viewHolder.stock_code.text = "TODO"
        viewHolder.percent_in_portfolio_value.text = eventInfo.date

        viewHolder.date_value.text = "TODO"
        viewHolder.date_value.visibility = View.VISIBLE

        if (!eventInfo.imageUrl.isNullOrEmpty()) {
            Picasso.get().load(eventInfo.imageUrl).into(viewHolder.image_preview2)
        }

    }
}

