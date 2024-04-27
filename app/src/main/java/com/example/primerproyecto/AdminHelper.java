package com.example.primerproyecto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminHelper extends SQLiteOpenHelper {
    public AdminHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase BaseDeDatos) {
        // Crear tabla de usuarios
        BaseDeDatos.execSQL("CREATE TABLE IF NOT EXISTS users (" +
                "user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT NOT NULL, " +
                "password TEXT NOT NULL, " +
                "role TEXT NOT NULL CHECK (role IN ('admin', 'client')));");

        // Crear tabla de clientes
        BaseDeDatos.execSQL("CREATE TABLE IF NOT EXISTS clients (" +
                "client_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cedula TEXT NOT NULL, " +
                "name TEXT NOT NULL, " +
                "salary REAL NOT NULL, " +
                "phone TEXT NOT NULL, " +
                "birth_date TEXT NOT NULL, " +
                "marital_status TEXT NOT NULL, " +
                "address TEXT NOT NULL, " +
                "user_id INTEGER, " +
                "FOREIGN KEY(user_id) REFERENCES users(user_id));");

        // Crear tabla de préstamos
        BaseDeDatos.execSQL("CREATE TABLE IF NOT EXISTS loans (" +
                "loan_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "client_id INTEGER NOT NULL, " +
                "loan_type TEXT NOT NULL CHECK (loan_type IN ('hipotecario', 'educacion', 'personal', 'viajes')), " +
                "amount REAL NOT NULL, " +
                "period INTEGER NOT NULL, " +
                "interest_rate REAL NOT NULL, " +
                "monthly_payment REAL NOT NULL, " +
                "FOREIGN KEY (client_id) REFERENCES clients (client_id));");

        // Crear tabla de ahorros
        BaseDeDatos.execSQL("CREATE TABLE IF NOT EXISTS savings (" +
                "saving_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "client_id INTEGER NOT NULL, " +
                "saving_type TEXT NOT NULL CHECK (saving_type IN ('navideno', 'escolar', 'marchamo', 'extraordinario')), " +
                "monthly_contribution REAL NOT NULL, " +
                "FOREIGN KEY (client_id) REFERENCES clients (client_id));");

        // Añadir admin
        BaseDeDatos.execSQL("INSERT INTO users (username, password, role) VALUES ('admin', 'abcd1234', 'admin');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
