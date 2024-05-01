package com.example.primerproyecto.crud
import android.content.ContentValues

import android.database.Cursor

import android.database.sqlite.SQLiteDatabase


class ClienteCRUD(private val BaseDeDatos: SQLiteDatabase) {
    // Método para añadir un nuevo cliente
    fun agregarCliente(
        cedula: String?,
        name: String?,
        salary: Double,
        phone: String?,
        birth_date: String?,
        marital_status: String?,
        address: String?,
        user_id: Int
    ): Boolean {
        val valores = ContentValues()
        valores.put("cedula", cedula)
        valores.put("name", name)
        valores.put("salary", salary)
        valores.put("phone", phone)
        valores.put("birth_date", birth_date)
        valores.put("marital_status", marital_status)
        valores.put("address", address)
        valores.put("user_id", user_id)
        val resultado = BaseDeDatos.insert("clients", null, valores)
        return resultado != -1L // Retorna true si la inserción fue exitosa
    }

    // Método para leer los datos de un cliente
    fun obtenerCliente(client_id: Int): Cursor {
        return BaseDeDatos.query(
            "clients",
            null,
            "client_id = ?",
            arrayOf(client_id.toString()),
            null,
            null,
            null
        )
    }

    // Método para actualizar los datos de un cliente
    fun actualizarCliente(
        client_id: Int,
        cedula: String?,
        name: String?,
        salary: Double,
        phone: String?,
        birth_date: String?,
        marital_status: String?,
        address: String?
    ): Boolean {
        val valores = ContentValues()
        valores.put("cedula", cedula)
        valores.put("name", name)
        valores.put("salary", salary)
        valores.put("phone", phone)
        valores.put("birth_date", birth_date)
        valores.put("marital_status", marital_status)
        valores.put("address", address)
        val resultado =
            BaseDeDatos.update("clients", valores, "client_id = ?", arrayOf(client_id.toString()))
        return resultado > 0 // Retorna true si la actualización fue exitosa
    }

    // Método para eliminar un cliente
    fun eliminarCliente(client_id: Int): Boolean {
        val resultado =
            BaseDeDatos.delete("clients", "client_id = ?", arrayOf(client_id.toString()))
        return resultado > 0 // Retorna true si la eliminación fue exitosa
    }
}

