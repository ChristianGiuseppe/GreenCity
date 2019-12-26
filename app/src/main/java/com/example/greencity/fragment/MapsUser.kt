package com.example.greencity.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.greencity.CustomDialog
import com.example.greencity.DBFirebase
import com.example.greencity.R
import com.example.greencity.pojo.InformazioniGenerali
import com.example.greencity.pojo.Markers
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


/**
 * @class MapsUser: Questa classe gestisce il fragment per la mappa
 * @var mapGreenCity: utilizzata per settare le informazioni sulla mappa
 */
class MapsUser : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private var editor2: SharedPreferences.Editor? = null
    private  var mapGreenCity: GoogleMap ? = null
    private lateinit var btnMarker: FloatingActionButton
    private var btnConferma: Button? = null
    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationManager = context!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    }


    /**
     * @callback onMapReady: viene invocata nel momento in cui la mappa può' essere visualizzata
     * in questo caso nel momento in cui è pronta vengono settati i paramentri necessari
     * per la visualizzazione.
     */
    @SuppressLint("MissingPermission")
    override fun onMapReady(mappa: GoogleMap) {
        mapGreenCity = mappa
        mapGreenCity?.uiSettings?.isZoomControlsEnabled = true
        mapGreenCity?.setOnMarkerClickListener(this)
        val sharedPreferences = context?.getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val sharedPreferences2 = context?.getSharedPreferences("SP_INFO2", Context.MODE_PRIVATE)
        val idUsSP = sharedPreferences?.getString("IDUSER","").toString()
        editor2 = sharedPreferences2?.edit()
        locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, locationListener)
        editor2?.clear()
        editor2?.commit()

        //var usersCurrent: Utente? = InformazioniGenerali.getInformazioniGenerali().user
        //var idUser :String ? = InformazioniGenerali.getInformazioniGenerali().idUs
        //var listMarkers: ArrayList<LatLng?> = ArrayList()
        DBFirebase.getDbFirebase().databaseReference.addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(InformazioniGenerali.getInformazioniGenerali().idUs != null){
                    val snapshotIterator = dataSnapshot.child("users").child(InformazioniGenerali.getInformazioniGenerali().idUs).children
                    val iterator = snapshotIterator.iterator()
                    while (iterator.hasNext()) {
                        var isValid = false
                        var nextIt = iterator.next()
                        var finishMarkCognome = nextIt.key?.equals("cognome")
                        var finishMarkNome = nextIt.key?.equals("nome")
                        var finishMarkEmail = nextIt.key?.equals("email")
                        var finishMarkPassword = nextIt.key?.equals("password")
                        var finishMarkAdmin =  nextIt.key?.equals("admin")
                        var finishMarkCapoluogo =  nextIt.key?.equals("capoluogo")
                        var finishMarkRegione =  nextIt.key?.equals("regione")

                        if(finishMarkCognome == true || finishMarkCapoluogo ==  true || finishMarkRegione == true  || finishMarkNome == true || finishMarkEmail == true || finishMarkPassword == true || finishMarkAdmin == true){
                            isValid = true
                        }


                        if(isValid == false){
                            var marker: Markers? = nextIt.getValue(Markers::class.java)
                            var latitude = marker?.latitudine?.toDouble()
                            var longitude = marker?.longitudine?.toDouble()
                            var pos : LatLng = LatLng(latitude!!, longitude!!)
                            val markerOptions = MarkerOptions().position(pos)

                            mapGreenCity?.addMarker(markerOptions)
                            //mapGreenCity?.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 5f))
                        }
                    }
                }
                else{
                    val snapshotIterator = dataSnapshot.child("users").child(idUsSP.toString()).children
                    val iterator = snapshotIterator.iterator()
                    while (iterator.hasNext()) {
                        var isValid = false
                        var nextIt = iterator.next()
                        var finishMarkCognome = nextIt.key?.equals("cognome")
                        var finishMarkNome = nextIt.key?.equals("nome")
                        var finishMarkEmail = nextIt.key?.equals("email")
                        var finishMarkPassword = nextIt.key?.equals("password")
                        var finishMarkAdmin =  nextIt.key?.equals("admin")
                        var finishMarkCapoluogo =  nextIt.key?.equals("capoluogo")
                        var finishMarkRegione =  nextIt.key?.equals("regione")

                        if(finishMarkCognome == true || finishMarkCapoluogo ==  true || finishMarkRegione == true  || finishMarkNome == true || finishMarkEmail == true || finishMarkPassword == true || finishMarkAdmin == true){
                            isValid = true
                        }


                        if(isValid == false){
                            var marker: Markers? = nextIt.getValue(Markers::class.java)
                            var latitude = marker?.latitudine?.toDouble()
                            var longitude = marker?.longitudine?.toDouble()
                            var pos : LatLng = LatLng(latitude!!, longitude!!)
                            val markerOptions = MarkerOptions().position(pos)

                            mapGreenCity?.addMarker(markerOptions)
                            //mapGreenCity?.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 5f))
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

    }

    //define the listener
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            val currentLatLng = LatLng(location.latitude, location.longitude)
            val markerOptions = MarkerOptions().position(currentLatLng)
            mapGreenCity?.addMarker(markerOptions)
            mapGreenCity?.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 10f))
            editor2?.putString("LATITUDE",location.latitude.toString())
            editor2?.putString("LONGITUDE",location.longitude.toString())
            editor2?.commit()
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        val root: View = inflater.inflate(R.layout.fragment_maps_user, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isLocationEnabled()) {
            buildAlertMessageNoGps()
        }
        btnMarker = view.findViewById(R.id.open_marker_dialog)
        btnMarker.setOnClickListener {
            val markerDialog = CustomDialog(this.context)
            markerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            markerDialog.setContentView(R.layout.activity_custom_dialog)
            val v = markerDialog.window?.decorView
            v?.setBackgroundResource(android.R.color.transparent);
            markerDialog.show()


        }

        /*btnConferma?.setOnClickListener {
            Log.i("entro","dentrobutton")
            DBFirebase.getDbFirebase().databaseReference.child(idUser.toString()).child("Marker").push().setValue("nome")
       */
    }

    private fun buildAlertMessageNoGps() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context!!);
        builder.setMessage(getString(R.string.gps_message))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.positive_message)) { dialog, id ->
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
            .setNegativeButton(getString(R.string.negative_message)) { dialog, id ->
                dialog.cancel();
            }
        val alert: AlertDialog = builder.create();
        alert.show();
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    private fun isLocationEnabled(): Boolean {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }


}