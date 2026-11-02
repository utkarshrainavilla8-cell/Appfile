package com.example.ui.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Discount
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.HoneycombRepository
import com.example.model.CartItem
import com.example.model.Order
import com.example.ui.components.ProductArtThumbnail
import com.example.ui.theme.HoneyGoldPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
  cartItems: List<CartItem>,
  storeUpiId: String,
  storePayeeName: String,
  onBackClicked: () -> Unit,
  onUpdateQuantity: (String, Int) -> Unit,
  onRemoveItem: (String) -> Unit,
  onPlaceOrder: (
    name: String,
    email: String,
    address: String,
    paymentMethod: String,
    discount: Double,
    payeeUpi: String?,
    utr: String?,
    isPaidOnline: Boolean
  ) -> Order?,
  onViewOrders: () -> Unit,
  modifier: Modifier = Modifier
) {
  BackHandler { onBackClicked() }

  var promoCode by remember { mutableStateOf("") }
  var appliedPromo by remember { mutableStateOf<String?>(null) }
  var discountPercent by remember { mutableDoubleStateOf(0.0) }
  var promoError by remember { mutableStateOf<String?>(null) }

  var showCheckoutSheet by remember { mutableStateOf(false) }
  var showFamPayGateway by remember { mutableStateOf(false) }

  // Customer form inputs
  var pendingCustomerName by remember { mutableStateOf("Ananya Sharma") }
  var pendingCustomerEmail by remember { mutableStateOf("ananya.s@example.in") }
  var pendingCustomerAddress by remember { mutableStateOf("Flat 304, Green Glen Layout, Bellandur, Bengaluru 560103") }
  var selectedPaymentMode by remember { mutableStateOf("FAMPAY_UPI") } // "FAMPAY_UPI" or "COD"

  var placedOrderResult by remember { mutableStateOf<Order?>(null) }

  val subtotal = cartItems.sumOf { it.product.price * it.quantity }
  val discountAmount = subtotal * discountPercent
  val shipping = if (subtotal > 999.0 || cartItems.isEmpty()) 0.0 else 99.0
  val total = (subtotal - discountAmount + shipping).coerceAtLeast(0.0)

  // FamPay Online Payment Gateway Dialog
  if (showFamPayGateway) {
    FamPayGatewayDialog(
      amount = total,
      payeeUpiId = storeUpiId,
      payeeName = storePayeeName,
      onDismiss = { showFamPayGateway = false },
      onPaymentConfirmed = { utrNumber ->
        showFamPayGateway = false
        val created = onPlaceOrder(
          pendingCustomerName,
          pendingCustomerEmail,
          pendingCustomerAddress,
          "Online (PhonePe / UPI)",
          discountAmount,
          storeUpiId,
          utrNumber,
          true
        )
        placedOrderResult = created
      }
    )
  }

  // Order Success Dialog
  if (placedOrderResult != null) {
    val order = placedOrderResult!!
    AlertDialog(
      onDismissRequest = { placedOrderResult = null },
      icon = {
        Icon(
          Icons.Default.CheckCircle,
          contentDescription = null,
          tint = Color(0xFF15803D),
          modifier = Modifier.size(48.dp)
        )
      },
      title = {
        Text(
          text = if (order.isPaidOnline) "Payment Received & Order Placed! ✨🍯" else "Order Confirmed (COD)! 📦",
          fontWeight = FontWeight.Bold,
          fontSize = 18.sp,
          color = Color(0xFF26140A)
        )
      },
      text = {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
          Text(
            text = if (order.isPaidOnline) {
              "Thank you for supporting handmade craft! Payment of ${HoneycombRepository.formatInr(order.total)} has been credited to FamPay UPI account (${order.payeeUpiId ?: storeUpiId})."
            } else {
              "Thank you for supporting handmade craft! Order #${order.id} is confirmed. Please keep ${HoneycombRepository.formatInr(order.total)} in cash ready at delivery."
            },
            fontSize = 13.sp,
            color = Color(0xFF57483F)
          )
          Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFFFEF3C7),
            modifier = Modifier.fillMaxWidth().padding(top = 6.dp)
          ) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
              Text(text = "Order ID: #${order.id}", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color(0xFFB45309))
              Text(text = "Amount: ${HoneycombRepository.formatInr(order.total)}", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF78350F))
              Text(text = "Payment: ${order.paymentMethod}", fontSize = 12.sp, color = Color(0xFF78350F))
              if (order.isPaidOnline && !order.payeeUpiId.isNullOrBlank()) {
                Text(
                  text = "Credited to FamPay: ${order.payeeUpiId}",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = Color(0xFF15803D),
                  fontFamily = FontFamily.Monospace
                )
              }
              Text(text = "Status: Crafting with Love 🧶", fontSize = 12.sp, color = Color(0xFF15803D), fontWeight = FontWeight.SemiBold)
            }
          }
        }
      },
      confirmButton = {
        Button(
          onClick = {
            placedOrderResult = null
            onViewOrders()
          },
          colors = ButtonDefaults.buttonColors(containerColor = HoneyGoldPrimary),
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier.testTag("track_my_order_button")
        ) {
          Text("Track My Order ✨")
        }
      },
      dismissButton = {
        TextButton(onClick = {
          placedOrderResult = null
          onBackClicked()
        }) {
          Text("Explore More Crafts", color = Color(0xFF78716C))
        }
      },
      shape = RoundedCornerShape(22.dp),
      containerColor = Color.White
    )
  }

  // Delivery & Payment Selection Sheet
  if (showCheckoutSheet) {
    ModalBottomSheet(
      onDismissRequest = { showCheckoutSheet = false },
      sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
      containerColor = Color.White
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp)
          .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "Delivery & Payment ✨",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF26140A)
          )
          Text(
            text = "Total: ${HoneycombRepository.formatInr(total)}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = HoneyGoldPrimary
          )
        }

        OutlinedTextField(
          value = pendingCustomerName,
          onValueChange = { pendingCustomerName = it },
          label = { Text("Recipient Name") },
          singleLine = true,
          modifier = Modifier.fillMaxWidth().testTag("checkout_name_input"),
          shape = RoundedCornerShape(12.dp)
        )

        OutlinedTextField(
          value = pendingCustomerEmail,
          onValueChange = { pendingCustomerEmail = it },
          label = { Text("Email for updates & UPI receipt") },
          singleLine = true,
          modifier = Modifier.fillMaxWidth().testTag("checkout_email_input"),
          shape = RoundedCornerShape(12.dp)
        )

        OutlinedTextField(
          value = pendingCustomerAddress,
          onValueChange = { pendingCustomerAddress = it },
          label = { Text("Shipping Address in India 🇮🇳") },
          maxLines = 2,
          modifier = Modifier.fillMaxWidth().testTag("checkout_address_input"),
          shape = RoundedCornerShape(12.dp)
        )

        Text(
          text = "Select Payment Method",
          fontWeight = FontWeight.Bold,
          fontSize = 13.sp,
          color = Color(0xFF381E0D)
        )

        // Option 1: Online Payment (FamPay UPI)
        Card(
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(
            containerColor = if (selectedPaymentMode == "FAMPAY_UPI") Color(0xFFFEF3C7) else Color(0xFFFAF5ED)
          ),
          border = androidx.compose.foundation.BorderStroke(
            1.5.dp,
            if (selectedPaymentMode == "FAMPAY_UPI") HoneyGoldPrimary else Color(0xFFE5D5C2)
          ),
          modifier = Modifier
            .fillMaxWidth()
            .clickable { selectedPaymentMode = "FAMPAY_UPI" }
            .testTag("select_fampay_upi_option")
        ) {
          Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            RadioButton(
              selected = selectedPaymentMode == "FAMPAY_UPI",
              onClick = { selectedPaymentMode = "FAMPAY_UPI" },
              colors = RadioButtonDefaults.colors(selectedColor = HoneyGoldPrimary)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Column(modifier = Modifier.weight(1f)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                  text = "Online Payment (PhonePe / UPI)",
                  fontWeight = FontWeight.Bold,
                  fontSize = 13.sp,
                  color = Color(0xFF26140A)
                )
                Surface(
                  shape = RoundedCornerShape(6.dp),
                  color = Color(0xFF6739B7),
                  modifier = Modifier.padding(start = 6.dp)
                ) {
                  Text(
                    text = "RECOMMENDED",
                    color = Color.White,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                  )
                }
              }

              Text(
                text = "Pay via PhonePe, GPay, Paytm, or any UPI app",
                fontSize = 11.sp,
                color = Color(0xFF78716C)
              )

              Text(
                text = "Credited directly to $storeUpiId",
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF6739B7),
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.padding(top = 2.dp)
              )
            }
          }
        }

        // Option 2: Cash on Delivery (COD)
        Card(
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(
            containerColor = if (selectedPaymentMode == "COD") Color(0xFFFEF3C7) else Color(0xFFFAF5ED)
          ),
          border = androidx.compose.foundation.BorderStroke(
            1.5.dp,
            if (selectedPaymentMode == "COD") HoneyGoldPrimary else Color(0xFFE5D5C2)
          ),
          modifier = Modifier
            .fillMaxWidth()
            .clickable { selectedPaymentMode = "COD" }
            .testTag("select_cod_option")
        ) {
          Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            RadioButton(
              selected = selectedPaymentMode == "COD",
              onClick = { selectedPaymentMode = "COD" },
              colors = RadioButtonDefaults.colors(selectedColor = HoneyGoldPrimary)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Column(modifier = Modifier.weight(1f)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                  text = "Cash on Delivery (COD)",
                  fontWeight = FontWeight.Bold,
                  fontSize = 13.sp,
                  color = Color(0xFF26140A)
                )
                Surface(
                  shape = RoundedCornerShape(6.dp),
                  color = Color(0xFF15803D),
                  modifier = Modifier.padding(start = 6.dp)
                ) {
                  Text(
                    text = "AVAILABLE",
                    color = Color.White,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                  )
                }
              }

              Text(
                text = "Pay in cash when delivery partner arrives at your address",
                fontSize = 11.sp,
                color = Color(0xFF78716C)
              )

              Text(
                text = "Exact cash payable at delivery: ${HoneycombRepository.formatInr(total)}",
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF57483F),
                modifier = Modifier.padding(top = 2.dp)
              )
            }
          }
        }

        // Submit Action Button
        Button(
          onClick = {
            showCheckoutSheet = false
            if (selectedPaymentMode == "FAMPAY_UPI") {
              // Open FamPay payment gateway to credit user's UPI ID
              showFamPayGateway = true
            } else {
              // Direct Cash on Delivery placement
              val created = onPlaceOrder(
                pendingCustomerName,
                pendingCustomerEmail,
                pendingCustomerAddress,
                "Cash on Delivery (COD)",
                discountAmount,
                null,
                null,
                false
              )
              placedOrderResult = created
            }
          },
          modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .testTag("confirm_place_order_button"),
          shape = RoundedCornerShape(14.dp),
          colors = ButtonDefaults.buttonColors(containerColor = HoneyGoldPrimary)
        ) {
          Icon(
            if (selectedPaymentMode == "FAMPAY_UPI") Icons.Default.AccountBalanceWallet else Icons.Default.LocalShipping,
            contentDescription = null,
            modifier = Modifier.size(18.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = if (selectedPaymentMode == "FAMPAY_UPI") {
              "Proceed to FamPay Payment (${HoneycombRepository.formatInr(total)}) ✨"
            } else {
              "Confirm Cash on Delivery (${HoneycombRepository.formatInr(total)}) 📦"
            },
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }
    }
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFFFBF5))
  ) {
    // Header
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 6.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      IconButton(onClick = onBackClicked, modifier = Modifier.testTag("cart_back_button")) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF381E0D))
      }
      Text(
        text = "Your Honeycomb Basket ✨",
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF381E0D)
      )
    }

    if (cartItems.isEmpty()) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(32.dp),
        contentAlignment = Alignment.Center
      ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Icon(
            imageVector = Icons.Default.ShoppingBag,
            contentDescription = null,
            tint = Color(0xFFD6C7BC),
            modifier = Modifier.size(64.dp)
          )
          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text = "Your basket is empty",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color(0xFF381E0D)
          )
          Text(
            text = "Discover warm, handmade crochet and knit items!",
            fontSize = 13.sp,
            color = Color(0xFF78716C)
          )
          Spacer(modifier = Modifier.height(16.dp))
          Button(
            onClick = onBackClicked,
            colors = ButtonDefaults.buttonColors(containerColor = HoneyGoldPrimary),
            shape = RoundedCornerShape(14.dp)
          ) {
            Text("Start Exploring ✨")
          }
        }
      }
    } else {
      LazyColumn(
        modifier = Modifier
          .weight(1f)
          .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        items(cartItems, key = { it.product.id }) { item ->
          Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
          ) {
            Row(
              modifier = Modifier.padding(12.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Box(
                modifier = Modifier
                  .size(64.dp)
                  .clip(RoundedCornerShape(14.dp))
              ) {
                ProductArtThumbnail(product = item.product, modifier = Modifier.matchParentSize())
              }

              Spacer(modifier = Modifier.width(12.dp))

              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = item.product.title,
                  fontWeight = FontWeight.Bold,
                  fontSize = 14.sp,
                  color = Color(0xFF26140A),
                  maxLines = 1
                )
                Text(
                  text = "${HoneycombRepository.formatInr(item.product.price)} each",
                  fontSize = 12.sp,
                  color = Color(0xFF78716C)
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Quantity Row
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier
                    .background(Color(0xFFF3ECE4), RoundedCornerShape(10.dp))
                    .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                  IconButton(
                    onClick = { onUpdateQuantity(item.product.id, item.quantity - 1) },
                    modifier = Modifier.size(26.dp)
                  ) {
                    Icon(Icons.Default.Remove, contentDescription = "Decrease", tint = Color(0xFF451A03), modifier = Modifier.size(14.dp))
                  }
                  Text(
                    text = "${item.quantity}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = Color(0xFF451A03),
                    modifier = Modifier.padding(horizontal = 6.dp)
                  )
                  IconButton(
                    onClick = { onUpdateQuantity(item.product.id, item.quantity + 1) },
                    modifier = Modifier.size(26.dp)
                  ) {
                    Icon(Icons.Default.Add, contentDescription = "Increase", tint = Color(0xFF451A03), modifier = Modifier.size(14.dp))
                  }
                }
              }

              Column(horizontalAlignment = Alignment.End) {
                IconButton(
                  onClick = { onRemoveItem(item.product.id) },
                  modifier = Modifier.size(32.dp).testTag("cart_remove_${item.product.id}")
                ) {
                  Icon(Icons.Default.Delete, contentDescription = "Remove", tint = Color(0xFFDC2626), modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                  text = HoneycombRepository.formatInr(item.product.price * item.quantity),
                  fontWeight = FontWeight.Bold,
                  fontSize = 14.sp,
                  color = Color(0xFF26140A)
                )
              }
            }
          }
        }

        // Promo Code Card
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Discount, contentDescription = null, tint = HoneyGoldPrimary, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "Honeycomb Promo Coupon ✨", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color(0xFF26140A))
              }

              Spacer(modifier = Modifier.height(8.dp))

              Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                  value = promoCode,
                  onValueChange = {
                    promoCode = it
                    promoError = null
                  },
                  placeholder = { Text("Use HONEY10") },
                  singleLine = true,
                  shape = RoundedCornerShape(12.dp),
                  modifier = Modifier.weight(1f).testTag("promo_code_input")
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                  onClick = {
                    if (promoCode.trim().equals("HONEY10", ignoreCase = true)) {
                      appliedPromo = "HONEY10"
                      discountPercent = 0.10
                      promoError = null
                    } else {
                      promoError = "Invalid code. Try HONEY10"
                    }
                  },
                  shape = RoundedCornerShape(12.dp),
                  colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFEF3C7), contentColor = Color(0xFFB45309)),
                  modifier = Modifier.testTag("apply_promo_button")
                ) {
                  Text("Apply", fontWeight = FontWeight.Bold)
                }
              }

              if (appliedPromo != null) {
                Text(
                  text = "✓ $appliedPromo applied (10% off your craft order!) 💛",
                  color = Color(0xFF15803D),
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(top = 4.dp)
                )
              } else if (promoError != null) {
                Text(
                  text = promoError!!,
                  color = Color(0xFFDC2626),
                  fontSize = 12.sp,
                  modifier = Modifier.padding(top = 4.dp)
                )
              }
            }
          }
        }

        // Bill Summary Card
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
          ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
              Text(text = "Order Summary", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF26140A))

              Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Subtotal", fontSize = 13.sp, color = Color(0xFF78716C))
                Text(HoneycombRepository.formatInr(subtotal), fontSize = 13.sp, color = Color(0xFF26140A))
              }

              if (discountAmount > 0) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                  Text("Promo Discount (10%)", fontSize = 13.sp, color = Color(0xFF15803D))
                  Text("-${HoneycombRepository.formatInr(discountAmount)}", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF15803D))
                }
              }

              Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Craft Delivery in India", fontSize = 13.sp, color = Color(0xFF78716C))
                Text(if (shipping == 0.0) "FREE ✨" else HoneycombRepository.formatInr(shipping), fontSize = 13.sp, color = if (shipping == 0.0) Color(0xFF15803D) else Color(0xFF26140A), fontWeight = if (shipping == 0.0) FontWeight.Bold else FontWeight.Normal)
              }

              HorizontalDivider(color = Color(0xFFF0E3D3), modifier = Modifier.padding(vertical = 4.dp))

              Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF26140A))
                Text(HoneycombRepository.formatInr(total), fontWeight = FontWeight.Bold, fontSize = 18.sp, color = HoneyGoldPrimary)
              }

              // FamPay & COD availability note
              Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFFFAF5ED),
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
              ) {
                Row(
                  modifier = Modifier.padding(8.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Icon(Icons.Default.Shield, contentDescription = null, tint = HoneyGoldPrimary, modifier = Modifier.size(14.dp))
                  Spacer(modifier = Modifier.width(6.dp))
                  Text(
                    text = "Accepting FamPay UPI & Cash on Delivery (COD) 🇮🇳",
                    fontSize = 11.sp,
                    color = Color(0xFF78350F),
                    fontWeight = FontWeight.Medium
                  )
                }
              }
            }
          }
        }

        item {
          Spacer(modifier = Modifier.height(16.dp))
        }
      }

      // Checkout Button Bar
      Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 8.dp
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Button(
            onClick = { showCheckoutSheet = true },
            modifier = Modifier
              .fillMaxWidth()
              .height(48.dp)
              .testTag("proceed_to_checkout_button"),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = HoneyGoldPrimary)
          ) {
            Text(
              text = "Proceed to Checkout · ${HoneycombRepository.formatInr(total)} ✨",
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold
            )
          }
        }
      }
    }
  }
}
