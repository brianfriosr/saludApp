package com.rios.figueroa.saludappbc.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.rios.figueroa.saludappbc.R

class RegistroActivity : AppCompatActivity() {
    //instanciamos el boton que va a enviar la informacion capturada como datos a la bd
    lateinit var signUpButton: Button
    lateinit var loginButton:TextView
    //instaciamos la coneccion a la base de datos de firebase
    private lateinit var firebaseAuth:FirebaseAuth

    lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //instanciamos el layout que se va a usar
        setContentView(R.layout.activity_registro)
        firebaseAuth= Firebase.auth
        signUpButton=findViewById(R.id.buttonSignUp)
        database = Firebase.database.reference.child("usuario")

        loginButton=findViewById(R.id.bLogin)
        val correo= findViewById<EditText>(R.id.correoRegistro)
        val password= findViewById<EditText>(R.id.passwordRegistro)
        val nombre = findViewById<EditText>(R.id.nombreRegistro)
        val apellido = findViewById<EditText>(R.id.apellidoRegistro)

        signUpButton.setOnClickListener {
            crearcuenta(correo.text.toString(),password.text.toString(),nombre.text.toString(),apellido.text.toString())
        }
        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
    //creamos la funcion que va a dar las instruccion de que hacer con los datos una vez capturados
    private fun crearcuenta(correo:String,password:String,nombre:String, apellido:String) {
        firebaseAuth.createUserWithEmailAndPassword(correo,password)
            //si la funcion es ejecuta de manera correcta le mandaramos un mensaje de confirmacion al usuario
            .addOnCompleteListener(this) {
                Task->if (Task.isSuccessful) {
                    val user=firebaseAuth.currentUser
                val db=database.child(user?.uid.toString())

                db.child("nombre").setValue(nombre)
                db.child("apellido").setValue(apellido)
                db.child("correo").setValue(correo)

                Toast.makeText(baseContext, "Cuenta Creada", Toast.LENGTH_SHORT).show()
                //depues de registrase lo redirecciona a la homeactivity
                startActivity(Intent(this, HomeActivity::class.java))
            }else{
                Toast.makeText(baseContext,"Error, la cuenta no fue creada",Toast.LENGTH_SHORT).show()
            }
            }
    }

}