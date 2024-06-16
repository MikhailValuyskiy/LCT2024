package ru.androidheroes.kamchatka.ui.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_profile.mapViewPager
import kotlinx.android.synthetic.main.fragment_profile.tabLayout
import ru.androidheroes.kamchatka.R

class MapRootFragment : Fragment() {

    private lateinit var mapRootTabLayoutTitles: Array<String>

    private var profilePageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map_root, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapRootTabLayoutTitles = resources.getStringArray(R.array.map_tab_titles)

        mapViewPager.isUserInputEnabled = false;

        val profileAdapter = MapRootAdapter(
            this,
            mapRootTabLayoutTitles.size
        )
        mapViewPager.adapter = profileAdapter

        mapViewPager.registerOnPageChangeCallback(profilePageChangeCallback)

        TabLayoutMediator(tabLayout, mapViewPager) { tab, position ->

            val title =  mapRootTabLayoutTitles[position]


            tab.text = title
        }.attach()
    }
}
