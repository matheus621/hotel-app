package com.example.hoteisapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.hoteisapp.R
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

            }
        })

    }
}