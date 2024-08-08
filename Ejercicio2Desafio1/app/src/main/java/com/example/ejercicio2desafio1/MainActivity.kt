package com.example.ejercicio2desafio1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etSalarioBase: EditText
    private lateinit var btnCalcular: Button
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNombre = findViewById(R.id.etNombre)
        etSalarioBase = findViewById(R.id.etSalarioBase)
        btnCalcular = findViewById(R.id.btnCalcular)
        tvResultado = findViewById(R.id.tvResultado)

        btnCalcular.setOnClickListener { calcularDescuentos() }
    }

    private fun calcularDescuentos() {
        val nombre = etNombre.text.toString()
        val salarioBase = etSalarioBase.text.toString().toDoubleOrNull()

        if (nombre.isEmpty() || salarioBase == null) {
            Toast.makeText(this, "Por favor, complete todos los campos con valores válidos.", Toast.LENGTH_LONG).show()
            return
        }

        // Cálculo de AFP e ISSS
        val afp = salarioBase * 0.0725
        val isss = salarioBase * 0.03

        // Cálculo de la renta
        val renta = calcularRenta(salarioBase)

        // Cálculo del salario neto
        val salarioNeto = salarioBase - afp - isss - renta

        val resultado = """
            Nombre: $nombre
            Salario Base: $${String.format("%.2f", salarioBase)}
            AFP (7.25%): $${String.format("%.2f", afp)}
            ISSS (3%): $${String.format("%.2f", isss)}
            Renta: $${String.format("%.2f", renta)}
            Salario Neto: $${String.format("%.2f", salarioNeto)}
        """.trimIndent()

        tvResultado.text = resultado
    }

    private fun calcularRenta(salario: Double): Double {
        return when {
            salario <= 472.00 -> 0.0
            salario <= 895.24 -> (salario - 472.00) * 0.10 + 17.67
            salario <= 2038.10 -> (salario - 895.24) * 0.20 + 60.00
            else -> (salario - 2038.10) * 0.30 + 288.57
        }
    }
}