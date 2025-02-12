package com.ashna.mapd721_a2_ashnapaul

import android.os.Bundle
import android.webkit.PermissionRequest
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.records.HeartRateRecord
import com.ashna.mapd721_a2_ashnapaul.ui.theme.MAPD721A2AshnaPaulTheme
import java.security.Permission

class MainActivity : ComponentActivity() {
    private lateinit var healthConnectClient: HealthConnectClient

    private val healthPermissions = setOf(
        Permission.createWritePermission(HeartRateRecord::class),
        Permission.createReadPermission(HeartRateRecord::class)
    )

    private var heartRateHistory: List<String> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        healthConnectClient = HealthConnectClient.getOrCreate(this)
        requestHealthPermissions()

    }
}

private fun requestHealthPermissions() {
    val request = PermissionRequest(healthPermissions)
    val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions.containsValue(false)) {
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show()
            }
        }
    permissionLauncher.launch(request.permissions.toTypedArray())
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MAPD721A2AshnaPaulTheme {
        Greeting("Android")
    }
}