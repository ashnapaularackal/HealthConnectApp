package com.ashna.mapd721_a2_ashnapaul

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.time.Instant

@Composable
fun HealthScreen(
    onSave: (Int, String) -> Unit,
    onLoad: () -> Unit,
    history: List<String>
) {
    var heartRate by remember { mutableStateOf("") }
    var dateTime by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Health Connect - Heart Rate", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        // Heart Rate Input
        OutlinedTextField(
            value = heartRate,
            onValueChange = { heartRate = it },
            label = { Text("Heart Rate (BPM)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Date/Time Input
        OutlinedTextField(
            value = dateTime,
            onValueChange = { dateTime = it },
            label = { Text("Date/Time (ISO-8601 format)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (heartRate.isNotEmpty() && dateTime.isNotEmpty()) {
                        try {
                            val bpm = heartRate.toInt()
                            Instant.parse(dateTime) // Validates format
                            onSave(bpm, dateTime)
                            Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Toast.makeText(context, "Invalid Input Format!", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Please enter all fields!", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text("Save")
            }

            Button(onClick = { onLoad() }) {
                Text("Load")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Heart Rate History
        Text(text = "Heart Rate History", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            history.forEach { record ->
                Text(text = record, modifier = Modifier.padding(4.dp))
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // About Section
        Text(text = "About", style = MaterialTheme.typography.headlineSmall)
        Text(text = "Student Name: Ashna Paul")
        Text(text = "Student ID: 301479554")
    }
}
