package ru.androidheroes.kamchatka.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.warning_fragment.*
import ru.androidheroes.kamchatka.R
import ru.androidheroes.kamchatka.ui.feed.FeedFragment
import ru.androidheroes.kamchatka.ui.feed.RouteDetails

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class WarningFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var eventId: Int? = null

    lateinit var image:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            eventId = it.getInt(FeedFragment.KEY_ID)
        }
    }

    private val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.warning_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val event =
            ru.androidheroes.kamchatka.data.MockRepository.getEvents().find { it.id == eventId }

  add_photo_button.setOnClickListener {
      ImagePicker.with(this)
          .crop()	    			//Crop image(Optional), Check Customization for more option
          .compress(1024)			//Final image size will be less than 1 MB(Optional)
          .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
          .createIntent { intent ->
              startForProfileImageResult.launch(intent)
          }
  }


        val snackbar = Snackbar.make(
            view,
            "Сообщение о нарушении отправлено для рассмотрения!",
            Snackbar.LENGTH_LONG
        )

        send_button.setOnClickListener {
            snackbar.show()
        }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!

               // mProfileUri = fileUri
                image_pollution.setImageURI(fileUri)
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!

            // Use Uri object instead of File to avoid storage permissions
            image_pollution.setImageURI(uri)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openProjectDetails(route: RouteDetails) {
        val bundle = Bundle()
        bundle.putString(FeedFragment.KEY_TITLE, route.title)
        bundle.putInt(FeedFragment.KEY_ID, route.routeId)
        findNavController().navigate(R.id.route_details_fragment, bundle, options)
    }

    override fun onResume() {
        super.onResume()
    }


    private fun openWebView(url: String) {

        val browserIntent = Intent(Intent.ACTION_VIEW, url.toUri())
        ContextCompat.startActivity(requireContext(), browserIntent, null)
    }

    fun saveKey(key: String, value: String) {
        val sharedPref = activity?.getSharedPreferences(
            "my_preffs", Context.MODE_PRIVATE
        )
        with(sharedPref!!.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun getKey(key: String): String? {
        val sharedPref = activity?.getSharedPreferences(
            "my_preffs", Context.MODE_PRIVATE
        )

        return sharedPref?.getString(key, "empty")
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
