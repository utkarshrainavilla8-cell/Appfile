package com.example.model

/**
 * Model representing an authorized administrator in Honeycomb Stitches.
 * Supports primary Super Admin (Utkarsh Raina) as well as additional admin IDs
 * created dynamically from the Admin Workshop Portal.
 */
data class AdminUser(
  val id: String,
  val name: String,
  val email: String,
  val password: String,
  val role: String = "Admin", // "Super Admin", "Store Manager", "Order Lead", "Inventory Specialist"
  val isPrimarySuperAdmin: Boolean = false,
  val createdAt: Long = System.currentTimeMillis()
)
