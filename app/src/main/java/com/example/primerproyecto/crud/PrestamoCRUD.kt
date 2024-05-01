package com.example.primerproyecto.crud

import android.content.ContentValues

import android.database.Cursor

import android.database.sqlite.SQLiteDatabase


class PrestamoCRUD(private val BaseDeDatos: SQLiteDatabase) {
    // Método para añadir un nuevo préstamo
    fun agregarPrestamo(
        client_id: Int,
        loan_type: String?,
        amount: Double,
        period: Int,
        interest_rate: Double,
        monthly_payment: Double
    ): Boolean {
        val valores = ContentValues()
        valores.put("client_id", client_id)
        valores.put("loan_type", loan_type)
        valores.put("amount", amount)
        valores.put("period", period)
        valores.put("interest_rate", interest_rate)
        valores.put("monthly_payment", monthly_payment)
        val resultado = BaseDeDatos.insert("loans", null, valores)
        return resultado != -1L // Retorna true si la inserción fue exitosa
    }

    // Método para obtener los datos de un préstamo
    fun obtenerPrestamo(loan_id: Int): Cursor {
        return BaseDeDatos.query(
            "loans",
            null,
            "loan_id = ?",
            arrayOf(loan_id.toString()),
            null,
            null,
            null
        )
    }

    // Método para actualizar los datos de un préstamo
    fun actualizarPrestamo(
        loan_id: Int,
        client_id: Int,
        loan_type: String?,
        amount: Double,
        period: Int,
        interest_rate: Double,
        monthly_payment: Double
    ): Boolean {
        val valores = ContentValues()
        valores.put("client_id", client_id)
        valores.put("loan_type", loan_type)
        valores.put("amount", amount)
        valores.put("period", period)
        valores.put("interest_rate", interest_rate)
        valores.put("monthly_payment", monthly_payment)
        val resultado =
            BaseDeDatos.update("loans", valores, "loan_id = ?", arrayOf(loan_id.toString()))
        return resultado > 0 // Retorna true si la actualización fue exitosa
    }

    // Método para eliminar un préstamo
    fun eliminarPrestamo(loan_id: Int): Boolean {
        val resultado = BaseDeDatos.delete("loans", "loan_id = ?", arrayOf(loan_id.toString()))
        return resultado > 0 // Retorna true si la eliminación fue exitosa
    }
}

