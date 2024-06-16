package ru.androidheroes.kamchatka.ui.map

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.*
import com.yandex.runtime.image.ImageProvider
import com.yandex.runtime.ui_view.ViewProvider
import kotlinx.android.synthetic.main.fragment_map.*
import ru.androidheroes.kamchatka.R
import ru.androidheroes.kamchatka.data.AddressRepository
import ru.androidheroes.kamchatka.data.District
import ru.androidheroes.kamchatka.data.Warning
import ru.androidheroes.kamchatka.ui.watchlist.ConfirmationDialog
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MapFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var mapObjects: MapObjectCollection? = null
    private val animationHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        MapKitFactory.initialize(activity?.applicationContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            ru.androidheroes.kamchatka.R.layout.fragment_map,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapview.map.move(
            CameraPosition(Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0f),
            null
        )

        mapObjects = mapview.map.mapObjects.addCollection()


        val style: TextStyle = TextStyle(
            14f,
            ContextCompat.getColor(requireContext(), R.color.black),
            0,
            TextStyle.Placement.BOTTOM,
            0f,
            false,
            false
        )
        val schools = AddressRepository.getWarnings()
            .map { it ->
                mapObjects!!.addPlacemark(Point(it.location.first, it.location.second)).apply {
                    setIcon(
                        (ImageProvider.fromResource(
                            activity,
                            getPin(it.district)
                        ))
                    )
                    addTapListener(circleMapObjectTapListener)
                    userData = it
                    //  setText(it.id.toString(),style)

                }
            }

//        val mark = mapObjects!!.addPlacemark(Point(55.740624, 37.590686))
//        mark.setIcon(ImageProvider.fromResource(activity, ru.androidheroes.metodcabinet.R.drawable.green))
//        mark.isDraggable = true
//        mark.setText("111")

        createPlacemarkMapObjectWithViewProvider()

    }

    fun getPin(district:District):Int{
       return  when(district){
            District.CAO ->  R.drawable.pin
            District.SAO ->  R.drawable.pin_blue
            District.SBAO ->  R.drawable.pin_ultra
            District.BAO -> R.drawable.pin_violet
            District.YBAO -> R.drawable.pin_violet
            District.YAO -> R.drawable.pin_orange
            District.YZAO -> R.drawable.pin_green
            District.ZAO -> R.drawable.pin_malina
            District.SZAO -> R.drawable.pin_dark_violet
            District.ZelAO -> R.drawable.pin_light_green
            District.TinAO -> R.drawable.pin_light_green
        }
    }

    private val circleMapObjectTapListener =
        MapObjectTapListener { mapObject, point ->
            if (mapObject is PlacemarkMapObject) {
                var s = (mapObject.userData as Warning)
                ConfirmationDialog.newInstance(s, activity as Context)
            }

            true
        }

    private fun createPlacemarkMapObjectWithViewProvider() {
        val textView = TextView(activity)
        val colors = intArrayOf(Color.RED, Color.GREEN, Color.BLACK)
        val params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        textView.layoutParams = params
        textView.setTextColor(Color.RED)
        textView.text = "Hello, World!"
        val viewProvider = ViewProvider(textView)
        val viewPlacemark = mapObjects!!.addPlacemark(Point(59.946263, 30.315181), viewProvider)
        val random = Random()
        val delayToShowInitialText = 5000 // milliseconds
        val delayToShowRandomText = 500 // milliseconds;

        // Show initial text `delayToShowInitialText` milliseconds and then
        // randomly change text in textView every `delayToShowRandomText` milliseconds
        animationHandler?.postDelayed(object : Runnable {
            override fun run() {
                val randomInt: Int = random.nextInt(1000)
                textView.text = "Some text version $randomInt"
                textView.setTextColor(colors[randomInt % colors.size])
                viewProvider.snapshot()
                viewPlacemark.setView(viewProvider)
                animationHandler.postDelayed(this, delayToShowRandomText.toLong())
            }
        }, delayToShowInitialText.toLong())
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart();
        mapview.onStart();
    }

    override fun onStop() {
        super.onStop()
        mapview.onStop();
        MapKitFactory.getInstance().onStop()
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}