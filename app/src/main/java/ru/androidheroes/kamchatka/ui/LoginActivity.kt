package ru.androidheroes.kamchatka.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import kotlinx.android.synthetic.main.activity_login.*
import ru.androidheroes.kamchatka.MainActivity
import ru.androidheroes.kamchatka.R
import ru.androidheroes.kamchatka.ui.quiz.Constants
import ru.androidheroes.kamchatka.ui.watchlist.OnboardingFragment

class LoginActivity : AppCompatActivity() {

    var context: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        context = this

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view, OnboardingFragment.newInstance())
        }


        skip.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(Constants.USER_NAME, "user name")
            startActivity(intent)
            finish()
        }

    }
}