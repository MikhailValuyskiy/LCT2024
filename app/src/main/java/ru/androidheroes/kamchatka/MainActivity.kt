package ru.androidheroes.kamchatka

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ru.androidheroes.kamchatka.R.layout.activity_main)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(ru.androidheroes.kamchatka.R.id.my_nav_host_fragment) as NavHostFragment?
            ?: return

        // Set up Action Bar
        val navController = host.navController
        bottom_nav_view.visibility = View.GONE
        setupBottomNavMenu(navController)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decor: View = window.decorView
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }

        bottom_nav_view.visibility = View.VISIBLE

    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav =
            findViewById<BottomNavigationView>(ru.androidheroes.kamchatka.R.id.bottom_nav_view)
        bottomNav?.setupWithNavController(navController)
    }
}
