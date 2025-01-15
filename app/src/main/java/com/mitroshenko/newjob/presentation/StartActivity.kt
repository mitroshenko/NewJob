package com.mitroshenko.newjob.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mitroshenko.newjob.R

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val email = findViewById<EditText>(R.id.ed_email)
        val resume = findViewById<Button>(R.id.btn_resume)

            email.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
                override fun afterTextChanged(s: Editable?) {
                    if (android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
                        resume.isEnabled = true
                        resume.setOnClickListener {
                            val intent = Intent(this@StartActivity, CodeActivity::class.java)
                            intent.putExtra("email", email.text.toString())
                            startActivity(intent)
                        }
                    }else {
                        resume.isEnabled = false
                        email.setError("Вы ввели неверный e-mail")
                    }
                }
            })
    }
}