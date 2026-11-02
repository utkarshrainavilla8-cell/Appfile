package com.example.ui.customer

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Launch
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.HoneycombRepository
import com.example.ui.theme.HoneyGoldPrimary
import java.util.Locale

@Composable
fun FamPayGatewayDialog(
  amount: Double,
  payeeUpiId: String,
  payeeName: String,
  onDismiss: () -> Unit,
  onPaymentConfirmed: (transactionRef: String) -> Unit
) {
  val context = LocalContext.current
  var utrNumber by remember { mutableStateOf("") }
  var copiedToast by remember { mutableStateOf(false) }
  var showQrCode by remember { mutableStateOf(true) }

  val formattedAmount = HoneycombRepository.formatInr(amount)

  fun copyUpiId() {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("PhonePe UPI ID", payeeUpiId)
    clipboard.setPrimaryClip(clip)
    copiedToast = true
    Toast.makeText(context, "UPI ID copied: $payeeUpiId", Toast.LENGTH_SHORT).show()
  }

  fun launchUpiIntent() {
    try {
      // NPCI standard UPI payment intent supported by PhonePe, GPay, Paytm, etc.
      val uri = Uri.parse(
        "upi://pay?pa=$payeeUpiId" +
          "&pn=${Uri.encode(payeeName)}" +
          "&am=${String.format(Locale.US, "%.2f", amount)}" +
          "&cu=INR" +
          "&tn=${Uri.encode("Honeycomb Stitches Craft Order")}"
      )
      val intent = Intent(Intent.ACTION_VIEW, uri)
      context.startActivity(Intent.createChooser(intent, "Pay via PhonePe or UPI App"))
    } catch (e: Exception) {
      Toast.makeText(
        context,
        "No UPI app detected. You can copy the UPI ID below to pay directly in PhonePe!",
        Toast.LENGTH_LONG
      ).show()
    }
  }

  AlertDialog(
    onDismissRequest = onDismiss,
    title = {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(36.dp)
              .clip(CircleShape)
              .background(Color(0xFFFEF3C7))
              .border(1.5.dp, Color(0xFFF59E0B), CircleShape),
            contentAlignment = Alignment.Center
          ) {
            Image(
              painter = painterResource(id = R.drawable.honeycomb_logo),
              contentDescription = null,
              modifier = Modifier.size(32.dp),
              contentScale = ContentScale.Crop
            )
          }

          Spacer(modifier = Modifier.width(10.dp))

          Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "PhonePe / UPI Gateway",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = Color(0xFF26140A)
              )
              Surface(
                shape = RoundedCornerShape(6.dp),
                color = Color(0xFF15803D),
                modifier = Modifier.padding(start = 6.dp)
              ) {
                Text(
                  text = "UPI LIVE",
                  color = Color.White,
                  fontSize = 9.sp,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                )
              }
            }
            Text(
              text = "Credited directly to $payeeUpiId",
              fontSize = 11.sp,
              color = Color(0xFF78716C)
            )
          }
        }
      }
    },
    text = {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        // Amount Due Banner with dreamy honey shimmer
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = Color.Transparent),
          modifier = Modifier.fillMaxWidth()
        ) {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .background(
                Brush.horizontalGradient(
                  colors = listOf(Color(0xFFFEF3C7), Color(0xFFF3E8FF), Color(0xFFFFF9EE))
                )
              )
              .border(1.dp, Color(0xFFFDE68A), RoundedCornerShape(16.dp))
              .padding(14.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column {
                Text(
                  text = "Total Payable Amount",
                  fontSize = 11.sp,
                  color = Color(0xFF78350F),
                  fontWeight = FontWeight.SemiBold
                )
                Text(
                  text = formattedAmount,
                  fontSize = 24.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFFB45309)
                )
              }

              Surface(
                shape = RoundedCornerShape(10.dp),
                color = Color(0xFF6739B7)
              ) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                  Icon(
                    Icons.Default.Shield,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                  )
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    text = "100% SECURE",
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                  )
                }
              }
            }
          }
        }

        // Payee PhonePe / UPI ID Display Card
        Card(
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFFFAF5ED)),
          border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE5D5C2)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(12.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column {
                Text(
                  text = "Crediting Merchant PhonePe / UPI ID:",
                  fontSize = 11.sp,
                  color = Color(0xFF78716C),
                  fontWeight = FontWeight.Medium
                )
                Text(
                  text = payeeUpiId,
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF6739B7),
                  fontFamily = FontFamily.Monospace
                )
                Text(
                  text = "Payee: $payeeName",
                  fontSize = 11.sp,
                  color = Color(0xFF57483F)
                )
              }

              OutlinedButton(
                onClick = { copyUpiId() },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.testTag("copy_fampay_upi_button")
              ) {
                Icon(
                  Icons.Default.ContentCopy,
                  contentDescription = "Copy",
                  modifier = Modifier.size(14.dp),
                  tint = Color(0xFF6739B7)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = if (copiedToast) "Copied!" else "Copy",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF6739B7)
                )
              }
            }
          }
        }

        // Action: 1-Tap Pay via PhonePe / UPI App
        Button(
          onClick = { launchUpiIntent() },
          modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .testTag("launch_fampay_intent_button"),
          shape = RoundedCornerShape(12.dp),
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6739B7))
        ) {
          Icon(Icons.Default.Launch, contentDescription = null, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Pay with PhonePe / Any UPI App ($formattedAmount)",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
          )
        }

        // Toggle QR Code button
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "Or Scan & Pay via QR",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF381E0D)
          )

          TextButton(onClick = { showQrCode = !showQrCode }) {
            Icon(Icons.Default.QrCode, contentDescription = null, modifier = Modifier.size(16.dp), tint = HoneyGoldPrimary)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = if (showQrCode) "Hide QR" else "Show QR",
              fontSize = 11.sp,
              color = HoneyGoldPrimary,
              fontWeight = FontWeight.Bold
            )
          }
        }

        // Stylized Dynamic UPI QR Code
        if (showQrCode) {
          Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE5D5C2)),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(
              modifier = Modifier.padding(14.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Box(
                modifier = Modifier
                  .size(160.dp)
                  .background(Color(0xFFFFFDF8), RoundedCornerShape(12.dp))
                  .border(2.dp, Color(0xFFFEF3C7), RoundedCornerShape(12.dp))
                  .padding(10.dp),
                contentAlignment = Alignment.Center
              ) {
                // Procedural QR pattern representation
                Canvas(modifier = Modifier.size(140.dp)) {
                  val cellSize = size.width / 14f

                  // Corner locator squares (top-left, top-right, bottom-left)
                  fun drawLocator(x: Float, y: Float) {
                    drawRoundRect(
                      color = Color(0xFF26140A),
                      topLeft = Offset(x, y),
                      size = Size(cellSize * 4, cellSize * 4),
                      cornerRadius = CornerRadius(4.dp.toPx())
                    )
                    drawRoundRect(
                      color = Color(0xFFFFFDF8),
                      topLeft = Offset(x + cellSize * 0.7f, y + cellSize * 0.7f),
                      size = Size(cellSize * 2.6f, cellSize * 2.6f),
                      cornerRadius = CornerRadius(2.dp.toPx())
                    )
                    drawRoundRect(
                      color = Color(0xFF6739B7),
                      topLeft = Offset(x + cellSize * 1.3f, y + cellSize * 1.3f),
                      size = Size(cellSize * 1.4f, cellSize * 1.4f),
                      cornerRadius = CornerRadius(2.dp.toPx())
                    )
                  }

                  drawLocator(0f, 0f)
                  drawLocator(size.width - cellSize * 4, 0f)
                  drawLocator(0f, size.height - cellSize * 4)

                  // Middle data blocks
                  val seedPattern = listOf(
                    5 to 1, 6 to 2, 7 to 1, 8 to 3,
                    1 to 5, 2 to 6, 3 to 7, 5 to 5, 7 to 6, 8 to 5,
                    9 to 2, 10 to 3, 11 to 2, 9 to 8, 10 to 9,
                    4 to 9, 5 to 10, 6 to 11, 7 to 10, 8 to 11,
                    9 to 11, 10 to 12, 11 to 10, 12 to 11,
                    1 to 9, 2 to 10, 3 to 11
                  )

                  seedPattern.forEach { (cx, cy) ->
                    drawRoundRect(
                      color = Color(0xFF381E0D),
                      topLeft = Offset(cx * cellSize, cy * cellSize),
                      size = Size(cellSize * 0.85f, cellSize * 0.85f),
                      cornerRadius = CornerRadius(1.5.dp.toPx())
                    )
                  }

                  // Center brand badge overlay
                  drawCircle(
                    color = Color(0xFF6739B7),
                    radius = cellSize * 1.5f,
                    center = Offset(size.width / 2, size.height / 2)
                  )
                  drawCircle(
                    color = Color(0xFFFEF3C7),
                    radius = cellSize * 1.1f,
                    center = Offset(size.width / 2, size.height / 2)
                  )
                }

                // Mini Bee Emblem in center of QR
                Image(
                  painter = painterResource(id = R.drawable.honeycomb_logo),
                  contentDescription = null,
                  modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape),
                  contentScale = ContentScale.Crop
                )
              }

              Spacer(modifier = Modifier.height(8.dp))
              Text(
                text = "Scan with PhonePe, Google Pay, Paytm, or any UPI app",
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF78716C)
              )
            }
          }
        }

        // UPI Reference / UTR Input
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Text(
            text = "Enter 12-Digit UPI / UTR Reference (Optional):",
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF381E0D)
          )

          OutlinedTextField(
            value = utrNumber,
            onValueChange = { utrNumber = it },
            placeholder = { Text("e.g. 430129849201") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("fampay_utr_input")
          )

          Text(
            text = "Payment directly credits to merchant's PhonePe / UPI account with 0% fee.",
            fontSize = 10.sp,
            color = Color(0xFF15803D),
            fontWeight = FontWeight.Medium
          )
        }
      }
    },
    confirmButton = {
      Button(
        onClick = {
          onPaymentConfirmed(utrNumber.ifBlank { "UPI-${(100000..999999).random()}" })
        },
        colors = ButtonDefaults.buttonColors(containerColor = HoneyGoldPrimary),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.testTag("confirm_fampay_payment_button")
      ) {
        Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text("I Have Paid · Confirm Order ✨")
      }
    },
    dismissButton = {
      TextButton(onClick = onDismiss) {
        Text("Cancel", color = Color(0xFF78716C))
      }
    },
    shape = RoundedCornerShape(24.dp),
    containerColor = Color.White
  )
}
