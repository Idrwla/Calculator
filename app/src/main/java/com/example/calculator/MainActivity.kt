package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Character.isDigit
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var expression: TextView
    private lateinit var result: TextView
    private lateinit var clean: Button
    private lateinit var digit1: Button
    private lateinit var digit2: Button
    private lateinit var digit3: Button
    private lateinit var digit4: Button
    private lateinit var digit5: Button
    private lateinit var digit6: Button
    private lateinit var digit7: Button
    private lateinit var digit8: Button
    private lateinit var digit9: Button
    private lateinit var digit0: Button
    private lateinit var dot: Button
    private lateinit var mod: Button
    private lateinit var div: Button
    private lateinit var mul: Button
    private lateinit var plus: Button
    private lateinit var minus: Button
    private lateinit var equal: Button
    private lateinit var back: Button

    private var isSolved = false
    private var ops = listOf<Char>('+','-','*','/','%')
    private var temporaryResult=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        expression = findViewById<TextView>(R.id.expression)
        expression.setText(R.string.start)
        result = findViewById<TextView>(R.id.result)
        result.setText(R.string.start)

        //listeners
        mod = findViewById(R.id.mod)
        mod.setOnClickListener{ appendExp("%")}
        div = findViewById(R.id.div)
        div.setOnClickListener{appendExp("/")}

        mul = findViewById(R.id.mul)
        mul.setOnClickListener{appendExp("*")}

        plus = findViewById(R.id.plus)
        plus.setOnClickListener{appendExp("+")}

        minus = findViewById(R.id.minus)
        minus.setOnClickListener{appendExp("-")}

        clean = findViewById<Button>(R.id.clean)
        clean.setOnClickListener { clean() }
        equal = findViewById(R.id.calculate)
        equal.setOnClickListener { solve() }

        back =findViewById(R.id.back)
        back.setOnClickListener { back() }

        dot = findViewById(R.id.dot)
        dot.setOnClickListener {appendExp(".")  }
        //digits
        digit1 = findViewById(R.id.digit1)
        digit1.setOnClickListener{appendExp("1")}
        digit2 = findViewById(R.id.digit2)
        digit2.setOnClickListener{appendExp("2")}
        digit3 = findViewById(R.id.digit3)
        digit3.setOnClickListener{appendExp("3")}
        digit4 = findViewById(R.id.digit4)
        digit4.setOnClickListener{appendExp("4")}
        digit5 = findViewById(R.id.digit5)
        digit5.setOnClickListener{appendExp("5")}
        digit6 = findViewById(R.id.digit6)
        digit6.setOnClickListener{appendExp("6")}
        digit7 = findViewById(R.id.digit7)
        digit7.setOnClickListener{appendExp("7")}
        digit8 = findViewById(R.id.digit8)
        digit8.setOnClickListener{appendExp("8")}
        digit9 = findViewById(R.id.digit9)
        digit9.setOnClickListener{appendExp("9")}
        digit0 = findViewById(R.id.digit0)
        digit0.setOnClickListener{appendExp("0")}

    }

    // save state

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("exp", expression.text.toString())
        outState.putString("res", result.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        expression.setText(savedInstanceState.getString("exp"))
        result.setText(savedInstanceState.getString("res"))
    }
    //methods

    private fun clean(){
        expression.setText(R.string.start)
        result.setText(R.string.start)
    }

    private fun back(){
        val str = expression.text.toString()
        if(str.isNotEmpty())
            expression.text = str.substring(0, str.length-1)
        result.setText(R.string.start)
    }
    private fun appendExp(exp: String){
        if (expression.text.toString() == "0" ||isSolved){
            expression.setText("")
        }
        isSolved=false

        if(expression.text.toString().isNotEmpty()){
            if(ops.contains<Char>(expression.text.toString().get(expression.text.toString().length-1))
                    && ops.contains<Char>(exp[0])
            ){
                expression.append("")
            }else{
                expression.append(exp)
            }
        }else if(!ops.contains(exp)){
            expression.append(exp)
        }
    }


    //solve
    private fun solve(){
        try {
            val ex = ExpressionBuilder(expression.text.toString()).build()
            var res = ex.evaluate()
            var longRes = res.toLong()
            if(res ==longRes.toDouble()){
                result.text = longRes.toString()
            }else{
                result.text = res.toString()
            }
            isSolved=true
        }catch (e:Exception){
            Log.e("Error while calc", e.message.toString())
        }
    }


}