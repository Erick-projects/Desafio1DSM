package com.example.ejercicio1desafio1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etNota1: EditText
    private lateinit var etNota2: EditText
    private lateinit var etNota3: EditText
    private lateinit var etNota4: EditText
    private lateinit var etNota5: EditText
    private lateinit var btnCalcular: Button
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNombre = findViewById(R.id.etNombre)
        etNota1 = findViewById(R.id.etNota1)
        etNota2 = findViewById(R.id.etNota2)
        etNota3 = findViewById(R.id.etNota3)
        etNota4 = findViewById(R.id.etNota4)
        etNota5 = findViewById(R.id.etNota5)
        btnCalcular = findViewById(R.id.btnCalcular)
        tvResultado = findViewById(R.id.tvResultado)

        btnCalcular.setOnClickListener { calcularPromedio() }
    }

    private fun calcularPromedio() {
        val nombre = etNombre.text.toString()
        val nota1 = etNota1.text.toString().toDoubleOrNull()
        val nota2 = etNota2.text.toString().toDoubleOrNull()
        val nota3 = etNota3.text.toString().toDoubleOrNull()
        val nota4 = etNota4.text.toString().toDoubleOrNull()
        val nota5 = etNota5.text.toString().toDoubleOrNull()

        if (nombre.isEmpty() || nota1 == null || nota2 == null || nota3 == null || nota4 == null || nota5 == null) {
            Toast.makeText(this, "Por favor, complete todos los campos con valores válidos.", Toast.LENGTH_LONG).show()
            return
        }

        if (nota1 !in 0.0..10.0 || nota2 !in 0.0..10.0 || nota3 !in 0.0..10.0 || nota4 !in 0.0..10.0 || nota5 !in 0.0..10.0) {
            Toast.makeText(this, "Las notas deben estar entre 0 y 10.", Toast.LENGTH_LONG).show()
            return
        }

        val promedio = (nota1 * 0.15) + (nota2 * 0.15) + (nota3 * 0.20) + (nota4 * 0.25) + (nota5 * 0.25)

        val resultado = if (promedio >= 6) "Aprobado" else "Reprobado"

        tvResultado.text = "Nota Final: $promedio\nResultado: $resultado"
    }
}