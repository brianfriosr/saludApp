package com.rios.figueroa.saludappbc.view.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.rios.figueroa.saludappbc.R

@Suppress("DEPRECATION")
class PerfilFragment : Fragment() {

    lateinit var firebaseAuth:FirebaseAuth
    var database:DatabaseReference=FirebaseDatabase.getInstance().getReference("usuario")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = Firebase.auth
        val user = firebaseAuth.currentUser
        val name = view.findViewById<EditText>(R.id.nameProfile)
        val lastName = view.findViewById<EditText>(R.id.lastNameProfile)
        val email = view.findViewById<EditText>(R.id.emailProfile)
        val editName=view.findViewById<ImageButton>(R.id.editName)
        val editLastName=view.findViewById<ImageButton>(R.id.editLastName)

        editName.setOnClickListener{
            database.child(user?.uid.toString()).child("nombre").setValue(name.text.toString()).addOnSuccessListener {
                Toast.makeText(this.context,"Se cambio el nombre correctamente",Toast.LENGTH_LONG).show()
            }
        }

        editLastName.setOnClickListener{
            database.child(user?.uid.toString()).child("apellido").setValue(lastName.text.toString()).addOnSuccessListener {
                Toast.makeText(this.context,"Se cambio el apellido correctamente",Toast.LENGTH_LONG).show()
            }
        }

        email.setText(user?.email.toString())

        database.child(user?.uid.toString()).addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                name.setText(snapshot.child("nombre").value.toString())
                lastName.setText(snapshot.child("apellido").value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

        val buttonBottomMenu= view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        buttonBottomMenu.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.nav_inicio -> findNavController().navigate(R.id.action_perfilFragment_to_homeFragment)
                R.id.nav_ubicacion -> findNavController().navigate(R.id.action_perfilFragment_to_mapFragment)
                R.id.nav_buscar -> findNavController().navigate(R.id.action_perfilFragment_to_searchFragment)
                R.id.nav_especialistas -> findNavController().navigate(R.id.action_perfilFragment_to_especialidadFragment)
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
                findNavController().navigate(R.id.action_perfilFragment_to_homeFragment)
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


