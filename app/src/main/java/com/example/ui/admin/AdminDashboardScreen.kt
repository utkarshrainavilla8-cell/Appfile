package com.example.ui.admin

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AdminNotification
import com.example.model.AdminUser
import com.example.model.Order
import com.example.model.OrderStatus
import com.example.model.Product
import com.example.ui.components.AnalyticsOverview
import com.example.ui.theme.HoneyGoldPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(
  products: List<Product>,
  orders: List<Order>,
  notifications: List<AdminNotification>,
  admins: List<AdminUser>,
  currentAdmin: AdminUser?,
  storeUpiId: String,
  onUpdateStoreUpiId: (String) -> Unit,
  onAddAdmin: (name: String, email: String, pass: String, role: String) -> Result<AdminUser>,
  onRemoveAdmin: (adminId: String) -> Boolean,
  onAddProduct: () -> Unit,
  onEditProduct: (Product) -> Unit,
  onDeleteProduct: (String) -> Unit,
  onChangePrice: (Product) -> Unit,
  onToggleOutOfStock: (String, Boolean) -> Unit,
  onUpdateStock: (String, Int) -> Unit,
  onUpdateOrderStatus: (String, OrderStatus) -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  var selectedTab by remember { mutableIntStateOf(0) }
  val tabs = listOf("Sales & UPI", "Manage Products", "Order Status", "Admin Team (${admins.size})")

  val unreadNotifications = notifications.filter { !it.isRead }
  val latestUnread = unreadNotifications.firstOrNull()

  var showEditUpiDialog by remember { mutableStateOf(false) }
  var inputUpiId by remember { mutableStateOf(storeUpiId) }

  var showAddAdminDialog by remember { mutableStateOf(false) }
  var adminToDelete by remember { mutableStateOf<AdminUser?>(null) }

  // Dialog to change or set the merchant PhonePe / UPI ID
  if (showEditUpiDialog) {
    AlertDialog(
      onDismissRequest = { showEditUpiDialog = false },
      icon = {
        Icon(
          Icons.Default.AccountBalanceWallet,
          contentDescription = null,
          tint = Color(0xFF6739B7),
          modifier = Modifier.size(36.dp)
        )
      },
      title = {
        Text(
          text = "Set PhonePe / UPI ID",
          fontWeight = FontWeight.Bold,
          fontSize = 18.sp,
          color = Color(0xFF26140A)
        )
      },
      text = {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
          Text(
            text = "Enter your PhonePe UPI ID (e.g. utkarshrainavilla8@ybl or yourmobile@ibl) where customer online payments will be credited directly with 0% fee.",
            fontSize = 12.sp,
            color = Color(0xFF57483F)
          )

          OutlinedTextField(
            value = inputUpiId,
            onValueChange = { inputUpiId = it },
            label = { Text("PhonePe / UPI ID") },
            placeholder = { Text("e.g. utkarshrainavilla8@ybl") },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().testTag("admin_fampay_upi_input")
          )

          Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color(0xFFF3E8FF),
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(
              text = "💡 PhonePe accounts usually have handles ending in @ybl, @ibl, or @axl. You can find yours in the PhonePe app under Profile > UPI IDs.",
              fontSize = 11.sp,
              color = Color(0xFF581C87),
              modifier = Modifier.padding(10.dp)
            )
          }
        }
      },
      confirmButton = {
        Button(
          onClick = {
            if (inputUpiId.isNotBlank()) {
              onUpdateStoreUpiId(inputUpiId.trim())
              Toast.makeText(context, "UPI ID updated to: ${inputUpiId.trim()}", Toast.LENGTH_SHORT).show()
              showEditUpiDialog = false
            }
          },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6739B7)),
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier.testTag("save_fampay_upi_button")
        ) {
          Text("Save PhonePe UPI ✨")
        }
      },
      dismissButton = {
        TextButton(onClick = { showEditUpiDialog = false }) {
          Text("Cancel", color = Color(0xFF78716C))
        }
      },
      shape = RoundedCornerShape(22.dp),
      containerColor = Color.White
    )
  }

  // Dialog to Add a New Admin User
  if (showAddAdminDialog) {
    var newAdminName by remember { mutableStateOf("") }
    var newAdminEmail by remember { mutableStateOf("") }
    var newAdminPass by remember { mutableStateOf("") }
    var newAdminRole by remember { mutableStateOf("Store Manager") }
    var addAdminError by remember { mutableStateOf<String?>(null) }
    var roleDropdownExpanded by remember { mutableStateOf(false) }
    val roles = listOf("Store Manager", "Order Lead", "Inventory Specialist", "Admin")

    AlertDialog(
      onDismissRequest = { showAddAdminDialog = false },
      icon = {
        Surface(
          shape = RoundedCornerShape(12.dp),
          color = Color(0xFFFEF3C7),
          modifier = Modifier.size(40.dp)
        ) {
          Box(contentAlignment = Alignment.Center) {
            Icon(
              Icons.Default.Group,
              contentDescription = null,
              tint = HoneyGoldPrimary,
              modifier = Modifier.size(24.dp)
            )
          }
        }
      },
      title = {
        Text(
          text = "Add New Admin ID",
          fontWeight = FontWeight.Bold,
          fontSize = 18.sp,
          color = Color(0xFF26140A)
        )
      },
      text = {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
          Text(
            text = "Create an additional administrator account. They can log in with their email and password to manage products, change prices, and oversee orders.",
            fontSize = 12.sp,
            color = Color(0xFF57483F)
          )

          OutlinedTextField(
            value = newAdminName,
            onValueChange = {
              newAdminName = it
              addAdminError = null
            },
            label = { Text("Full Name") },
            placeholder = { Text("e.g. Priya Sharma") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = HoneyGoldPrimary) },
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().testTag("new_admin_name_input")
          )

          OutlinedTextField(
            value = newAdminEmail,
            onValueChange = {
              newAdminEmail = it
              addAdminError = null
            },
            label = { Text("Email Address") },
            placeholder = { Text("e.g. priya@honeycomb.com") },
            leadingIcon = { Icon(Icons.Default.Mail, contentDescription = null, tint = HoneyGoldPrimary) },
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().testTag("new_admin_email_input")
          )

          OutlinedTextField(
            value = newAdminPass,
            onValueChange = {
              newAdminPass = it
              addAdminError = null
            },
            label = { Text("Password") },
            placeholder = { Text("Min 4 characters") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = HoneyGoldPrimary) },
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().testTag("new_admin_password_input")
          )

          ExposedDropdownMenuBox(
            expanded = roleDropdownExpanded,
            onExpandedChange = { roleDropdownExpanded = !roleDropdownExpanded },
            modifier = Modifier.fillMaxWidth()
          ) {
            OutlinedTextField(
              value = newAdminRole,
              onValueChange = {},
              readOnly = true,
              label = { Text("Admin Role") },
              trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = roleDropdownExpanded) },
              modifier = Modifier.menuAnchor().fillMaxWidth(),
              shape = RoundedCornerShape(10.dp)
            )
            ExposedDropdownMenu(
              expanded = roleDropdownExpanded,
              onDismissRequest = { roleDropdownExpanded = false }
            ) {
              roles.forEach { role ->
                DropdownMenuItem(
                  text = { Text(role) },
                  onClick = {
                    newAdminRole = role
                    roleDropdownExpanded = false
                  }
                )
              }
            }
          }

          if (addAdminError != null) {
            Text(
              text = addAdminError!!,
              color = Color(0xFFDC2626),
              fontSize = 12.sp,
              fontWeight = FontWeight.Medium
            )
          }
        }
      },
      confirmButton = {
        Button(
          onClick = {
            val result = onAddAdmin(newAdminName, newAdminEmail, newAdminPass, newAdminRole)
            result.onSuccess {
              Toast.makeText(context, "Added Admin: ${it.name} (${it.email})", Toast.LENGTH_SHORT).show()
              showAddAdminDialog = false
            }.onFailure { err ->
              addAdminError = err.message ?: "Failed to add admin"
            }
          },
          colors = ButtonDefaults.buttonColors(containerColor = HoneyGoldPrimary),
          shape = RoundedCornerShape(10.dp),
          modifier = Modifier.testTag("submit_create_admin_button")
        ) {
          Text("Create Admin ID ✨", fontWeight = FontWeight.Bold)
        }
      },
      dismissButton = {
        TextButton(onClick = { showAddAdminDialog = false }) {
          Text("Cancel", color = Color(0xFF78716C))
        }
      },
      shape = RoundedCornerShape(22.dp),
      containerColor = Color.White
    )
  }

  // Confirmation dialog for deleting an admin
  if (adminToDelete != null) {
    AlertDialog(
      onDismissRequest = { adminToDelete = null },
      title = { Text("Revoke Admin Access") },
      text = {
        Text("Are you sure you want to remove ${adminToDelete?.name} (${adminToDelete?.email}) from the Honeycomb Workshop admin team? They will no longer be able to log in.")
      },
      confirmButton = {
        Button(
          onClick = {
            val deleted = onRemoveAdmin(adminToDelete!!.id)
            if (deleted) {
              Toast.makeText(context, "Removed admin access", Toast.LENGTH_SHORT).show()
            } else {
              Toast.makeText(context, "Cannot remove primary Super Admin", Toast.LENGTH_SHORT).show()
            }
            adminToDelete = null
          },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC2626))
        ) {
          Text("Revoke Access")
        }
      },
      dismissButton = {
        TextButton(onClick = { adminToDelete = null }) {
          Text("Cancel")
        }
      }
    )
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFFFDF9))
  ) {
    // New Order Alert Banner (if unread incoming order exists!)
    if (latestUnread != null) {
      Surface(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp)
          .clickable { selectedTab = 2 }
          .testTag("admin_new_order_banner"),
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFFEF3C7),
        shadowElevation = 2.dp
      ) {
        Row(
          modifier = Modifier.padding(12.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Default.NotificationsActive,
            contentDescription = null,
            tint = Color(0xFFD97706),
            modifier = Modifier.size(24.dp)
          )
          Spacer(modifier = Modifier.width(10.dp))
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = latestUnread.title,
              fontWeight = FontWeight.Bold,
              fontSize = 13.sp,
              color = Color(0xFF78350F)
            )
            Text(
              text = latestUnread.message,
              fontSize = 11.sp,
              color = Color(0xFF92400E)
            )
          }
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = Color(0xFFD97706)
          ) {
            Text(
              text = "VIEW",
              color = Color.White,
              fontWeight = FontWeight.Bold,
              fontSize = 10.sp,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
          }
        }
      }
    }

    // Tabs Navigation
    PrimaryTabRow(
      selectedTabIndex = selectedTab,
      containerColor = Color.White,
      contentColor = HoneyGoldPrimary,
      indicator = {
        TabRowDefaults.PrimaryIndicator(
          modifier = Modifier.tabIndicatorOffset(selectedTab),
          color = HoneyGoldPrimary
        )
      }
    ) {
      tabs.forEachIndexed { index, title ->
        Tab(
          selected = selectedTab == index,
          onClick = { selectedTab = index },
          text = {
            Text(
              text = title,
              fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Medium,
              fontSize = 12.sp,
              color = if (selectedTab == index) HoneyGoldPrimary else Color(0xFF78716C)
            )
          },
          icon = {
            val icon = when (index) {
              0 -> Icons.Default.Analytics
              1 -> Icons.Default.Inventory2
              2 -> Icons.Default.ReceiptLong
              else -> Icons.Default.Group
            }
            Icon(
              imageVector = icon,
              contentDescription = null,
              tint = if (selectedTab == index) HoneyGoldPrimary else Color(0xFF78716C),
              modifier = Modifier.size(18.dp)
            )
          },
          modifier = Modifier.testTag("admin_tab_$index")
        )
      }
    }

    // Tab Content
    when (selectedTab) {
      0 -> {
        // Sales Analytics & PhonePe / UPI Payment Settings View
        Column(
          modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          // Merchant PhonePe / UPI Gateway Settings Card
          Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFFDE68A)),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
          ) {
            Column(modifier = Modifier.padding(16.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFF3E8FF),
                    modifier = Modifier.size(36.dp)
                  ) {
                    Box(contentAlignment = Alignment.Center) {
                      Icon(
                        Icons.Default.AccountBalanceWallet,
                        contentDescription = null,
                        tint = Color(0xFF6739B7),
                        modifier = Modifier.size(22.dp)
                      )
                    }
                  }
                  Spacer(modifier = Modifier.width(8.dp))
                  Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                      Text(
                        text = "PhonePe & UPI Payment Gateway",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color(0xFF26140A)
                      )
                    }
                    Text(
                      text = "Direct credit to your PhonePe linked bank account",
                      fontSize = 11.sp,
                      color = Color(0xFF78716C)
                    )
                  }
                }

                Surface(
                  shape = RoundedCornerShape(8.dp),
                  color = Color(0xFFDCFCE7)
                ) {
                  Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                  ) {
                    Icon(
                      Icons.Default.Check,
                      contentDescription = null,
                      tint = Color(0xFF15803D),
                      modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                      text = "ACTIVE",
                      color = Color(0xFF15803D),
                      fontSize = 10.sp,
                      fontWeight = FontWeight.Bold
                    )
                  }
                }
              }

              Spacer(modifier = Modifier.height(12.dp))

              Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFFAF5ED),
                modifier = Modifier.fillMaxWidth()
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Column {
                    Text(
                      text = "Merchant PhonePe / UPI VPA:",
                      fontSize = 11.sp,
                      color = Color(0xFF78716C)
                    )
                    Text(
                      text = storeUpiId,
                      fontSize = 14.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color(0xFF6739B7),
                      fontFamily = FontFamily.Monospace
                    )
                  }

                  Row {
                    OutlinedButton(
                      onClick = {
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("UPI ID", storeUpiId)
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(context, "UPI ID copied!", Toast.LENGTH_SHORT).show()
                      },
                      shape = RoundedCornerShape(8.dp),
                      modifier = Modifier.testTag("admin_copy_upi_button")
                    ) {
                      Icon(Icons.Default.ContentCopy, contentDescription = "Copy", modifier = Modifier.size(14.dp), tint = Color(0xFF6739B7))
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Button(
                      onClick = {
                        inputUpiId = storeUpiId
                        showEditUpiDialog = true
                      },
                      shape = RoundedCornerShape(8.dp),
                      colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6739B7)),
                      modifier = Modifier.testTag("admin_edit_upi_button")
                    ) {
                      Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(14.dp))
                      Spacer(modifier = Modifier.width(4.dp))
                      Text("Change UPI", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                  }
                }
              }

              Spacer(modifier = Modifier.height(8.dp))

              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Shield, contentDescription = null, tint = Color(0xFF15803D), modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = "Customers can pay via PhonePe, GPay, Paytm, or any UPI app · 0% commission",
                  fontSize = 11.sp,
                  color = Color(0xFF15803D),
                  fontWeight = FontWeight.Medium
                )
              }
            }
          }

          // Analytics Overview
          AnalyticsOverview(orders = orders, products = products)
          Spacer(modifier = Modifier.height(24.dp))
        }
      }
      1 -> {
        // Manage Products View (Add, change price, toggle out of stock)
        AdminProductManagementScreen(
          products = products,
          onAddProduct = onAddProduct,
          onEditProduct = onEditProduct,
          onDeleteProduct = onDeleteProduct,
          onChangePrice = onChangePrice,
          onToggleOutOfStock = onToggleOutOfStock,
          onUpdateStock = onUpdateStock
        )
      }
      2 -> {
        // Orders & Order Status View
        AdminOrderManagementScreen(
          orders = orders,
          onUpdateOrderStatus = onUpdateOrderStatus
        )
      }
      3 -> {
        // Admin Team & Multi-Admin Management View
        AdminTeamManagementScreen(
          admins = admins,
          currentAdmin = currentAdmin,
          onOpenAddAdminDialog = { showAddAdminDialog = true },
          onRequestDeleteAdmin = { adminToDelete = it }
        )
      }
    }
  }
}

