package ru.androidheroes.kamchatka.ui.watchlist

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.dialog_details.*
import ru.androidheroes.kamchatka.R
import ru.androidheroes.kamchatka.data.Warning

class ConfirmationDialog(
    private val warning: Warning,
    context: Context
) :
    BottomSheetDialog(context, R.style.TransparentBottomSheet) {

    override fun onCreate(savedInstanceState: Bundle?) {
        val contentView = View.inflate(context, R.layout.dialog_details, null)
        setContentView(contentView)
        super.onCreate(savedInstanceState)

        tv_bottom_sheet_heading.text = warning.title

        warning_address.text =  Html.fromHtml("Маршрут: <b>${warning.routeName}</b>")
        warning_title.text =  Html.fromHtml("Email: <b>${warning.title}</b>")
        warning_status.text =  Html.fromHtml("Статус: <b>${warning.status}</b>")
    }

    override fun onStart() {
        super.onStart()
    }

    companion object {
        fun newInstance(address: Warning, context: Context): ConfirmationDialog {
            val dialog = ConfirmationDialog(address, context)
            dialog.show()
            return dialog
        }
    }
}