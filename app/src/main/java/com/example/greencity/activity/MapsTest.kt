package com.example.greencity.activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.greencity.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsTest : AppCompatActivity() {


    lateinit var mapFragment : SupportMapFragment
    lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitymaps)

        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback {
            googleMap = it
            googleMap.isMyLocationEnabled = true
            val location1 = LatLng(40.860672,14.843984)
            googleMap.addMarker(MarkerOptions().position(location1).title("Campania"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1,5f))

         

        })
    }
}