@Composable
fun AdminTeamManagementScreen(
  admins: List<AdminUser>,
  currentAdmin: AdminUser?,
  onOpenAddAdminDialog: () -> Unit,
  onRequestDeleteAdmin: (AdminUser) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier.fillMaxSize().padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp),
    contentPadding = PaddingValues(bottom = 24.dp)
  ) {
    item {
      Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFDE68A)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = "Honeycomb Admin Team",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF26140A)
              )
              Text(
                text = "${admins.size} authorized administrators with workshop portal access",
                fontSize = 12.sp,
                color = Color(0xFF78716C)
              )
            }

            Button(
              onClick = onOpenAddAdminDialog,
              colors = ButtonDefaults.buttonColors(containerColor = HoneyGoldPrimary),
              shape = RoundedCornerShape(12.dp),
              modifier = Modifier.testTag("admin_add_admin_button")
            ) {
              Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
              Spacer(modifier = Modifier.width(4.dp))
              Text("Add Admin", fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
          }
        }
      }
    }

    item {
      Text(
        text = "AUTHORIZED ADMINISTRATORS",
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFB45309),
        modifier = Modifier.padding(start = 4.dp, top = 4.dp)
      )
    }

    items(admins, key = { it.id }) { admin ->
      Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
          containerColor = if (admin.isPrimarySuperAdmin) Color(0xFFFFFBEB) else Color.White
        ),
        border = androidx.compose.foundation.BorderStroke(
          1.dp,
          if (admin.isPrimarySuperAdmin) Color(0xFFFCD34D) else Color(0xFFE7E5E4)
        ),
        modifier = Modifier.fillMaxWidth().testTag("admin_card_${admin.id}")
      ) {
        Row(
          modifier = Modifier.padding(14.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          // Avatar Initial
          Surface(
            shape = CircleShape,
            color = if (admin.isPrimarySuperAdmin) HoneyGoldPrimary else Color(0xFFE0E7FF),
            modifier = Modifier.size(44.dp)
          ) {
            Box(contentAlignment = Alignment.Center) {
              Text(
                text = admin.name.take(1).uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = if (admin.isPrimarySuperAdmin) Color(0xFF451A03) else Color(0xFF3730A3)
              )
            }
          }

          Spacer(modifier = Modifier.width(12.dp))

          Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = admin.name,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color(0xFF26140A)
              )
              if (admin.isPrimarySuperAdmin) {
                Spacer(modifier = Modifier.width(6.dp))
                Surface(
                  shape = RoundedCornerShape(6.dp),
                  color = Color(0xFFD97706)
                ) {
                  Text(
                    text = "SUPER ADMIN",
                    color = Color.White,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                  )
                }
              }
            }

            Text(
              text = admin.email,
              fontSize = 12.sp,
              color = Color(0xFF78716C)
            )

            Row(
              modifier = Modifier.padding(top = 4.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Surface(
                shape = RoundedCornerShape(6.dp),
                color = Color(0xFFFAF5ED),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE5D5C2))
              ) {
                Text(
                  text = "Role: ${admin.role}",
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Medium,
                  color = Color(0xFF57483F),
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
              }

              if (admin.isPrimarySuperAdmin) {
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = "• Protected Primary Account",
                  fontSize = 10.sp,
                  color = Color(0xFF15803D),
                  fontWeight = FontWeight.Medium
                )
              }
            }
          }

          if (!admin.isPrimarySuperAdmin && !admin.email.equals("utkarshrainavilla8@gmail.com", ignoreCase = true)) {
            IconButton(
              onClick = { onRequestDeleteAdmin(admin) },
              modifier = Modifier.testTag("delete_admin_${admin.id}")
            ) {
              Icon(
                Icons.Default.Delete,
                contentDescription = "Remove admin",
                tint = Color(0xFFDC2626)
              )
            }
          }
        }
      }
    }
  }
}
