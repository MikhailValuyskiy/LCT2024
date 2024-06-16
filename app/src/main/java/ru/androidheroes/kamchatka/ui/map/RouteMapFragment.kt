package ru.androidheroes.kamchatka.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.route_map_fragment.*
import org.osmdroid.bonuspack.kml.KmlDocument
import org.osmdroid.config.Configuration.getInstance
import org.osmdroid.events.DelayedMapListener
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.FolderOverlay
import org.osmdroid.views.overlay.Polyline
import org.osmdroid.views.overlay.compass.CompassOverlay
import ru.androidheroes.kamchatka.R
import ru.androidheroes.kamchatka.ui.feed.FeedFragment
import ru.androidheroes.kamchatka.ui.feed.RouteDetails


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RouteMapFragment : Fragment() {

    private lateinit var route:RouteDetails

    private var mLastLocation: Location? = null
    private var lat: Double? = 0.0
    private var long: Double? = 0.0

    lateinit var locationManager: LocationManager
    lateinit var locationListener: android.location.LocationListener


    private var param1: String? = null
    private var param2: String? = null
    private var eventId: Int? = null
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1
    private lateinit var mapView: MapView


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

    private fun checkLocationPermissionStatus(): Boolean {
        val permission = Manifest.permission.ACCESS_FINE_LOCATION

        return ContextCompat.checkSelfPermission(
            requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    private val foregroundLocationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions.any { it.value }

        if (granted) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0L,
                0F,
                locationListener!!
            )
        }
    }

    // Request the permission

    private fun ensureForegroundLocationPermission() {
        foregroundLocationPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        getInstance().load(
            requireContext(),
            PreferenceManager.getDefaultSharedPreferences(requireContext())
        )

        return inflater.inflate(R.layout.route_map_fragment, container, false)
    }

    private fun setupMap(view: View) {
        mapView = view.findViewById(R.id.mapview) as MapView
        mapView.setClickable(true)
        mapView.setBuiltInZoomControls(true)
        //setContentView(mapView); //displaying the MapView
        mapView.getController().setZoom(15) //set initial zoom-level, depends on your need
        //mapView.getController().setCenter(ONCATIVO);
        //mapView.setUseDataConnection(false); //keeps the mapView from loading online tiles using network connection.
        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE)

//        val oMapLocationOverlay = MyLocationNewOverlay(requireContext(), mapView)
//        mapView.getOverlays().add(oMapLocationOverlay)
//        oMapLocationOverlay.enableFollowLocation()
//        oMapLocationOverlay.enableMyLocation()
//        oMapLocationOverlay.enableFollowLocation()


        val compassOverlay = CompassOverlay(requireContext(), mapView)
        compassOverlay.enableCompass()
        mapView.getOverlays().add(compassOverlay)
        mapView.setMapListener(DelayedMapListener(object : MapListener {
            override fun onZoom(e: ZoomEvent): Boolean {
                val mapView = view.findViewById(R.id.mapview) as MapView
                val latitudeStr = "" + mapView.mapCenter.latitude
                val longitudeStr = "" + mapView.mapCenter.longitude
                val latitudeFormattedStr = latitudeStr.substring(0, Math.min(latitudeStr.length, 7))
                val longitudeFormattedStr =
                    longitudeStr.substring(0, Math.min(longitudeStr.length, 7))
                Log.i("zoom", "" + mapView.mapCenter.latitude + ", " + mapView.mapCenter.longitude)
                val latLongTv: TextView = view.findViewById(R.id.textView) as TextView
                latLongTv.setText("$latitudeFormattedStr, $longitudeFormattedStr")
                return true
            }

            override fun onScroll(e: ScrollEvent): Boolean {
                val mapView = view.findViewById(R.id.mapview) as MapView
                val latitudeStr = "" + mapView.mapCenter.latitude
                val longitudeStr = "" + mapView.mapCenter.longitude
                val latitudeFormattedStr = latitudeStr.substring(0, Math.min(latitudeStr.length, 7))
                val longitudeFormattedStr =
                    longitudeStr.substring(0, Math.min(longitudeStr.length, 7))
                Log.i(
                    "scroll",
                    "" + mapView.mapCenter.latitude + ", " + mapView.mapCenter.longitude
                )
                val latLongTv: TextView = view.findViewById(R.id.textView) as TextView
                latLongTv.setText("$latitudeFormattedStr, $longitudeFormattedStr")
                return true
            }
        }, 1000))
    }

    private fun setCenterInMyCurrentLocation() {
        val mapController = mapView.controller
        mapController.setZoom(9.5)
        val startPoint = GeoPoint(53.351978, 158.38896);
        mapController.setCenter(startPoint);

        if (mLastLocation != null) {
            mapView.controller.setCenter(
                GeoPoint(
                    158.38896,
                    53.351978
                )
            )
        } else {
            Toast.makeText(requireContext(), "Getting current location", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("CheckResult")
    private fun setUpRoute() {
        val line = Polyline(mapView)
        line.subDescription = Polyline::class.java.canonicalName
        line.width = 10f


        val kmlDocument = KmlDocument()

        Observable.fromCallable { kmlDocument.parseKMLUrl(route.kmlUrl ?: "https://trekkingmania.ru/assets/files/kml/nalyuchevo.kml") }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val kmlOverlay =
                    kmlDocument.mKmlRoot.buildOverlay(
                        mapView,
                        null,
                        null,
                        kmlDocument
                    ) as FolderOverlay
                mapView.overlays.add(kmlOverlay)

                val bb: BoundingBox = kmlDocument.mKmlRoot.getBoundingBox()
                mapView.zoomToBoundingBox(bb, true)
            }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        warning_item.setOnClickListener {
            openWarning(route)
        }

        ensureForegroundLocationPermission()
        setupMap(view)

        locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationListener = android.location.LocationListener { location ->

            lat = location.latitude
            long = location.longitude
            mLastLocation = location

        }

        setCenterInMyCurrentLocation()

        setUpRoute()


        // TODO Add id
        route =
            ru.androidheroes.kamchatka.data.MockRepository.getRoutes()
                .find { it.routeId == eventId }!!

    }

    private fun openWarning(event: RouteDetails) {
        val bundle = Bundle()
        bundle.putString(FeedFragment.KEY_TITLE, event.title)
        bundle.putInt(FeedFragment.KEY_ID, event.id)
        findNavController().navigate(R.id.warning_fragment, bundle, options)
    }

    override fun onResume() {
        super.onResume()
        // map.onResume()
    }

    override fun onPause() {
        super.onPause()
        // map.onPause()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        val permissionsToRequest = ArrayList<String>()
        var i = 0
        while (i < grantResults.size) {
            permissionsToRequest.add(permissions[i])
            i++
        }
        if (permissionsToRequest.size > 0) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                permissionsToRequest.toTypedArray(),
                REQUEST_PERMISSIONS_REQUEST_CODE
            )
        }
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
            RouteMapFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }
}
