package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weighttext=findViewById<EditText>(R.id.et_wt)
        val heigttext=findViewById<EditText>(R.id.et_ht)
        val buttonn=findViewById<Button>(R.id.btn_calc)
        buttonn.setOnClickListener{
            val weight=weighttext.text.toString()
            val height=heigttext.text.toString()
            if(validateInputs(weight,height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2d = String.format("%.2f", bmi).toFloat()
                displayResults(bmi2d) //we gonna disp in tvs of cv3
            }

        }

        }
    private fun displayResults(bmi:Float){
        val resIndex=findViewById<TextView>(R.id.tv_bmi)
        val resDesc=findViewById<TextView>(R.id.tv_res)
        val resInfo=findViewById<TextView>(R.id.tv_moreinf)
        resIndex.text=bmi.toString()
        resInfo.text="(Normal range is 18.5-24.9)"
        var resText=""
        var resColor=0 //we ini both and based on condition their values assigned
        when {
            bmi < 18.50 -> {
                resText = "YOU ARE UNDERWEIGHT"
                resColor = R.color.un_wt
            }
            bmi in 18.50..24.90 -> {
                resText = "Your are NORMAL"
                resColor = R.color.nr_wt
            }
            bmi in 25.00..29.90 -> {
                resText = "Your are OVERWEIGHT!"
                resColor = R.color.ov_wt
            }
            bmi in 30.00..34.90 -> {
                resText = "Your are OBESE!!"
                resColor = R.color.ob_wt
            }
            bmi >= 35.00 -> {
                resText = "Your are EXTREMELY OBESE!!!"
                resColor = R.color.exob_wt
            }
        }
        resDesc.setTextColor(ContextCompat.getColor(this,resColor))
        resDesc.text=resText
    }
    private fun validateInputs(weight:String?,height:String?):Boolean{
        return when{
            weight.isNullOrEmpty()-> {
                Toast.makeText(this,"PLEASE ENTER WEIGHT",Toast.LENGTH_LONG).show()
                false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this,"PLEASE ENTER HEIGHT",Toast.LENGTH_LONG).show()
                false
            }
            else-> true
        }
    }
}


