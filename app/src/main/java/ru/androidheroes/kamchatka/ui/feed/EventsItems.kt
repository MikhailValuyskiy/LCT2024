package ru.androidheroes.kamchatka.ui.feed

import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.item_event.*
import kotlinx.android.synthetic.main.item_event.content
import kotlinx.android.synthetic.main.item_event.date
import kotlinx.android.synthetic.main.item_event.description
import kotlinx.android.synthetic.main.item_event.description2
import kotlinx.android.synthetic.main.item_event.image_preview
import kotlinx.android.synthetic.main.item_with_expert.*
import ru.androidheroes.kamchatka.R
import ru.androidheroes.kamchatka.ui.event_details.loadTransformationImage

class EventItem(
    private val content: RouteDetails,
    private val onClick: (goods: RouteDetails) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_with_text

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.description.text = content.title
        viewHolder.description2.text = content.desc
        viewHolder.date.text = content.date
        viewHolder.movie_rating.rating = 5.0f
        viewHolder.like.isChecked = content.isInFavorite
        viewHolder.content.setOnClickListener {
            onClick.invoke(content)
        }

        Picasso.get()
            .load(content.imageUrl)
            .into(viewHolder.image_preview)
    }
}

class NearEventItem(
    private val content: RouteDetails,
    private val onClick: (goods: RouteDetails) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_with_text_horizontal

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.description.text = content.title
        viewHolder.movie_rating.rating = 5.0f
        viewHolder.like.isChecked = content.isInFavorite
        viewHolder.content.setOnClickListener {
            onClick.invoke(content)
        }

        Picasso.get()
            .load(content.imageUrl)
            .into(viewHolder.image_preview)
    }
}

class ExpertArticleItem(
    private val content: RouteDetails,
    private val onClick: (goods: RouteDetails) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_with_expert

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val person = ru.androidheroes.kamchatka.data.MockRepository.getPeople().shuffled()[0]
        viewHolder.description.text = content.title
        viewHolder.description2.text = ""
        viewHolder.date.text = content.date
        viewHolder.movie_rating.rating = 5.0f
        viewHolder.content.setOnClickListener {
            onClick.invoke(content)
        }

        Picasso.get()
            .load(content.imageUrl)
            .into(viewHolder.image_preview)

        viewHolder.person_avatar.loadTransformationImage(
            person.imageUrl,
            CropCircleTransformation()
        )
    }
}

