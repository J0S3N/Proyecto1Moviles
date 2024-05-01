package com.example.primerproyecto.administrativa

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.primerproyecto.AdminHelper
import com.example.primerproyecto.R
import kotlin.math.pow

class Agregar_Prestamo : AppCompatActivity()  {
    lateinit var idEditText: EditText
    lateinit var loanTypeSpinner: Spinner
    lateinit var loanPeriodSpinner: Spinner
    lateinit var calculateButton: Button
    lateinit var monthlyPaymentTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.administrativa_agregar_prestamo)

        idEditText = findViewById(R.id.idEditText)
        loanTypeSpinner = findViewById(R.id.loanTypeSpinner)
        loanPeriodSpinner = findViewById(R.id.loanPeriodSpinner)
        calculateButton = findViewById(R.id.calculateButton)
        monthlyPaymentTextView = findViewById(R.id.monthlyPaymentTextView)

        setupSpinners()
        calculateButton.setOnClickListener { calculateLoan() }
    }

    private fun setupSpinners() {
        // Configurar el spinner de tipo de préstamo
        val loanTypes = arrayOf("Hipotecario", "Educación", "Personal", "Viajes")
        val loanTypeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, loanTypes)
        loanTypeSpinner.adapter = loanTypeAdapter

        // Configurar el spinner de período del préstamo
        val loanPeriods = arrayOf("3 años", "5 años", "10 años")
        val loanPeriodAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, loanPeriods)
        loanPeriodSpinner.adapter = loanPeriodAdapter
    }

    @SuppressLint("StringFormatInvalid")
    private fun calculateLoan() {
        val id = idEditText.text.toString()
        val loanType = loanTypeSpinner.selectedItem.toString()
        val loanPeriod = loanPeriodSpinner.selectedItem.toString().filter { it.isDigit() }.toInt()
        val interestRates = mapOf("Hipotecario" to 7.5, "Educación" to 8.0, "Personal" to 10.0, "Viajes" to 12.0)

        // Obtener la información del cliente basado en la cédula
        val clientInfo = getClientInfo(id)
        val maxLoanAmount = clientInfo!!.salary * 0.45
        val interestRate = interestRates[loanType] ?: 0.0
        val monthlyPayment = calculateMonthlyPayment(maxLoanAmount, interestRate, loanPeriod)

        saveLoan(clientInfo.clientId, maxLoanAmount, loanType, loanPeriod, interestRate)

        monthlyPaymentTextView.text = getString(R.string.monthly_payment, monthlyPayment)
    }

    private fun getClientInfo(id: String): ClientInfo? {
        val admin = AdminHelper(this, "administracion", null, 1)
        val database = admin.readableDatabase
        var clientInfo: ClientInfo? = null

        val cursor = database.query(
            "clients", // Nombre de la tabla
            arrayOf("client_id", "salary"), // Columnas que quieres retornar
            "cedula = ?", // Criterios de selección
            arrayOf(id), // Valores para los criterios de selección
            null, // Group by
            null, // Having
            null  // Order by
        )

        if (cursor.moveToFirst()) {
            val clientId = cursor.getInt(cursor.getColumnIndexOrThrow("client_id"))
            val salary = cursor.getDouble(cursor.getColumnIndexOrThrow("salary"))
            clientInfo = ClientInfo(clientId = clientId, salary = salary)
        }
        cursor.close()
        return clientInfo
    }

    private fun calculateMonthlyPayment(amount: Double, annualInterestRate: Double, years: Int): Double {
        val monthlyInterestRate = annualInterestRate / 100 / 12
        val numberOfPayments = years * 12
        return if (monthlyInterestRate != 0.0) {
            val discountFactor = (1 - (1 + monthlyInterestRate).pow(-numberOfPayments.toDouble())) / monthlyInterestRate
            amount / discountFactor
        } else {
            amount / numberOfPayments
        }
    }

    private fun saveLoan(clientId: Int, loanAmount: Double, loanType: String, loanPeriod: Int, interestRate: Double) {
        val admin = AdminHelper(this, "administracion", null, 1)
        val database = admin.writableDatabase

        val values = ContentValues().apply {
            put("client_id", clientId)
            put("amount", loanAmount)
            put("loan_type", loanType)
            put("period", loanPeriod)
            put("interest_rate", interestRate)
            // Añade aquí cualquier otro detalle que necesites guardar
        }

        val newRowId = database.insert("loans", null, values)
        if (newRowId == -1L) {
            // Manejar el caso de error al insertar
        } else {
            // El préstamo se guardó correctamente
        }
    }
}