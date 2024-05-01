package com.example.primerproyecto.cliente

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.primerproyecto.R
import kotlin.math.pow

class Calcular_Cuota : AppCompatActivity()  {
    private lateinit var loanTypeSpinner: Spinner
    private lateinit var loanPeriodSpinner: Spinner
    private lateinit var loanAmountEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var monthlyPaymentTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cliente_calcular_cuota)

        loanTypeSpinner = findViewById(R.id.loanTypeSpinner)
        loanPeriodSpinner = findViewById(R.id.loanPeriodSpinner)
        loanAmountEditText = findViewById(R.id.loanAmountEditText)
        calculateButton = findViewById(R.id.calculateButton)
        monthlyPaymentTextView = findViewById(R.id.monthlyPaymentTextView)

        setupSpinners()
        calculateButton.setOnClickListener { calculateMonthlyPayment() }
    }

    private fun setupSpinners() {
        val loanTypes = arrayOf("Hipotecario", "Educación", "Personal", "Viajes")
        val loanTypeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, loanTypes)
        loanTypeSpinner.adapter = loanTypeAdapter

        val loanPeriods = arrayOf("3 años", "5 años", "10 años")
        val loanPeriodAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, loanPeriods)
        loanPeriodSpinner.adapter = loanPeriodAdapter
    }

    private fun calculateMonthlyPayment() {
        val loanType = loanTypeSpinner.selectedItem.toString()
        val loanPeriod = loanPeriodSpinner.selectedItem.toString().filter { it.isDigit() }.toInt()
        val loanAmount = loanAmountEditText.text.toString().toDoubleOrNull() ?: return
        val interestRates = mapOf("Hipotecario" to 7.5, "Educación" to 8.0, "Personal" to 10.0, "Viajes" to 12.0)
        val annualInterestRate = interestRates[loanType] ?: 0.0
        val monthlyInterestRate = annualInterestRate / 12 / 100
        val numberOfPayments = loanPeriod * 12

        val monthlyPayment = if (monthlyInterestRate != 0.0) {
            val discountFactor = (1 - (1 + monthlyInterestRate).pow(-numberOfPayments)) / monthlyInterestRate
            loanAmount / discountFactor
        } else {
            loanAmount / numberOfPayments
        }

        monthlyPaymentTextView.text = getString(R.string.monthly_payment_result, monthlyPayment)
    }
}