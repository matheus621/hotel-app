package com.example.hoteisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val edtEmail = edtEmail
        val edtPassword = edtPassword

        val buttonLogin = buttonLogin

        buttonLogin.setOnClickListener(View.OnClickListener {
            if (edtEmail.equals("") || edtPassword.equals("")) {
                Toast.makeText(this, "Favor informar o email e/ou senha!", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent
            }
        })

    }
}