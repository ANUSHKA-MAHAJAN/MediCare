package com.example.endpjct

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dbHelper = DatabaseHelper(this)

        val loginButton = findViewById<Button>(R.id.button_login)
        val signUpButton = findViewById<Button>(R.id.button_signup)

        // Handle login button click
        loginButton.setOnClickListener {
            val username = findViewById<EditText>(R.id.editTextUsername).text.toString()
            val password = findViewById<EditText>(R.id.editTextPassword).text.toString()

            val user = dbHelper.getUser(username, password)
            if (user != null) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                // Navigate to the next activity (e.g., Doctor Selection)
                startActivity(Intent(this, DoctorSelectionActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle sign-up button click
        signUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
