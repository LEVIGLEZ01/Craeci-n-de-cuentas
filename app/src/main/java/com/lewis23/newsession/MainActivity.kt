package com.lewis23.newsession

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btningresar : Button = findViewById(R.id.btnIngresar)
        val txtemail : TextView = findViewById(R.id.edtEmail)
        val txtpass : TextView = findViewById(R.id.edtPassword)
        val btnCrear : TextView = findViewById(R.id.btnCrear)
        firebaseAuth= Firebase.auth
        btningresar.setOnClickListener()
        {
          singIn(txtemail.text.toString(), txtpass.text.toString())
        }
        btnCrear.setOnClickListener()
        {
            val i = Intent (this, CrearCuentaAct::class.java)
            startActivity(i)
        }
    }

    private fun singIn(email: String, password: String)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){task ->
            if (task.isSuccessful){
                val user = firebaseAuth.currentUser
                Toast.makeText(baseContext, "Autenticación Exitosa", Toast.LENGTH_SHORT).show()
                //aqui vamos a ir a la segunda activity
                val i = Intent(this, MainActivity2::class.java)
                startActivity(i)
            }
            else
            {
                Toast.makeText(baseContext, "Error de Email y/o Contraseña", Toast.LENGTH_SHORT).show()
            }

        }
    }
}
