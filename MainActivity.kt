package com.example.androidhw2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Exception
import java.lang.NullPointerException
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    lateinit var edit1 : EditText
    lateinit var edit2 : EditText
    lateinit var btnAdd: Button
    lateinit var btnSub: Button
    lateinit var btnMul: Button
    lateinit var btnDiv: Button
    lateinit var btnMod: Button
    lateinit var btnChg: Button
    lateinit var textResult: TextView
    lateinit var exceptionMsg: TextView
    var num1 : String? = null
    var num2 : String? = null
    var cnt: Int = 0
    var result: Int? = null
    var arithCnt: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "$arithCnt 회 계산기"
        edit1 = findViewById<EditText>(R.id.Edit1)
        edit2 = findViewById<EditText>(R.id.Edit2)
        btnAdd = findViewById<Button>(R.id.BtnAdd)
        btnSub = findViewById<Button>(R.id.BtnSub)
        btnMul = findViewById<Button>(R.id.BtnMul)
        btnDiv = findViewById<Button>(R.id.BtnDiv)
        btnMod = findViewById<Button>(R.id.BtnMod)
        btnChg = findViewById<Button>(R.id.BtnChg)
        textResult = findViewById<TextView>(R.id.TextResult)
        exceptionMsg = findViewById<TextView>(R.id.Exception)
        fun numInit() {
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()
        }
        fun doArithmetic(a: Char) {
            cnt++
            when (a) {
                '+' -> result = Integer.parseInt(num1) + Integer.parseInt(num2)
                '-' -> result = Integer.parseInt(num1) - Integer.parseInt(num2)
                '*' -> result = Integer.parseInt(num1) * Integer.parseInt(num2)
                '/' -> result = Integer.parseInt(num1) / Integer.parseInt(num2)
                '%' -> result = Integer.parseInt(num1) % Integer.parseInt(num2)
                'c' -> {
                    if(cnt%2==1) {
                        var tmp: String = edit1.text.toString()
                        edit1.setText(edit2.text.toString())
                        edit2.setText(tmp)
                    }
                }
            }
            textResult.text = "계산 결과: " + result.toString()
        }
        fun calc(a: Char) {
            numInit()
            if (num1 == null || num2 == null)
                throw NullPointerException("숫자를 모두 입력해주세요.")
            else if(num1 == "" || num2 == "")
                throw NumberFormatException("숫자를 모두 입력해주세요.")
            doArithmetic(a)
            arithCnt++
        }
        try {
            btnAdd.setOnTouchListener { view, motionEvent ->
                calc('+')
                false
            }
            btnSub.setOnTouchListener { view, motionEvent ->
                calc('-')
                false
            }
            btnMul.setOnTouchListener { view, motionEvent ->
                calc('*')
                false
            }
            btnDiv.setOnTouchListener { view, motionEvent ->
                calc('/')
                false
            }
            btnMod.setOnTouchListener { view, motionEvent ->
                calc('%')
                false
            }
            btnChg.setOnTouchListener { view, motionEvent ->
                calc('c')
                false
            }
        } catch(e: Exception){
            exceptionMsg.text = e.message
        }
    }
}