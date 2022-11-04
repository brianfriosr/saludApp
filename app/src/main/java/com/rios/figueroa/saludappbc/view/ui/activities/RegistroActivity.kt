package com.rios.figueroa.saludappbc.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.rios.figueroa.saludappbc.R

class RegistroActivity : AppCompatActivity() {
    lateinit var signUpButton: Button
    lateinit var iniciarSesionButton: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        signUpButton=findViewById(R.id.buttonSignUp)
        iniciarSesionButton=findViewById(R.id.bSignUp)

        signUpButton.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        iniciarSesionButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }


    }
}