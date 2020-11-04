package br.com.iesb.rbmurussi.android.calculator

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.function.Function
import net.objecthunter.exp4j.operator.Operator


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtScreen.movementMethod = ScrollingMovementMethod()

        val click = View.OnClickListener {
            val text = (it as Button).text
            txtScreen.append(text)
        }

        /* ^ */
        btnExp.setOnClickListener(click)
        /* ! */
        btnFact.setOnClickListener(click)
        /* % */
        btnMod.setOnClickListener(click)

        /* ( */
        btnLeftParenthesis.setOnClickListener(click)
        /* ) */
        btnRightParentheses.setOnClickListener(click)

        btnAdd.setOnClickListener(click)
        btnMultiply.setOnClickListener(click)
        btnSubtract.setOnClickListener(click)
        btnDivide.setOnClickListener(click)

        btnOne.setOnClickListener(click)
        btnTwo.setOnClickListener(click)
        btnThree.setOnClickListener(click)
        btnFour.setOnClickListener(click)
        btnFive.setOnClickListener(click)
        btnSix.setOnClickListener(click)
        btnSeven.setOnClickListener(click)
        btnEight.setOnClickListener(click)
        btnNine.setOnClickListener(click)
        btnZero.setOnClickListener(click)
        btnDot.setOnClickListener(click)

        btnSin.setOnClickListener(click)
        btnSqrt.setOnClickListener(click)
        btnLog.setOnClickListener(click)

        btnCos.setOnClickListener(click)
        btnTag.setOnClickListener(click)
        btnCossec.setOnClickListener(click)
        btnSec.setOnClickListener(click)
        btnCot.setOnClickListener(click)

        btnEqual.setOnClickListener {
            var result = ExpressionBuilder(lastLineText())
                    .operator(factorial)
                    .function(cosecant)
                    .function(secant)
                    .build()
                    .evaluate()
            txtScreen.text = "${txtScreen.text}=$result\n"
        }

        btnClear.setOnClickListener {
            txtScreen.text = "\n"
        }
    }

    private fun lastLineText(): String {
        var text = txtScreen.text.toString()
        val index = text.lastIndexOf('\n')
        Log.i("INDEX", "$index")
        if(index > 0) text = text.substring(index + 1)
        Log.i("CALC", text)
        return text
    }

    private val factorial: Operator = object : Operator("!", 1, true, PRECEDENCE_POWER + 1) {
        override fun apply(vararg args: Double): Double {
            val arg = args[0].toInt()
            require(arg.toDouble() == args[0]) { "Operand for factorial has to be an integer" }
            require(arg >= 0) { "The operand of the factorial can not be less than zero" }
            var result = 1.0
            for (i in 1..arg) {
                result *= i.toDouble()
            }
            return result
        }
    }

    private val cosecant: Function = object : Function("csc") {
        override fun apply(vararg args: Double): Double {
            val sin = kotlin.math.sin(args[0])
            if (sin == 0.0) {
                throw ArithmeticException("Division by zero in cosecant!")
            }
            return 1.0 / sin
        }
    }

    private val secant: Function = object : Function("sec") {
        override fun apply(vararg args: Double): Double {
            val cos = kotlin.math.cos(args[0])
            if (cos == 0.0) {
                throw java.lang.ArithmeticException("Division by zero in secant!")
            }
            return 1.0 / cos
        }
    }
}

