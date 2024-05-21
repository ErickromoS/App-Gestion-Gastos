package com.example.tdmpa_1p_ex_75441

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txtMonto = findViewById<EditText>(R.id.txtMonto)
        var radioGroup = findViewById<RadioGroup>(R.id.rbGroup)
        var btnIngresarDatos = findViewById<Button>(R.id.btnIngresarDatos)
        var btnPromedioTotal = findViewById<Button>(R.id.btnPromedioTotal)
        var txtTotal = findViewById<TextView>(R.id.txtTotal)
        var txtPromedioTotal = findViewById<TextView>(R.id.txtPromedioTotal)
        var txtTotalCategoria = findViewById<TextView>(R.id.txtTotalCategoria)
        var btnTotal = findViewById<Button>(R.id.btnTotal)
        var btnCalcularTotalCategoria = findViewById<Button>(R.id.btnCalcularTotalCategoria)
        var btnCalcularPromedioCategoria = findViewById<Button>(R.id.btnCalcularPromedioCategoria)
        var txtPromedioCategoria = findViewById<TextView>(R.id.txtPromedioCategoria)

        btnIngresarDatos.setOnClickListener{
            var numero = txtMonto.text.toString()
            var radioSeleccionado = radioGroup.checkedRadioButtonId;
            when (radioSeleccionado){
                R.id.radioComida ->{
                    var numero = txtMonto.text.toString().toInt()
                    listaComida.add(numero.toDouble())
                    Toast.makeText(this, "Se agrego a comida", Toast.LENGTH_SHORT).show()

                }
                R.id.radioRopa ->{
                    var numero = txtMonto.text.toString().toInt()
                    listaRopa.add(numero.toDouble())
                    Toast.makeText(this, "Se agrego a ropa", Toast.LENGTH_SHORT).show()

                }
                R.id.radioDeudas ->{
                    var numero = txtMonto.text.toString().toInt()
                    listaDeudas.add(numero.toDouble())
                    Toast.makeText(this, "Se agrego a deudas", Toast.LENGTH_SHORT).show()
                }
                R.id.radioGustos ->{
                    var numero = txtMonto.text.toString().toInt()
                    listaGustos.add(numero.toDouble())
                    Toast.makeText(this, "Se agrego a gustos", Toast.LENGTH_SHORT).show()

                }
                else->{
                    Toast.makeText(this, "No se selecciono ningun radio button", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnTotal.setOnClickListener{
            txtTotal.text = totalT().toString()


        }
        btnPromedioTotal.setOnClickListener{
            txtPromedioTotal.text = promedioTotal().toString()
        }

        btnCalcularTotalCategoria.setOnClickListener{
            val radioButtonId = radioGroup.checkedRadioButtonId
            val totalCategoria = calcularTotalPorCategoria(radioButtonId)
            txtTotalCategoria.text = totalCategoria.toString()
        }

        btnCalcularPromedioCategoria.setOnClickListener{
            val radioButtonId = radioGroup.checkedRadioButtonId
            val promedioCategoria = calcularPromedioPorCategoria(radioButtonId)
            txtPromedioCategoria.text = promedioCategoria.toString()
        }


    }

    var listaComida: MutableList<Double> = mutableListOf();
    var listaRopa: MutableList<Double> = mutableListOf();
    var listaDeudas: MutableList<Double> = mutableListOf();
    var listaGustos: MutableList<Double> = mutableListOf();

    fun totalT(): Double{
        val suma = listaComida.sum() + listaGustos.sum() + listaRopa.sum() + listaDeudas.sum()
        return suma
    }

    fun promedioTotal(): Double {
        val totalElementos = listaComida.size + listaGustos.size + listaRopa.size + listaDeudas.size
        val sumaTotal = listaComida.sum() + listaGustos.sum() + listaRopa.sum() + listaDeudas.sum()

        if (totalElementos == 0) {
            return 0.0 // Evitar división por cero si no hay elementos
        }

        val promedio = sumaTotal / totalElementos.toDouble()
        return promedio
    }

    fun calcularTotalPorCategoria(radioButtonId: Int): Double {
        when (radioButtonId) {
            R.id.radioComida -> return listaComida.sum()
            R.id.radioRopa -> return listaRopa.sum()
            R.id.radioDeudas -> return listaDeudas.sum()
            R.id.radioGustos -> return listaGustos.sum()
            else -> return 0.0 // Valor predeterminado si no se selecciona una categoría válida
        }
    }

    fun calcularPromedioPorCategoria(radioButtonId: Int): Double {
        when (radioButtonId) {
            R.id.radioComida -> {
                val lista = listaComida
                if (lista.isNotEmpty()) {
                    return lista.sum() / lista.size
                }
            }
            R.id.radioRopa -> {
                val lista = listaRopa
                if (lista.isNotEmpty()) {
                    return lista.sum() / lista.size
                }
            }
            R.id.radioDeudas -> {
                val lista = listaDeudas
                if (lista.isNotEmpty()) {
                    return lista.sum() / lista.size
                }
            }
            R.id.radioGustos -> {
                val lista = listaGustos
                if (lista.isNotEmpty()) {
                    return lista.sum() / lista.size
                }
            }
        }
        return 0.0 // Valor predeterminado si no se selecciona una categoría válida o la lista está vacía
    }

}