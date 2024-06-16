package ru.androidheroes.kamchatka.ui.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RawRes
import kotlinx.android.synthetic.main.fragment_splash_lottie.*
import ru.androidheroes.kamchatka.R

private const val ARG_PARAM1 = "imageId"
private const val ARG_PARAM2 = "title"
private const val ARG_PARAM3 = "subtitle"

class LottieSplashFragment : androidx.fragment.app.Fragment() {

    private var param1: Int? = null
    private var title: String? = null
    private var subTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            title = it.getString(ARG_PARAM2)
            subTitle = it.getString(ARG_PARAM3)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_splash_lottie, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title_text.text= this.title
        subtitle.text = this.subTitle
        loti_animation.setAnimation(this.param1!!)
    }

    companion object {

        @JvmStatic
        fun newInstance(@RawRes imageId: Int, title: String, subtitle: String) =
            LottieSplashFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, imageId)
                    putString(ARG_PARAM2, title)
                    putString(ARG_PARAM3, subtitle)
                }
            }
    }
}
