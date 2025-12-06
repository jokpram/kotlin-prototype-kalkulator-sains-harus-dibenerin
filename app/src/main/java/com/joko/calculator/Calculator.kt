package com.joko.calculator

class Calculator {
    private var currentInput = "0"
    private var currentOperator = ""
    private var previousInput = ""
    private var shouldResetInput = false

    fun appendNumber(number: String) {
        if (shouldResetInput) {
            currentInput = number
            shouldResetInput = false
        } else {
            if (currentInput == "0") {
                currentInput = number
            } else {
                currentInput += number
            }
        }
    }

    fun appendOperator(operator: String) {
        if (currentOperator.isNotEmpty()) {
            calculate()
        }
        previousInput = currentInput
        currentOperator = operator
        shouldResetInput = true
    }

    fun appendDecimal() {
        if (shouldResetInput) {
            currentInput = "0."
            shouldResetInput = false
        } else if (!currentInput.contains(".")) {
            currentInput += "."
        }
    }

    fun calculate(): String {
        if (previousInput.isEmpty() || currentOperator.isEmpty()) {
            return currentInput
        }

        val prev = previousInput.toDouble()
        val current = currentInput.toDouble()
        var result = 0.0

        when (currentOperator) {
            "+" -> result = prev + current
            "-" -> result = prev - current
            "×" -> result = prev * current
            "÷" -> {
                if (current != 0.0) {
                    result = prev / current
                } else {
                    clear()
                    return "Error"
                }
            }
            "%" -> result = prev % current
        }

        // Format hasil untuk menghilangkan .0 jika tidak perlu
        currentInput = if (result % 1 == 0.0) {
            result.toLong().toString()
        } else {
            result.toString()
        }

        previousInput = ""
        currentOperator = ""
        shouldResetInput = true

        return currentInput
    }

    fun clear() {
        currentInput = "0"
        previousInput = ""
        currentOperator = ""
        shouldResetInput = false
    }

    fun toggleBracket(): String {
        // Implementasi sederhana untuk bracket
        if (currentInput.contains("(")) {
            currentInput = currentInput.replace("(", "") + ")"
        } else {
            currentInput = "($currentInput"
        }
        return currentInput
    }

    fun getCurrentInput(): String = currentInput
    fun getPreviousInput(): String = previousInput
    fun getCurrentOperator(): String = currentOperator

    fun deleteLast() {
        if (currentInput.length > 1) {
            currentInput = currentInput.substring(0, currentInput.length - 1)
        } else {
            currentInput = "0"
        }
    }
}