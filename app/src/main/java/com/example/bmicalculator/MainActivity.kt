package com.example.bmicalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText=findViewById<EditText>(R.id.editTextTextPersonName)
        val heightText=findViewById<EditText>(R.id.cvHeight)

        val button=findViewById<Button>(R.id.button)
        button.setOnClickListener{
            val weight=weightText.text.toString()
            val height=heightText.text.toString()
            if(validateInput(weight,height)) {


                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
//            result with two decimal places
                val bmi2digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2digits)
                Toast.makeText(this, "BMI calculated", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun validateInput(weight:String?,height:String?):Boolean{
        return when {
            weight.isNullOrEmpty()->{
                Toast.makeText(this,"Weight is empty ",Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this,"Height is empty ",Toast.LENGTH_SHORT).show()
                return false
            }
            else->{
                return true
            }
        }

    }

    private fun displayResult(bmi:Float){
        val result=findViewById<TextView>(R.id.result)
        val resultValue=findViewById<TextView>(R.id.resultValue)
        val range=findViewById<TextView>(R.id.resultRange)
        resultValue.text=bmi.toString()
        range.text="(Normal range is 18.5 - 24.9)"
        var resultText=""
        var color=0
        when{
            bmi<18.50 ->{
                resultText="Underweight"
                color=R.color.underWight
            }
            bmi in 18.50..22.49->{
                resultText="Healthy"
                color=R.color.Normal
            }
            bmi in 25.00..29.99->{
                resultText="Overweight"
                color=R.color.overweight
            }
            bmi>29.99->{
                resultText="Overweight"
                color=R.color.Obese
            }
        }
        result.setTextColor(ContextCompat.getColor(this,color))
        result.text=resultText
    }
}