package com.example.preparcialayd.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.preparcialayd.presentation.CriptoPresenter
import com.example.preparcialayd.R
import com.example.preparcialayd.injector.CriptoInjector

val MONEDAS_NAMES = listOf("USD", "EUR", "CAD", "JPY", "RUB", "GBP", "KRW", "PLN")

class CriptoView() : AppCompatActivity() {
    private lateinit var presenter: CriptoPresenter
    private lateinit var spinner: Spinner
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var priceTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initModule()
        initProperties()
        initListeners()
        initObservers()
    }

    private fun initModule() {
        CriptoInjector.init(this)
        presenter = CriptoInjector.presenter
    }

    private fun initListeners() {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val monedaSeleccionada = MONEDAS_NAMES[position]
                presenter.fetchPrice(monedaSeleccionada)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun initObservers() {
        presenter.observer.subscribe { result ->
            onPrice(result.first, result.second)
        }

    }

    private fun initProperties() {
        priceTextView = findViewById<TextView>(R.id.textPrecio)
        spinner = findViewById<Spinner>(R.id.spinnerMonedas)
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, MONEDAS_NAMES)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun onPrice(symbol: String, price: Int) {
        val coin = "$symbol – $price"
        runOnUiThread {
            priceTextView.text = coin
        }
    }
}