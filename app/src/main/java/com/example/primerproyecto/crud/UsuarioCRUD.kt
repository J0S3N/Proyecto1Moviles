package com.example.primerproyecto.crud

import android.content.ContentValues

import android.database.Cursor

import android.database.sqlite.SQLiteDatabase


class UsuarioCRUD(private val BaseDeDatos: SQLiteDatabase) {

    // Método para añadir un nuevo usuario
    fun agregarUsuario(username: String?, password: String?, role: String?): Boolean {
        val valores = ContentValues()
        valores.put("username", username)
        valores.put("password", password)
        valores.put("role", role)
        val resultado = BaseDeDatos.insert("users", null, valores)
        return resultado != -1L // Retorna true si la inserción fue exitosa
    }

    // Método para obtener los datos de un usuario
    fun obtenerUsuario(user_id: Int): Cursor {
        return BaseDeDatos.query(
            "users",
            null,
            "user_id = ?",
            arrayOf(user_id.toString()),
            null,
            null,
            null
        )
    }

    // Método para actualizar los datos de un usuario
    fun actualizarUsuario(
        user_id: Int,
        username: String?,
        password: String?,
        role: String?
    ): Boolean {
        val valores = ContentValues()
        valores.put("username", username)
        valores.put("password", password)
        valores.put("role", role)
        val resultado =
            BaseDeDatos.update("users", valores, "user_id = ?", arrayOf(user_id.toString()))
        return resultado > 0 // Retorna true si la actualización fue exitosa
    }

    // Método para eliminar un usuario
    fun eliminarUsuario(user_id: Int): Boolean {
        val resultado = BaseDeDatos.delete("users", "user_id = ?", arrayOf(user_id.toString()))
        return resultado > 0 // Retorna true si la eliminación fue exitosa
    }
}

