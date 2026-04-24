package com.example.weatherapp2

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp2.ui.theme.WeatherApp2Theme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherApp2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginPage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPage(modifier: Modifier = Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val activity = LocalActivity.current as Activity
    Column(
        modifier = modifier.padding(24.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val fieldModifier = Modifier.fillMaxWidth(fraction = 0.9f)
        Text(
            text = "Bem-vindo/a!",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        OutlinedTextField(
            value = email,
            label = { Text(text = "Digite seu e-mail") },
            modifier = fieldModifier,
            onValueChange = { email = it }
        )
        OutlinedTextField(
            value = password,
            label = { Text(text = "Digite sua senha") },
            modifier = fieldModifier,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation()
        )
        Row(
            modifier = modifier.padding(top = 12.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button( onClick = {
                Toast.makeText(activity, "Login OK!", Toast.LENGTH_LONG).show() },
                enabled = email.isNotEmpty() && password.isNotEmpty(),
                modifier = Modifier.weight(1f)
                ) {
                Text("Login")
            }
            Spacer(modifier = Modifier.size(12.dp))
            Button(
                onClick = { email = ""; password = "" },
                modifier = Modifier.weight(1f)
            ) {
                Text("Limpar")
            }
        }
    }
}