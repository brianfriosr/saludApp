package com.rios.figueroa.saludappbc.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.rios.figueroa.saludappbc.R
import com.rios.figueroa.saludappbc.view.ui.fragments.HomeFragment

class LoginActivity : AppCompatActivity() {
    lateinit var loginButton:Button
    lateinit var registroButton:TextView
    lateinit var recuperarButton:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton=findViewById(R.id.buttonLogin)
        registroButton=findViewById(R.id.bCreateAccount)
        recuperarButton=findViewById(R.id.bForgotPassword)

        loginButton.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        registroButton.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        recuperarButton.setOnClickListener {
            startActivity(Intent(this, RecuperarContActivity::class.java))
        }
    }
}