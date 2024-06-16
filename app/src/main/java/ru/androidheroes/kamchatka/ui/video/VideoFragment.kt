package ru.androidheroes.kamchatka.ui.video

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_video.*
import ru.androidheroes.kamchatka.R
import ru.androidheroes.kamchatka.data.MockRepository
import ru.androidheroes.kamchatka.ui.education.EducationItem
import ru.androidheroes.kamchatka.ui.feed.MainCardContainer

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class VideoFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Добавляем recyclerView
        video_recycler_view.layoutManager = LinearLayoutManager(context)
        video_recycler_view.adapter = adapter.apply { addAll(listOf()) }

        val nearYouEvents = listOf(
            MainCardContainer(
                R.string.music,
                MockRepository.getCoursesMusic()
                    .map {
                        EducationItem(it) { project ->
                            openCourse(project.imageUrl)
                        }
                    }.toList()
            ),
            MainCardContainer(
                R.string.danger,
                MockRepository.getCourses().filter { it.title.contains("Меры предосторожности") }
                    .map {
                        EducationItem(it) { project ->
                            openCourse(project.imageUrl)
                        }
                    }.toList()
            ),
            MainCardContainer(
                R.string.dance,
                MockRepository.getCourses().filter { it.title.contains("Экипировка") }
                    .map {
                        EducationItem(it) { project ->
                            openCourse(project.imageUrl)
                        }
                    }.toList()
            )

        )

        adapter.apply { addAll(nearYouEvents) }
    }

    fun openCourse(url:String){
        val browserIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VideoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}