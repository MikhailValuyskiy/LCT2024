package ru.androidheroes.kamchatka.ui.feed

import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_event.*
import ru.androidheroes.kamchatka.R

class NewsEventItem(
    private val content: RouteDetails,
    private val onClick: (goods: RouteDetails) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_event

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.description.text = content.title
        viewHolder.description2.text = content.desc
        viewHolder.date.text = content.date
        viewHolder.like.isChecked = content.isInFavorite
        viewHolder.content.setOnClickListener {
            onClick.invoke(content)
        }

        Picasso.get()
            .load(content.imageUrl)
            .into(viewHolder.image_preview)
    }
}

