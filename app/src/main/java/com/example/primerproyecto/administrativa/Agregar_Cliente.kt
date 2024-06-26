package com.example.primerproyecto.administrativa

import android.content.ContentValues
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.primerproyecto.AdminHelper
import com.example.primerproyecto.R
import java.lang.Integer.parseInt

class Agregar_Cliente : AppCompatActivity()  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.administrativa_agregar_cliente)

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val idEditText: EditText = findViewById(R.id.idEditText)
        val nameEditText: EditText = findViewById(R.id.nameEditText)
        val addressEditText: EditText = findViewById(R.id.addressEditText)
        val maritalStatusSpinner: Spinner = findViewById(R.id.maritalStatusSpinner)
        val salaryEditText: EditText = findViewById(R.id.salaryEditText)
        val phoneEditText: EditText = findViewById(R.id.phoneEditText)
        val birthDateEditText: EditText = findViewById(R.id.birthDateEditText)

        val arraySpinner = arrayOf(
            "soltero/a", "casado/a", "divorciado/a", "viudo/a"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            arraySpinner
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        maritalStatusSpinner.adapter = adapter

        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val id = idEditText.text.toString()
            val name = nameEditText.text.toString()
            val address = addressEditText.text.toString()
            val maritalStatus = maritalStatusSpinner.selectedItem.toString()
            val salaryText = salaryEditText.text.toString()
            val phoneText = phoneEditText.text.toString()
            val birthDate = birthDateEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty() && id.isNotEmpty() &&
                name.isNotEmpty() && address.isNotEmpty() && maritalStatus.isNotEmpty() &&
                salaryText.isNotEmpty() && phoneText.isNotEmpty() && birthDate.isNotEmpty()
            ) {

                val salary = parseInt(salaryText)
                val phone = parseInt(phoneText)
                saveClientInfo(
                    username,
                    password,
                    id,
                    name,
                    address,
                    maritalStatus,
                    salary,
                    phone,
                    birthDate
                )
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun saveClientInfo(
        username: String,
        password: String,
        id: String,
        name: String,
        address: String,
        maritalStatus: String,
        salary: Int,
        phone: Int,
        birthDate: String
    ) {
        val admin = AdminHelper(this, "administracion", null, 1)
        val baseDatos = admin.writableDatabase


        val userValues = ContentValues().apply {
            put("username", username)
            put("password", password)
            put("role", "client")
        }
        val userId = baseDatos.insert("users", null, userValues)

        if (userId > 0) {

            val clientValues = ContentValues().apply {
                put("cedula", id)
                put("name", name)
                put("address", address)
                put("marital_status", maritalStatus)
                put("salary", salary)
                put("phone", phone)
                put("birth_date", birthDate)
                put("user_id", userId)
            }
            val clientId = baseDatos.insert("clients", null, clientValues)

            if (clientId > 0) {
                Toast.makeText(this, "El cliente se ha guardado correctamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al guardar la información del cliente", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Error al guardar la información del cliente", Toast.LENGTH_LONG).show()
        }
    }
}