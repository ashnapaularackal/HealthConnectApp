package com.ashna.mapd721_a2_ashnapaul

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * HealthScreen Composable function
 * Author: Ashna Paul
 * ID :301479554
 * Description: This UI allows users to input and save heart rate readings along with timestamps.
 * It also displays a scrollable history of previous heart rate readings.
 */
@Composable
fun HealthScreen(
    onSaveClick: (String, String) -> Unit,
    onLoadClick: () -> Unit,
    heartRateHistory: List<Pair<String, String>>
) {
    var heartRate by remember { mutableStateOf("") }
    var dateTime by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    // Gradient background
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFF4A90E2), Color(0xFF50E3C2))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundBrush) // Ensures full background coverage
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
        ) {
            // Title
            Text(
                "MAPD 721 - A2",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Heart Rate Input Field
            OutlinedTextField(
                value = heartRate,
                onValueChange = {
                    heartRate = it
                    errorMessage = if (it.toIntOrNull() !in 1..300) "Heart rate must be between 1 and 300 bpm" else ""
                },
                label = { Text("Heart Rate (1-300 bpm)") },
                modifier = Modifier.fillMaxWidth(),
                isError = errorMessage.isNotEmpty()
            )
            if (errorMessage.isNotEmpty()) {
                Text(errorMessage, color = Color.Red, style = TextStyle(fontSize = 14.sp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Date/Time Input Field
            OutlinedTextField(
                value = dateTime,
                onValueChange = { dateTime = it },
                label = { Text("Date/Time (yyyy-MM-dd HH:mm)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onLoadClick,
                    modifier = Modifier.padding(8.dp).height(50.dp)
                ) {
                    Text("Load", fontSize = 18.sp)
                }
                Button(
                    onClick = {
                        if (errorMessage.isEmpty()) {
                            onSaveClick(heartRate, dateTime)
                        }
                    },
                    modifier = Modifier.padding(8.dp).height(50.dp)
                ) {
                    Text("Save", fontSize = 18.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Heart Rate History
            Text("Heart Rate History", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))

            Box(modifier = Modifier.weight(1f).padding(vertical = 8.dp)) {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(heartRateHistory) { reading ->
                        Text(
                            "${reading.second} - ${reading.first}",
                            style = TextStyle(fontSize = 16.sp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // About Section (Scrollable)
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text("About", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
                Text("Student Name: Ashna Paul", fontSize = 16.sp)
                Text("Student ID: 301479554", fontSize = 16.sp)
            }
        }
    }
}
