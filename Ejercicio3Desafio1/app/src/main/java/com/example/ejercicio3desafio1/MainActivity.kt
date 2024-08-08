package com.example.ejercicio3desafio1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etOperando1: EditText
    private lateinit var etOperando2: EditText
    private lateinit var spOperaciones: Spinner
    private lateinit var btnCalcular: Button
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etOperando1 = findViewById(R.id.etOperando1)
        etOperando2 = findViewById(R.id.etOperando2)
        spOperaciones = findViewById(R.id.spOperaciones)
        btnCalcular = findViewById(R.id.btnCalcular)
        tvResultado = findViewById(R.id.tvResultado)

        btnCalcular.setOnClickListener { realizarOperacion() }
    }

    private fun realizarOperacion() {
        val operando1Text = etOperando1.text.toString()
        val operando2Text = etOperando2.text.toString()

        if (operando1Text.isEmpty() || operando2Text.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese ambos operandos.", Toast.LENGTH_SHORT).show()
            return
        }

        val operando1 = operando1Text.toDoubleOrNull()
        val operando2 = operando2Text.toDoubleOrNull()

        if (operando1 == null || operando2 == null) {
            Toast.makeText(this, "Los operandos deben ser números válidos.", Toast.LENGTH_SHORT).show()
            return
        }

        val operacion = spOperaciones.selectedItem.toString()
        val resultado = when (operacion) {
            "Suma" -> operando1 + operando2
            "Resta" -> operando1 - operando2
            "Multiplicación" -> operando1 * operando2
            "División" -> {
                if (operando2 == 0.0) {
                    Toast.makeText(this, "No se puede dividir por cero.", Toast.LENGTH_SHORT).show()
                    return
                }
                operando1 / operando2
            }
            else -> {
                Toast.makeText(this, "Operación no válida.", Toast.LENGTH_SHORT).show()
                return
            }
        }

        tvResultado.text = "Resultado: ${String.format("%.2f", resultado)}"
    }
}