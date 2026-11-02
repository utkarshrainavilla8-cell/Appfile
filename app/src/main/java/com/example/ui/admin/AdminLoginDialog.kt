package com.example.ui.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.HoneycombRepository
import com.example.model.AdminUser
import com.example.ui.theme.HoneyGoldPrimary

@Composable
fun AdminLoginDialog(
  onDismiss: () -> Unit,
  onLoginSuccess: (AdminUser) -> Unit
) {
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var passwordVisible by remember { mutableStateOf(false) }
  var errorText by remember { mutableStateOf<String?>(null) }

  fun performLogin() {
    val admin = HoneycombRepository.authenticateAdmin(email.trim(), password)
    if (admin != null) {
      onLoginSuccess(admin)
    } else {
      errorText = "Invalid email or password. Please check your admin credentials."
    }
  }

  AlertDialog(
    onDismissRequest = onDismiss,
    icon = {
      Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFFEF3C7),
        modifier = Modifier.size(44.dp)
      ) {
        androidx.compose.foundation.layout.Box(contentAlignment = Alignment.Center) {
          Icon(
            Icons.Default.AdminPanelSettings,
            contentDescription = null,
            tint = HoneyGoldPrimary,
            modifier = Modifier.size(26.dp)
          )
        }
      }
    },
    title = {
      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
          text = "Honeycomb Workshop Portal",
          fontWeight = FontWeight.Bold,
          fontSize = 18.sp,
          color = Color(0xFF26140A)
        )
        Text(
          text = "Authorized Admin Login",
          fontSize = 12.sp,
          color = Color(0xFF78716C),
          modifier = Modifier.padding(top = 2.dp)
        )
      }
    },
    text = {
      Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Surface(
          shape = RoundedCornerShape(10.dp),
          color = Color(0xFFFAF5ED),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(
              Icons.Default.Security,
              contentDescription = null,
              tint = HoneyGoldPrimary,
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.size(6.dp))
            Text(
              text = "Multi-Admin enabled. Login with primary Super Admin or any admin ID created in the portal.",
              fontSize = 11.sp,
              color = Color(0xFF57483F)
            )
          }
        }

        OutlinedTextField(
          value = email,
          onValueChange = {
            email = it
            errorText = null
          },
          label = { Text("Admin Email") },
          placeholder = { Text("admin@example.com") },
          singleLine = true,
          modifier = Modifier.fillMaxWidth().testTag("admin_email_input"),
          shape = RoundedCornerShape(12.dp)
        )

        OutlinedTextField(
          value = password,
          onValueChange = {
            password = it
            errorText = null
          },
          label = { Text("Password") },
          placeholder = { Text("Enter password") },
          visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
          trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
              Icon(
                imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                contentDescription = if (passwordVisible) "Hide password" else "Show password",
                tint = Color(0xFF78716C),
                modifier = Modifier.size(20.dp)
              )
            }
          },
          singleLine = true,
          modifier = Modifier.fillMaxWidth().testTag("admin_password_input"),
          shape = RoundedCornerShape(12.dp)
        )

        if (errorText != null) {
          Text(
            text = errorText!!,
            color = Color(0xFFDC2626),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
          )
        }

        Surface(
          shape = RoundedCornerShape(8.dp),
          color = Color(0xFFF8FAFC),
          border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(
              Icons.Default.Lock,
              contentDescription = null,
              tint = Color(0xFF64748B),
              modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.size(6.dp))
            Text(
              text = "Restricted Area • Admin credentials are secure & encrypted.",
              fontSize = 11.sp,
              color = Color(0xFF475569)
            )
          }
        }
      }
    },
    confirmButton = {
      Button(
        onClick = { performLogin() },
        colors = ButtonDefaults.buttonColors(containerColor = HoneyGoldPrimary),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.testTag("submit_admin_login_button")
      ) {
        Text("Sign In ✨", fontWeight = FontWeight.Bold)
      }
    },
    dismissButton = {
      TextButton(onClick = onDismiss) {
        Text("Cancel", color = Color(0xFF78716C))
      }
    },
    shape = RoundedCornerShape(22.dp),
    containerColor = Color.White
  )
}
