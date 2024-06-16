package ru.androidheroes.kamchatka.ui.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import ru.androidheroes.kamchatka.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val userName = intent.getStringExtra(Constants.USER_NAME)
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val score = intent.getIntExtra(Constants.SCORE, 0)

        val congratulationsTv: TextView = findViewById(R.id.congratulationsTv)
        val scoreTv: TextView = findViewById(R.id.scoreTv)
        val btnRestart: Button = findViewById(R.id.btnRestart)

        congratulationsTv.text = "Поздравляем, Михаил!"
        scoreTv.text = "Ваш счёт $score из $totalQuestions"
        btnRestart.setOnClickListener{
            val intent = Intent(this, QuizQuestionsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}