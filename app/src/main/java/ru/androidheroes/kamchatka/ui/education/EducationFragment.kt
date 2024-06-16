package ru.androidheroes.kamchatka.ui.education

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_education.*
import ru.androidheroes.kamchatka.R
import ru.androidheroes.kamchatka.data.MockRepository
import ru.androidheroes.kamchatka.ui.feed.MainCardContainer
import ru.androidheroes.kamchatka.ui.quiz.Constants
import ru.androidheroes.kamchatka.ui.quiz.QuizQuestionsActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EducationFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_education, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Добавляем recyclerView
        education_recycler_view.layoutManager = LinearLayoutManager(context)
        education_recycler_view.adapter = adapter.apply { addAll(listOf()) }

        val nearYouEvents = listOf(
            MainCardContainer(
                R.string.projects,
                MockRepository.getTasks()
                    .map {
                        EducationItem(it) { project ->
                            openQuiz()
                        }
                    }.toList()
            ),
            MainCardContainer(
                R.string.projects,
                MockRepository.getMusicTasks()
                    .map {
                        EducationItem(it) { project ->
                            openQuiz()
                        }
                    }.toList()
            ),
            MainCardContainer(
                R.string.routes_header,
                MockRepository.getInteractivTasks()
                    .map {
                        EducationItem(it) { project ->
                            openPuzzle()
                        }
                    }.toList()
            )

        )

        adapter.apply { addAll(nearYouEvents) }
    }

    fun openQuiz(){
        val intent = Intent(this.activity, QuizQuestionsActivity::class.java)
        intent.putExtra(Constants.USER_NAME, "user name")
        startActivity(intent)
    }

    fun openPuzzle(){
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EducationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}