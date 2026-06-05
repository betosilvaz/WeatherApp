package com.example.weatherapp2

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp2.ui.theme.WeatherApp2Theme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

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

    Surface(
    ) {
        Column(
            modifier = modifier.padding(24.dp).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "WeatherApp",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = email,
                label = { Text(text = "Digite seu e-mail") },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { email = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                label = { Text(text = "Digite sua senha") },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        Firebase.auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(activity) { task ->
                                if (task.isSuccessful) {
                                    activity.startActivity(
                                        Intent(activity, MainActivity::class.java).setFlags(
                                            FLAG_ACTIVITY_SINGLE_TOP
                                        )
                                    )
                                    Toast.makeText(activity, "Login OK!", Toast.LENGTH_LONG).show()
                                } else {
                                    Toast.makeText(activity, "Login FALHOU!", Toast.LENGTH_LONG).show()
                                }
                            }
                    },
                    enabled = email.isNotEmpty() && password.isNotEmpty(),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Entrar")
                }
                Spacer(modifier = Modifier.size(16.dp))
                Button(
                    onClick = { email = ""; password = "" },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Limpar")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f))
                Text(text = "ou",
                    modifier = Modifier.padding(horizontal = 8.dp))
                HorizontalDivider(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    activity.startActivity(Intent(activity, RegisterActivity::class.java).setFlags(FLAG_ACTIVITY_SINGLE_TOP))
                }
            ) {
                Text("Criar Conta")
            }
        }
    }
}