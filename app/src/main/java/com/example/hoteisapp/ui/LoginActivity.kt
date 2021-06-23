package com.example.hoteisapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.hoteisapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val buttonLogin = buttonLogin

        buttonLogin.setOnClickListener(View.OnClickListener {
            when {
                edtEmail.text.toString().isEmpty() || edtPassword.text.toString().isEmpty() -> {
                    Toast.makeText(this, "Favor informar o email e/ou senha!", Toast.LENGTH_LONG).show()
                }
                edtEmail.text.toString() == EMAIL && edtPassword.text.toString() == PASSWORD -> {
                    val intent = Intent(this, HotelActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                edtEmail.text.toString() != EMAIL || edtPassword.text.toString() != PASSWORD -> {
                    Toast.makeText(this, "Favor informar o email e/ou senha CORRETOS!", Toast.LENGTH_LONG).show()
                }
            }
        })

    }

    companion object {
        private const val EMAIL = "admin@admin.com"
        private const val PASSWORD = "admin123"
    }
}