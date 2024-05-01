package com.example.primerproyecto.cliente

import android.content.ContentValues
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.primerproyecto.AdminHelper
import com.example.primerproyecto.R

class Ver_Informacion_Personal : AppCompatActivity()  {
    private lateinit var nameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var birthDateEditText: EditText
    private lateinit var maritalStatusSpinner: Spinner
    private lateinit var addressEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cliente_ver_informacion_personal)

        nameEditText = findViewById(R.id.nameEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        birthDateEditText = findViewById(R.id.birthDateEditText)
        maritalStatusSpinner = findViewById(R.id.maritalStatusSpinner)
        addressEditText = findViewById(R.id.addressEditText)
        saveButton = findViewById(R.id.saveButton)

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

        val userId = intent.getStringExtra("USER_ID")
        userId?.let {
            loadClientInformation(it)
        }

        saveButton.setOnClickListener {
            saveClientInformation(userId)
        }
    }

    private fun loadClientInformation(userId: String) {
        val admin = AdminHelper(this, "administracion", null, 1)
        val database = admin.readableDatabase
        val cursor = database.query(
            "clients", // Nombre de la tabla
            arrayOf("name", "phone", "birth_date", "marital_status", "address"),
            "user_id = ?",
            arrayOf(userId),
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"))
            val birthDate = cursor.getString(cursor.getColumnIndexOrThrow("birth_date"))
            val maritalStatus = cursor.getString(cursor.getColumnIndexOrThrow("marital_status"))
            val address = cursor.getString(cursor.getColumnIndexOrThrow("address"))


            nameEditText.setText(name)
            phoneEditText.setText(phone)
            birthDateEditText.setText(birthDate)
            addressEditText.setText(address)
        }
        cursor.close()
    }

    private fun saveClientInformation(userId: String?) {
        userId?.let {
            val admin = AdminHelper(this, "administracion", null, 1)
            val database = admin.writableDatabase
            val values = ContentValues().apply {
                put("name", nameEditText.text.toString())
                put("phone", phoneEditText.text.toString())
                put("birth_date", birthDateEditText.text.toString())
                put("marital_status", maritalStatusSpinner.selectedItem.toString())
                put("address", addressEditText.text.toString())
            }

            val numberOfRowsUpdated = database.update(
                "clients",
                values,
                "user_id = ?",
                arrayOf(userId)
            )

            if (numberOfRowsUpdated > 0) {

            } else {
                //error al actualizar
            }
        }
    }
}