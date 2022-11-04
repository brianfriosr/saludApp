package com.rios.figueroa.saludappbc.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.rios.figueroa.saludappbc.R

class RecuperarContActivity : AppCompatActivity() {
    lateinit var forgotButton: Button
    lateinit var registroButton: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_cont)

        forgotButton=findViewById(R.id.buttonForgotPassword)
        registroButton=findViewById(R.id.bCreateAccount)

        forgotButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        registroButton.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }
}