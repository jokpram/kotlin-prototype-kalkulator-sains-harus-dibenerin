package com.joko.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView
    private lateinit var tvHistory: TextView
    private lateinit var calculator: Calculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        calculator = Calculator()
        setupClickListeners()
    }

    private fun initializeViews() {
        tvResult = findViewById(R.id.tvResult)
        tvHistory = findViewById(R.id.tvHistory)
    }

    private fun setupClickListeners() {
        // Number buttons
        findViewById<Button>(R.id.btnZero).setOnClickListener { appendNumber("0") }
        findViewById<Button>(R.id.btnOne).setOnClickListener { appendNumber("1") }
        findViewById<Button>(R.id.btnTwo).setOnClickListener { appendNumber("2") }
        findViewById<Button>(R.id.btnThree).setOnClickListener { appendNumber("3") }
        findViewById<Button>(R.id.btnFour).setOnClickListener { appendNumber("4") }
        findViewById<Button>(R.id.btnFive).setOnClickListener { appendNumber("5") }
        findViewById<Button>(R.id.btnSix).setOnClickListener { appendNumber("6") }
        findViewById<Button>(R.id.btnSeven).setOnClickListener { appendNumber("7") }
        findViewById<Button>(R.id.btnEight).setOnClickListener { appendNumber("8") }
        findViewById<Button>(R.id.btnNine).setOnClickListener { appendNumber("9") }

        // Operator buttons
        findViewById<Button>(R.id.btnAdd).setOnClickListener { appendOperator("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { appendOperator("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { appendOperator("×") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { appendOperator("÷") }
        findViewById<Button>(R.id.btnPercent).setOnClickListener { appendOperator("%") }

        // Other buttons
        findViewById<Button>(R.id.btnDecimal).setOnClickListener { appendDecimal() }
        findViewById<Button>(R.id.btnEquals).setOnClickListener { calculate() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { clear() }
        findViewById<Button>(R.id.btnBracket).setOnClickListener { toggleBracket() }
    }

    private fun appendNumber(number: String) {
        calculator.appendNumber(number)
        updateDisplay()
    }

    private fun appendOperator(operator: String) {
        calculator.appendOperator(operator)
        updateDisplay()
        updateHistory()
    }

    private fun appendDecimal() {
        calculator.appendDecimal()
        updateDisplay()
    }

    private fun calculate() {
        val result = calculator.calculate()
        tvResult.text = result
        updateHistory()
    }

    private fun clear() {
        calculator.clear()
        updateDisplay()
        tvHistory.text = ""
    }

    private fun toggleBracket() {
        calculator.toggleBracket()
        updateDisplay()
    }

    private fun updateDisplay() {
        tvResult.text = calculator.getCurrentInput()
    }

    private fun updateHistory() {
        val history = "${calculator.getPreviousInput()} ${calculator.getCurrentOperator()}"
        tvHistory.text = history
    }
}