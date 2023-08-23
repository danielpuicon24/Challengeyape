package com.my.training.challengeyape.ui.view


import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.my.training.challengeyape.R
import com.my.training.challengeyape.databinding.ActivityMapaRecetaBinding


class MapaRecetaActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMapaRecetaBinding
    private lateinit var mMap: GoogleMap
    var idReceta : Int? = null
    var latitud : Double? = null
    var longitud : Double? = null
    var nombre : String? = null
    var descripcion : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapaRecetaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idReceta = intent.getIntExtra("idReceta", 0)
        latitud = intent.getDoubleExtra("latitud", 0.0)
        longitud = intent.getDoubleExtra("longitud", 0.0)
        nombre = intent.getStringExtra("nombre")
        descripcion = intent.getStringExtra("descripcion")


        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.txtNombreReceta.text = nombre
        binding.txtDescripcionReceta.text = descripcion

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, DetalleRecetaActivity::class.java)
            intent.putExtra("idReceta", idReceta)
            startActivity(intent)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val height = 130
        val width = 130
        val bitmapdraw = resources.getDrawable(R.drawable.food) as BitmapDrawable
        val b = bitmapdraw.bitmap
        val smallMarker = Bitmap.createScaledBitmap(b, width, height, false)

        val zoomLevel = 15.0f

        mMap.animateCamera(CameraUpdateFactory.zoomIn())
        mMap.uiSettings.isZoomControlsEnabled = true
        val minZoom = 2.0f
        val maxZoom = 18.0f
        mMap.setMinZoomPreference(minZoom)
        mMap.setMaxZoomPreference(maxZoom)

        val location = LatLng(latitud!!, longitud!!)
        mMap.addMarker(
            MarkerOptions()
            .position(location)
            .title(nombre!!.uppercase())
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))

        val cameraPosition = CameraPosition.Builder()
            .target(location)
            .zoom(zoomLevel)
            .bearing(90f)
            .tilt(30f)
            .build()
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
}