package ru.androidheroes.kamchatka.ui.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_addresses.*
import ru.androidheroes.kamchatka.R
import ru.androidheroes.kamchatka.data.AddressRepository
import ru.androidheroes.kamchatka.data.Warning
import ru.androidheroes.kamchatka.ui.afterTextChanged
import ru.androidheroes.kamchatka.ui.feed.FeedFragment
import timber.log.Timber

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class WarningFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        return inflater.inflate(R.layout.fragment_addresses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addresses_list.layoutManager = LinearLayoutManager(context)
        addresses_list.adapter = adapter.apply { addAll(listOf()) }

        address_search_toolbar.search_edit_text.afterTextChanged {
            Timber.d(it.toString())
            val searchTerm = it.toString()
            if (searchTerm.isNotEmpty()) {
                val searchTerm = it.toString()
                if (it.toString().length > FeedFragment.MIN_LENGTH) {
                    val result: List<Warning> =
                        AddressRepository.getWarnings().filter { it.title.contains(searchTerm)  }
                            .toList()
                    adapter.clear()
                    if (result.isNotEmpty()) {
                        adapter.apply {
                            addAll(result
                                .map {
                                    WarningContainer(it) { a ->
                                    }
                                }
                            )
                        }
                    }
                }
            } else {
                adapter.clear()
                getAddresses()
            }
        }

        getAddresses()
    }

    private fun getAddresses() {
        val addreses =

            AddressRepository.getWarnings()
                .map {
                    WarningContainer(it) { a ->
                    }
                }

        adapter.apply { addAll(addreses) }
    }


    override fun onStop() {
        super.onStop()
        adapter.clear()
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WarningFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}