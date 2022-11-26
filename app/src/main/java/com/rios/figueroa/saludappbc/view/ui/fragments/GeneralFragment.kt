package com.rios.figueroa.saludappbc.view.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rios.figueroa.saludappbc.R
import com.rios.figueroa.saludappbc.view.adapter.GeneralAdapter
import com.rios.figueroa.saludappbc.viewmodel.medicoGeneralViewModel

@Suppress("DEPRECATION")
class GeneralFragment : Fragment() {
    lateinit var recyclerLib: RecyclerView
    lateinit var adapter:GeneralAdapter
    val viewmodel by lazy { ViewModelProvider(this).get(medicoGeneralViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_general, container, false)
        recyclerLib=view.findViewById(R.id.recyclerGeneral)
        adapter= GeneralAdapter(requireContext())
        recyclerLib.layoutManager= LinearLayoutManager(context)
        recyclerLib.adapter=adapter
        observeData()
        return view
    }

    fun observeData(){
        viewmodel.fetchMedicoGeneralData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //instanciamos el menu para que lleve al fragment indicado
        val buttonBottomMenu= view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        buttonBottomMenu.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.nav_inicio -> findNavController().navigate(R.id.action_generalFragment_to_homeFragment)
                R.id.nav_ubicacion -> findNavController().navigate(R.id.action_generalFragment_to_mapFragment)
                R.id.nav_buscar -> findNavController().navigate(R.id.action_generalFragment_to_searchFragment)
                R.id.nav_especialistas -> findNavController().navigate(R.id.action_generalFragment_to_especialidadFragment)
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
                findNavController().navigate(R.id.action_generalFragment_to_perfilFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
}