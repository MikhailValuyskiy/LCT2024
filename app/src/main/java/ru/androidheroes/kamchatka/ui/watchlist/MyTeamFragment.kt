package ru.androidheroes.kamchatka.ui.watchlist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_my_team.*
import ru.androidheroes.kamchatka.R
import ru.androidheroes.kamchatka.ui.event_details.PersonItemHorizontal
import ru.androidheroes.kamchatka.ui.feed.RouteDetails
import ru.androidheroes.kamchatka.ui.feed.FeedFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MyTeamFragment : Fragment() {

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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_team, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Добавляем recyclerView
        team_recycler_view.layoutManager = LinearLayoutManager(context)
        team_recycler_view.adapter = adapter.apply { addAll(listOf()) }

        val pro =

            ru.androidheroes.kamchatka.data.MockRepository.getPeople()
                .map {
                    PersonItemHorizontal(it)
                }



        adapter.apply { addAll(pro) }
    }

    private fun openProjectDetails(event: RouteDetails) {
        val bundle = Bundle()
        bundle.putString(FeedFragment.KEY_TITLE, event.title)
        bundle.putInt(FeedFragment.KEY_ID, event.id)
        findNavController().navigate(R.id.project_details_fragment, bundle)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyCardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyTeamFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}