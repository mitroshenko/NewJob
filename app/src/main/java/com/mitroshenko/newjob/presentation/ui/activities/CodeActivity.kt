package com.mitroshenko.newjob.presentation.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.mitroshenko.newjob.R

class CodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code)

        val userMail = findViewById<TextView>(R.id.tv_username)
        val confirm = findViewById<Button>(R.id.btn_confirm)
        val int = intent.getStringExtra("email")

        userMail.setText("Отправили код на $int")

        val code1 = findViewById<EditText>(R.id.ed_code1)
        val code2 = findViewById<EditText>(R.id.ed_code2)
        val code3 = findViewById<EditText>(R.id.ed_code3)
        val code4 = findViewById<EditText>(R.id.ed_code4)

        code1.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable) {
                if (code1.text.length > 0) {
                    code2.requestFocus()
                }
            }
        })

        code2.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable) {
                if (code2.text.length > 0) {
                    code3.requestFocus()
                }
            }
        })

        code3.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable) {
                if (code3.text.length > 0) {
                    code4.requestFocus()
                }
            }
        })

        code4.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable) {
                if (code4.text.length > 0) {
                    confirm.isEnabled = true
                }
            }
        })

        confirm.setOnClickListener {
            val intent = Intent(this@CodeActivity, MainActivity::class.java)
            startActivity(intent)
        }
        fun entCOde(){

        }
    }
}