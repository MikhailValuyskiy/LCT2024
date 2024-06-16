package ru.androidheroes.kamchatka.ui.education

import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.education_item.*
import ru.androidheroes.kamchatka.R
import ru.androidheroes.kamchatka.ui.feed.EducationDetails

class EducationItem(
    private val content: EducationDetails,
    private val onClick: (item: EducationDetails) -> Unit
) : Item() {

    override fun getLayout() = R.layout.education_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.description.text = content.title
        viewHolder.description2.text = content.desc
        viewHolder.category_text.text = "Уровень: " + content.level.level

        Picasso.get()
            .load(content.imageUrl)
            .into(viewHolder.image_preview)

        viewHolder.content.setOnClickListener {
            onClick.invoke(content)
        }

    }
}