package com.example.primerproyecto.crud

import android.content.ContentValues

import android.database.Cursor

import android.database.sqlite.SQLiteDatabase


class AhorroCRUD(private val BaseDeDatos: SQLiteDatabase) {
    // Método para añadir un nuevo ahorro
    fun agregarAhorro(client_id: Int, saving_type: String?, monthly_contribution: Double): Boolean {
        val valores = ContentValues()
        valores.put("client_id", client_id)
        valores.put("saving_type", saving_type)
        valores.put("monthly_contribution", monthly_contribution)
        val resultado = BaseDeDatos.insert("savings", null, valores)
        return resultado != -1L // Retorna true si la inserción fue exitosa
    }

    // Método para obtener los datos de un ahorro
    fun obtenerAhorro(saving_id: Int): Cursor {
        return BaseDeDatos.query(
            "savings",
            null,
            "saving_id = ?",
            arrayOf(saving_id.toString()),
            null,
            null,
            null
        )
    }

    // Método para actualizar los datos de un ahorro
    fun actualizarAhorro(
        saving_id: Int,
        client_id: Int,
        saving_type: String?,
        monthly_contribution: Double
    ): Boolean {
        val valores = ContentValues()
        valores.put("client_id", client_id)
        valores.put("saving_type", saving_type)
        valores.put("monthly_contribution", monthly_contribution)
        val resultado =
            BaseDeDatos.update("savings", valores, "saving_id = ?", arrayOf(saving_id.toString()))
        return resultado > 0 // Retorna true si la actualización fue exitosa
    }

    // Método para eliminar un ahorro
    fun eliminarAhorro(saving_id: Int): Boolean {
        val resultado =
            BaseDeDatos.delete("savings", "saving_id = ?", arrayOf(saving_id.toString()))
        return resultado > 0 // Retorna true si la eliminación fue exitosa
    }
}