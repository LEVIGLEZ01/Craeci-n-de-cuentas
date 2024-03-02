package com.lewis23.newsession

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class CrearCuentaAct : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)
        val txtnombre : TextView = findViewById(R.id.edtNombre)
        val txtemail_nuevo : TextView = findViewById(R.id.edtEmailN)
        val txtpass1 : TextView = findViewById(R.id.edtPass1)
        val txtpass2 : TextView = findViewById(R.id.edtPass2)
        val btncrearc: Button = findViewById(R.id.btnCrearC)
        btncrearc.setOnClickListener()
        {
            val pass1 = txtpass1.text.toString()
            val pass2 = txtpass2.text.toString()
            if (pass1.equals(pass2))
            {
                createAccount(txtemail_nuevo.text.toString(), txtpass1.text.toString())
            }
            else
            {
                Toast.makeText(baseContext, "No son compatibles", Toast.LENGTH_SHORT).show()
                txtpass1.requestFocus()
            }
        }
        firebaseAuth= Firebase.auth
    }

    private fun createAccount(email: String, password: String)
    {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){task ->
        if (task.isSuccessful)
        {
            Toast.makeText(baseContext, "Cueta Registrada", Toast.LENGTH_SHORT).show()

        }
            else
        {
            Toast.makeText(baseContext, "Error en los datos" + task.exception, Toast.LENGTH_SHORT).show()
        }
        }
    }
}