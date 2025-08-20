package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.models.Calculator

class MainActivity : AppCompatActivity() {

    lateinit var edtNumber1: EditText
    lateinit var edtNumber2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtNumber1 = findViewById(R.id.edtNumber1)
        edtNumber2 = findViewById(R.id.edtNumber2)
    }

    fun buttonClick(v: View) {
        val num1 = edtNumber1.text.toString().toDoubleOrNull() ?: 0.0
        val num2 = edtNumber2.text.toString().toDoubleOrNull() ?: 0.0

        val calculator = Calculator(num1, num2)
        var ans = 0.0

        when (v.id) {
            R.id.btnPlus -> ans = calculator.add()
            R.id.btnMinus -> ans = calculator.subtract()
            R.id.btnMultiply -> ans = calculator.multiply()
            R.id.btnDivide -> ans = calculator.divide()
        }

        val intent = Intent(this, DisplayActivity::class.java)
        intent.putExtra("answer", ans)
        startActivity(intent)
        finish()
    }
}
