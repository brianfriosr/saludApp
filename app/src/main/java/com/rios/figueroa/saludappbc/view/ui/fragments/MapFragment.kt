package com.rios.figueroa.saludappbc.view.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rios.figueroa.saludappbc.R

@Suppress("DEPRECATION")
class MapFragment : Fragment(), OnMapReadyCallback{
    private lateinit var googleMap:GoogleMap

    companion object{
        const val REQUEST_CODE_LOCATION=0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        val mapFragment = this.childFragmentManager.findFragmentById(R.id.map_view) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonBottomMenu= view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        buttonBottomMenu.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.nav_inicio -> findNavController().navigate(R.id.action_mapFragment_to_homeFragment)
                R.id.nav_buscar -> findNavController().navigate(R.id.action_mapFragment_to_searchFragment)
                R.id.nav_especialistas -> findNavController().navigate(R.id.action_mapFragment_to_especialidadFragment)
            }
        }
        (activity as AppCompatActivity).setSupportActionBar(view?.findViewById(R.id.actionbartoolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_navigation_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.perfil->{
                findNavController().navigate(R.id.action_mapFragment_to_perfilFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onMapReady(map: GoogleMap) {
        val bogota = LatLng(4.60971, -74.08175)
        map?.let {
            this.googleMap=it
            map.addMarker(MarkerOptions().position(bogota))
        }
        enableLocation()
    }

    private fun isLocationPermissionGrated()=ContextCompat.checkSelfPermission(this.requireContext(),
        android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED

    @SuppressLint("MissingPermission")
    private fun enableLocation(){
        if(!::googleMap.isInitialized)return
        if(isLocationPermissionGrated()){
            googleMap.isMyLocationEnabled=true
        }else{
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this.requireActivity(),android.Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(context, "requiere activar permisos en ajustes", Toast.LENGTH_SHORT).show()
        }else{
            ActivityCompat.requestPermissions(this.requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                com.rios.figueroa.saludappbc.view.ui.fragments.MapFragment.REQUEST_CODE_LOCATION)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            com.rios.figueroa.saludappbc.view.ui.fragments.MapFragment.REQUEST_CODE_LOCATION ->
                if(grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    googleMap.isMyLocationEnabled=true
                }else{
                    Toast.makeText(context,"Para activar la localización ir a ajustes y aceptar los permisos",
                        Toast.LENGTH_SHORT).show()
                }else->{}
        }
    }
}