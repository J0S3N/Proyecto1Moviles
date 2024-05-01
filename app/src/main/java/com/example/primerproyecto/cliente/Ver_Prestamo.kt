package com.example.primerproyecto.cliente

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.primerproyecto.AdminHelper
import com.example.primerproyecto.R

class Ver_Prestamo : AppCompatActivity() {
    private lateinit var loansListView: ListView
    private lateinit var paymentEditText: EditText
    private lateinit var payButton: Button
    val userId = intent.getStringExtra("USER_ID")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cliente_ver_prestamo)

        loansListView = findViewById(R.id.loansListView)
        paymentEditText = findViewById(R.id.paymentEditText)
        payButton = findViewById(R.id.payButton)



        loadLoans()
        loansListView.setOnItemClickListener { parent, view, position, id ->
            // Lógica para seleccionar un préstamo de la lista
        }

        /*payButton.setOnClickListener {
            val selectedLoanId = // Obtener el ID del préstamo seleccionado
            val paymentAmount = paymentEditText.text.toString().toDoubleOrNull() ?: return@setOnClickListener
            makePayment(selectedLoanId, paymentAmount)
        }*/
    }

    private fun loadLoans() {
        val admin = AdminHelper(this, "administracion", null, 1)
        val database = admin.readableDatabase
        val loansList = mutableListOf<Loan>()

        val cursor = database.query(
            "loans", // Nombre de la tabla
            null, // null para retornar todas las columnas
            "client_id = ?", // Criterios de selección
            arrayOf(userId.toString()), // Valores para los criterios de selección
            null, // Group by
            null, // Having
            null  // Order by
        )

        while (cursor.moveToNext()) {
            val loanId = cursor.getInt(cursor.getColumnIndexOrThrow("loan_id"))
            val amount = cursor.getDouble(cursor.getColumnIndexOrThrow("amount"))
            val loanType = cursor.getString(cursor.getColumnIndexOrThrow("loan_type"))
            val period = cursor.getInt(cursor.getColumnIndexOrThrow("period"))
            val interestRate = cursor.getDouble(cursor.getColumnIndexOrThrow("interest_rate"))
            loansList.add(Loan(loanId, amount, loanType, period, interestRate))
        }
        cursor.close()

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            loansList.map { loan -> "Préstamo ${loan.loanType}: ${loan.amount}" }
        )
        loansListView.adapter = adapter
    }

    private fun makePayment(loanId: Int, amount: Double) {
        // Código para registrar el pago en la base de datos y actualizar la información del préstamo
    }
}