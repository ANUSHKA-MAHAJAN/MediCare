package com.example.endpjct

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        dbHelper = DatabaseHelper(this)

        val signUpButton = findViewById<Button>(R.id.button_signup)

        signUpButton.setOnClickListener {
            val username = findViewById<EditText>(R.id.editTextUsername).text.toString()
            val password = findViewById<EditText>(R.id.editTextPassword).text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                val success = dbHelper.insertUser(username, password)
                if (success) {
                    Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                    finish() // Redirect back to login after successful signup
                } else {
                    Toast.makeText(this, "Sign up failed", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


