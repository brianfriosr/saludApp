package com.rios.figueroa.saludappbc.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rios.figueroa.saludappbc.R

class RecuperarContActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var botonRecuperar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_cont)
        firebaseAuth= Firebase.auth
        botonRecuperar=findViewById(R.id.botonrecuperar)
        val correo = findViewById<EditText>(R.id.recuperarcorreo)
        botonRecuperar.setOnClickListener {
            cambiocontra(correo.text.toString())
        }
    }
    private fun cambiocontra(correo:String){
        firebaseAuth.sendPasswordResetEmail(correo)
            .addOnCompleteListener(this){
                task->if(task.isSuccessful) {
                Toast.makeText(baseContext, "Correo enviado", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
            }else{
                Toast.makeText(baseContext, "Error, correo no encontrado", Toast.LENGTH_SHORT).show()
            }
            }
    }

}