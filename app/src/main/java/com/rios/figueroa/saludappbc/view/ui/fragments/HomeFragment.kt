package com.rios.figueroa.saludappbc.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.rios.figueroa.saludappbc.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //creamos la variable que va a generar la vista apartir del fragment
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
    }

}