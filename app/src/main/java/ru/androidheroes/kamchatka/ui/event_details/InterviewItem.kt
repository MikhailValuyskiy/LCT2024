package ru.androidheroes.kamchatka.ui.event_details

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.item_with_text_circle.*
import ru.androidheroes.kamchatka.R
import ru.androidheroes.kamchatka.ui.feed.Interview

class InterviewItem(
    private val content: Interview,
    private val onClick: (p: Interview) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_interview

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.name_tv.text = content.name.replace(" ", "\n")
        viewHolder.person_avatar.loadTransformationImage(
            content.imageUrl,
            CropCircleTransformation()
        )

        viewHolder.content.setOnClickListener {
            onClick.invoke(content)
        }
    }
}