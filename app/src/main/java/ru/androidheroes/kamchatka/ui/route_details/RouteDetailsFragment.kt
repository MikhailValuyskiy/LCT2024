package ru.androidheroes.kamchatka.ui.route_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.route_details_fragment.*
import ru.androidheroes.kamchatka.R
import ru.androidheroes.kamchatka.ui.feed.*
import ru.androidheroes.kamchatka.ui.feed.FeedFragment.Companion.KEY_ID

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class RouteDetailsFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var eventId: Int? = null

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            eventId = it.getInt(KEY_ID)
        }
    }

    private val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.route_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val event =
            ru.androidheroes.kamchatka.data.MockRepository.getRoutes()
                .find { it.routeId == eventId }

        map_button.setOnClickListener {
            openMap(event!!)
        }

        title_tv.text = event?.title
        description_tv.text = event?.full_description
        work_load_value.text = event?.workLoad
        route_category_value.text = event?.level
        season_value.text = event?.season

        Picasso.get()
            .load(event?.imageUrl)
            .into(image_detail_preview)
    }

    private fun openMap(event: RouteDetails) {
        val bundle = Bundle()
        bundle.putString(FeedFragment.KEY_TITLE, event.title)
        bundle.putInt(FeedFragment.KEY_ID, event.routeId)
        findNavController().navigate(R.id.route_map_fragment, bundle, options)
    }

    override fun onResume() {
        super.onResume()
    }

    private fun openWebView(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, url.toUri())
        ContextCompat.startActivity(requireContext(), browserIntent, null)
    }

    fun saveKey(key: String, value: String) {
        val sharedPref = activity?.getSharedPreferences(
            "my_preffs", Context.MODE_PRIVATE
        )
        with(sharedPref!!.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun getKey(key: String): String? {
        val sharedPref = activity?.getSharedPreferences(
            "my_preffs", Context.MODE_PRIVATE
        )

        return sharedPref?.getString(key, "empty")
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RouteDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }
}
