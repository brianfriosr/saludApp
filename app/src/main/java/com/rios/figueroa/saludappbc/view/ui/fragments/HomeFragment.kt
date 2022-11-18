package com.rios.figueroa.saludappbc.view.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rios.figueroa.saludappbc.R

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //creamos la variable que va a generar la vista a partir del fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }
    //
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //instanciamos el fragment que general
        val cardLibGen = view.findViewById<ImageView>(R.id.cardGeneral)
        //declaramos la funcion on click
        cardLibGen.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_generalFragment)
        }
        //instanciamos el fragment que especialidades
        val cardLibEsp = view.findViewById<ImageView>(R.id.cardEspecialidades)
        //declaramos la funcion on click
        cardLibEsp.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_especialidadFragment)
        }
        //instanciamos el fragment que odontologia
        val cardLibOdon = view.findViewById<ImageView>(R.id.cardOdontologia)
        //declaramos la funcion on click
        cardLibOdon.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_dentistaFragment)
        }
        //instanciamos el menu para que lleve al fragment indicado
        val buttonBottomMenu= view.findViewById<BottomNavigationView>(R.id.bottom_main_navigation)
        buttonBottomMenu.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.nav_ubicacion -> findNavController().navigate(R.id.action_homeFragment_to_mapFragment)
                R.id.nav_buscar -> findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
                R.id.nav_especialistas -> findNavController().navigate(R.id.action_homeFragment_to_especialidadFragment)
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
                findNavController().navigate(R.id.action_homeFragment_to_perfilFragment)
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