package com.example.primerproyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Pantalla_Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_inicio)

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val loginButton: Button = findViewById(R.id.loginButton)
        val exitButton: Button = findViewById(R.id.exitButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            consultar(username, password)
        }

        exitButton.setOnClickListener {
            finish()
        }
    }
    private fun mostrarToast(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    private fun consultar(username: String, password: String) {
        val admin = AdminHelper(this, "administracion", null, 1)
        val baseDatos = admin.writableDatabase

        val cursor = baseDatos.query(
            "users",
            arrayOf("user_id", "username", "role"),
            "username = ? AND password = ?",
            arrayOf(username, password),
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            val userId = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"))
            val role = cursor.getString(cursor.getColumnIndexOrThrow("role"))

            if (role == "admin") {
                abrirPaginaPrincipalAdmin(userId)
            } else if (role == "client") {
                abrirPaginaPrincipalCliente(userId)
            }
        } else {
            mostrarToast("Credenciales incorrectas")
        }
        cursor.close()
    }

    private fun abrirPaginaPrincipalAdmin(userId : Int) {
        val intent = Intent(this, com.example.primerproyecto.administrativa.Principal::class.java)
        intent.putExtra("USER_ID", userId)
        startActivity(intent)

    }

    private fun abrirPaginaPrincipalCliente(userId : Int) {
        val intent = Intent(this, com.example.primerproyecto.cliente.Principal::class.java)
        intent.putExtra("USER_ID", userId)
        startActivity(intent)
    }
}