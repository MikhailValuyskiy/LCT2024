package ru.androidheroes.kamchatka.ui.watchlist

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import ru.androidheroes.kamchatka.MainActivity
import ru.androidheroes.kamchatka.R
import ru.androidheroes.kamchatka.ui.RegistrationActivity
import ru.androidheroes.kamchatka.ui.quiz.Constants

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class OnboardingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.onboarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val go_button = view.findViewById<TextView>(R.id.go_button)

        val viewPager = view.findViewById<ViewPager>(R.id.viewpager)

        val closeButton = view.findViewById<ImageView>(R.id.ic_close_button)


        closeButton.setOnClickListener(View.OnClickListener {

        })

        val adapter = SplashViewPagerAdapter(childFragmentManager)

        // add your fragments

//        adapter.addFrag(
//            SplashTwoFragment.newInstance(
//                R.drawable.tourist,
//                resources.getString(R.string.tutorial_1_title),
//                resources.getString(R.string.tutorial_1_subtitle)
//            ), getString(R.string.services)
//        )
//
//        adapter.addFrag(
//            SplashTwoFragment.newInstance(
//                R.drawable.warning,
//                resources.getString(R.string.tutorial_2_title),
//                resources.getString(R.string.tutorial_2_subtitle)
//            ), getString(R.string.services)
//        )

        adapter.addFrag(
            LottieSplashFragment.newInstance(
                R.raw.travel,
                resources.getString(R.string.tutorial_1_title),
                resources.getString(R.string.tutorial_1_subtitle)
            ), getString(R.string.services)
        )

        adapter.addFrag(
            LottieSplashFragment.newInstance(
                R.raw.factory,
                resources.getString(R.string.tutorial_2_title),
                resources.getString(R.string.tutorial_2_subtitle)
            ), getString(R.string.services)
        )

        // set adapter on viewpager
        viewPager.setAdapter(adapter)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if (position == 1) {
                    go_button.text = resources.getString(R.string.tutorial_start)
                    go_button.setOnClickListener {
                        val intent = Intent(context, MainActivity::class.java)
                        intent.putExtra(Constants.USER_NAME, "user name")
                        startActivity(intent)
                        activity?.finish()
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

//        viewPager.invalidateBullets()
        go_button.setOnClickListener {
            if (viewPager.currentItem < 2) {
                viewPager.currentItem = viewPager.currentItem + 1
            } else {
//                startHome()
            }
        }
    }

    fun openRegistration(it: String) {
        val myIntent = Intent(context, RegistrationActivity::class.java)
        myIntent.putExtra("Token", it)

        context?.startActivity(myIntent)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OnboardingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = OnboardingFragment()

    }
}