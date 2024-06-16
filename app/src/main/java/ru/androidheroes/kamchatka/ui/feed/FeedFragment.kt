package ru.androidheroes.kamchatka.ui.feed

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.android.synthetic.main.feed_header.*
import ru.androidheroes.kamchatka.R
import ru.androidheroes.kamchatka.ui.afterTextChanged
import timber.log.Timber


class FeedFragment : Fragment() {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    private lateinit var viewModel: FeedViewModel

    private val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.feed_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(FeedViewModel::class.java)

        user_profile.setOnClickListener {
            findNavController().navigate(R.id.profile_fragment)

        }

        events_recycler_view.layoutManager = LinearLayoutManager(context)
        events_recycler_view.adapter = adapter.apply { addAll(listOf()) }

        search_toolbar.search_edit_text.afterTextChanged {
            Timber.d(it.toString())
            if (it.toString().length > MIN_LENGTH) {
                openSearch(it.toString())
            }
        }


        val routesObserver = Observer<List<Interview>> { it ->

            val routes = ru.androidheroes.kamchatka.data.MockRepository.getRoutes()
                .map {
                    RouteItem(it) { route ->
                        openRouteDetails(route)
                    }
                }
                .toList()

            var routesList: List<Item>? = null

            routesList = listOf(
                MainCardContainer(
                    R.string.routes_header,
                    routes
                )
            )

            adapter.apply { addAll(routesList) }
        }

        viewModel.interviewsLiveData.observe(viewLifecycleOwner, routesObserver)


        val eventsObserver = Observer<List<RouteDetails>> { it ->
            var eventsList: List<Item>? = null
            eventsList = listOf(
                MainCardContainer(
                    R.string.parks,
                    ru.androidheroes.kamchatka.data.MockRepository.getEvents().map {
                        NewsEventItem(it) { park ->
                            openEventDetails(
                                park
                            )
                        }
                    }.toList()
                )
            )


            adapter.apply { addAll(eventsList) }
        }

        viewModel.eventsLiveData.observe(viewLifecycleOwner, eventsObserver)


        val nearEventsObserver = Observer<List<RouteDetails>> { it ->
            val nearYouEvents = listOf(
                MainCardContainer(
                    R.string.near_you,
                    it
                        .map {
                            NearEventItem(it) { movie ->
                                openEventDetails(movie)
                            }
                        }.toList()
                )
            )

            adapter.apply { addAll(nearYouEvents) }
        }

        viewModel.nearEventsLiveData.observe(viewLifecycleOwner, nearEventsObserver)


        val likedEventsObserver = Observer<List<RouteDetails>> { it ->
            val newEventsList = listOf(
                MainCardContainer(
                    R.string.upcoming,
                    it.shuffled()
                        .map { it -> it.apply { isInFavorite = true } }
                        .map {
                            EventItem(it) { movie ->
                                openEventDetails(movie)
                            }
                        }.toList()
                )
            )

            adapter.apply { addAll(newEventsList) }
        }

        viewModel.liked.observe(viewLifecycleOwner, likedEventsObserver)
    }

    private fun openRouteDetails(route: RouteDetails) {
        val bundle = Bundle()
        bundle.putString(FeedFragment.KEY_TITLE, route.title)
        bundle.putInt(FeedFragment.KEY_ID, route.routeId)
        findNavController().navigate(R.id.route_details_fragment, bundle, options)
    }


    private fun openEventDetails(event: RouteDetails) {
        val bundle = Bundle()
        bundle.putString(KEY_TITLE, event.title)
        bundle.putInt(KEY_ID, event.id)
        findNavController().navigate(R.id.event_details_fragment, bundle, options)
    }

    private fun openSearch(searchText: String) {
        val bundle = Bundle()
        bundle.putString(KEY_SEARCH, searchText)
        findNavController().navigate(R.id.search_dest, bundle, options)
    }

    override fun onStop() {
        super.onStop()
        search_toolbar.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    companion object {
        const val MIN_LENGTH = 3
        const val KEY_TITLE = "title"
        const val KEY_ID = "id"
        const val KEY_URL = "payment_url"
        const val KEY_SEARCH = "search"
    }
}
