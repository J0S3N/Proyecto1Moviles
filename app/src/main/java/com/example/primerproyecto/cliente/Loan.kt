package com.example.primerproyecto.cliente

data class Loan(
    val loanId: Int,
    val amount: Double,
    val loanType: String,
    val period: Int,
    val interestRate: Double
)