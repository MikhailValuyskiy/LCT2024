package ru.androidheroes.kamchatka.ui.feed

import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_with_text.content
import kotlinx.android.synthetic.main.item_with_text.description
import kotlinx.android.synthetic.main.item_with_text.description2
import kotlinx.android.synthetic.main.item_with_text.image_preview
import kotlinx.android.synthetic.main.item_with_text.like
import kotlinx.android.synthetic.main.project_item.*
import ru.androidheroes.kamchatka.R

class RouteItem(
    private val content: RouteDetails,
    private val onClick: (goods: RouteDetails) -> Unit
) : Item() {

    override fun getLayout() = R.layout.project_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.description.text = content.title
        viewHolder.like.isChecked = content.isInFavorite
        viewHolder.description2.text = content.full_description
        viewHolder.category_text.text = content.distance
        viewHolder.content.setOnClickListener {
            onClick.invoke(content)
        }

        Picasso.get()
            .load(content.imageUrl)
            .into(viewHolder.image_preview)
    }
}

