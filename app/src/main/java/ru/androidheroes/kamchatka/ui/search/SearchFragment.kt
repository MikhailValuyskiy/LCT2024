package ru.androidheroes.kamchatka.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.feed_header.*
import kotlinx.android.synthetic.main.fragment_search.*
import ru.androidheroes.kamchatka.R
import ru.androidheroes.kamchatka.ui.feed.RouteDetails
import ru.androidheroes.kamchatka.ui.feed.EventItem
import ru.androidheroes.kamchatka.ui.feed.FeedFragment
import ru.androidheroes.kamchatka.ui.feed.FeedFragment.Companion.KEY_ID
import ru.androidheroes.kamchatka.ui.feed.FeedFragment.Companion.KEY_SEARCH
import ru.androidheroes.kamchatka.ui.feed.FeedFragment.Companion.KEY_TITLE
import ru.androidheroes.kamchatka.ui.feed.MainCardContainer
import ru.androidheroes.uikit.search.afterTextChanged
import timber.log.Timber

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchTerm = requireArguments().getString(KEY_SEARCH)
        search_toolbar.setText(searchTerm)

        var eventsList: List<Item>? = null
        eventsList = listOf(
            MainCardContainer(
                R.string.events_title,
                ru.androidheroes.kamchatka.data.MockRepository.findEvents(searchTerm!!)
                    .map {
                        EventItem(it) { movie ->
                            openEventDetails(
                                movie
                            )
                        }
                    }.toList()
            )
        )

        eventsList?.let {list->
            if (list.isNotEmpty()) {
                events_recycler_view.adapter = adapter.apply { addAll(list) }
            }
        }


        search_toolbar.search_edit_text.afterTextChanged {
            Timber.d(it.toString())
            if (it.toString().length > FeedFragment.MIN_LENGTH) {
                eventsList = listOf(
                    MainCardContainer(
                        R.string.events_title,
                        ru.androidheroes.kamchatka.data.MockRepository.findEvents(searchTerm!!)
                            .map {
                                EventItem(it) { movie ->
                                    openEventDetails(
                                        movie
                                    )
                                }
                            }.toList()
                    )
                )

                eventsList?.let {list->
                    if (list.isNotEmpty()) {
                        events_recycler_view.adapter = adapter.apply { addAll(list) }
                    }
                }

                val pro = listOf(
                    MainCardContainer(
                        R.string.projects,
                        ru.androidheroes.kamchatka.data.MockRepository.findEvents(searchTerm!!)
                            .map {
                                EventItem(it) { movie ->
                                    openEventDetails(
                                        movie
                                    )
                                }
                            }.toList()
                    )
                )

                pro.let {list->
                    if (list.isNotEmpty()) {
                        events_recycler_view.adapter = adapter.apply { addAll(list) }
                    }
                }
            }


        }
    }

    private fun openEventDetails(event: RouteDetails) {
        val bundle = Bundle()
        bundle.putString(KEY_TITLE, event.title)
        bundle.putInt(KEY_ID, event.id)
        findNavController().navigate(R.id.event_details_fragment, bundle)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
