package com.rios.figueroa.saludappbc.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rios.figueroa.saludappbc.R
import com.rios.figueroa.saludappbc.view.ui.fragments.HomeFragment

class LoginActivity : AppCompatActivity() {
    lateinit var loginButton:Button
    lateinit var registroButton:TextView
    lateinit var recuperarButton:TextView
    //instaciamos la coneccion a la base de datos de firebase
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener:FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email=findViewById<EditText>(R.id.editTextUsername)
        val password=findViewById<EditText>(R.id.editTextPassword)

        firebaseAuth=Firebase.auth

        loginButton=findViewById(R.id.buttonLogin)
        registroButton=findViewById(R.id.bCreateAccount)
        recuperarButton=findViewById(R.id.bForgotPassword)

        loginButton.setOnClickListener {
            if (!email.text.toString().isNullOrEmpty()) {
                login(email.text.toString(), password.text.toString())
            }else{
                Toast.makeText(baseContext, "Ingrese por favor los datos solicitados", Toast.LENGTH_SHORT).show()
            }
        }

        registroButton.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        recuperarButton.setOnClickListener {
            startActivity(Intent(this, RecuperarContActivity::class.java))
        }
    }

    private fun login (email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task->
                if(task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    Toast.makeText(baseContext,user?.uid.toString(), Toast.LENGTH_SHORT).show()
                    val i = Intent(this, HomeActivity::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(baseContext,"Usuario o Contra incorrecto", Toast.LENGTH_SHORT).show()
                }

            }
    }
}