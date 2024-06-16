package ru.androidheroes.kamchatka.ui.map

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.androidheroes.kamchatka.ui.event_details.WebViewFragment
import ru.androidheroes.kamchatka.ui.watchlist.*

class MapRootAdapter(fragment: Fragment, private val itemsCount: Int) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
               // MapFragment.newInstance("", "")
                WebViewFragment.newInstance("","")
            }
            1 -> {
                WarningFragment.newInstance("", "")
            }
            else -> WatchlistFragment.newInstance()
        }
    }
}
