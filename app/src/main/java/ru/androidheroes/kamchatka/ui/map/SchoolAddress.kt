package ru.androidheroes.kamchatka.ui.map

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_address.*
import ru.androidheroes.kamchatka.R
import ru.androidheroes.kamchatka.data.Warning


class WarningContainer(
    private val content: Warning,
    private val onClick: (a: Warning) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_address

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.title.text = content.title
        viewHolder.address.text = content.routeName
        viewHolder.status.text = content.status

        viewHolder.content.setOnClickListener {
            onClick.invoke(content)
        }

    }
}
