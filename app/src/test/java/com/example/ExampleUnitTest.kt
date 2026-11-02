package com.example

import com.example.data.HoneycombRepository
import com.example.model.OrderStatus
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Unit tests verifying Honeycomb Stitches repository logic,
 * multi-admin authentication, and PhonePe/UPI payment flows.
 */
class ExampleUnitTest {

  @Before
  fun setUp() {
    HoneycombRepository.resetForTesting()
  }

  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }

  @Test
  fun primaryAdminAuthenticatesSuccessfullyWithGivenCredentials() {
    val admin = HoneycombRepository.authenticateAdmin(
      identifier = "utkarshrainavilla8@gmail.com",
      pass = "2550archana"
    )

    assertNotNull("Primary Super Admin should authenticate successfully", admin)
    assertEquals("utkarshrainavilla8@gmail.com", admin?.email)
    assertEquals("Super Admin", admin?.role)
    assertTrue("Primary admin should have isPrimarySuperAdmin = true", admin?.isPrimarySuperAdmin == true)
  }

  @Test
  fun adminCanCreateNewAdminAndAuthenticateWithNewCredentials() {
    val initialCount = HoneycombRepository.admins.value.size

    val createResult = HoneycombRepository.addAdmin(
      name = "Priya Sharma",
      email = "priya.sharma@honeycomb.com",
      pass = "priyaPass123",
      role = "Store Manager"
    )

    assertTrue("Admin creation should succeed", createResult.isSuccess)
    val newAdmin = createResult.getOrNull()
    assertNotNull(newAdmin)
    assertEquals("Priya Sharma", newAdmin?.name)
    assertEquals(initialCount + 1, HoneycombRepository.admins.value.size)

    // Authenticate with newly created credentials
    val authResult = HoneycombRepository.authenticateAdmin(
      identifier = "priya.sharma@honeycomb.com",
      pass = "priyaPass123"
    )
    assertNotNull("New admin should authenticate with their credentials", authResult)
    assertEquals("Store Manager", authResult?.role)
    assertFalse("Secondary admin should not be primary super admin", authResult?.isPrimarySuperAdmin == true)
  }

  @Test
  fun cannotCreateAdminWithDuplicateEmail() {
    val duplicateResult = HoneycombRepository.addAdmin(
      name = "Fake Admin",
      email = "utkarshrainavilla8@gmail.com",
      pass = "newpass123",
      role = "Admin"
    )

    assertTrue("Duplicate admin registration must fail", duplicateResult.isFailure)
  }

  @Test
  fun primarySuperAdminCannotBeDeleted() {
    val primary = HoneycombRepository.admins.value.first { it.isPrimarySuperAdmin }
    val deleted = HoneycombRepository.removeAdmin(primary.id)

    assertFalse("Primary Super Admin must be protected from deletion", deleted)
    assertTrue("Primary admin must still be in admins list", HoneycombRepository.admins.value.any { it.id == primary.id })
  }

  @Test
  fun secondaryAdminCanBeDeleted() {
    val createResult = HoneycombRepository.addAdmin(
      name = "Temporary Admin",
      email = "temp@crafts.com",
      pass = "temppass",
      role = "Order Lead"
    )
    val secondary = createResult.getOrThrow()

    val deleted = HoneycombRepository.removeAdmin(secondary.id)
    assertTrue("Secondary admin should be deleted successfully", deleted)
    assertFalse("Secondary admin should no longer be in admins list", HoneycombRepository.admins.value.any { it.id == secondary.id })
  }

  @Test
  fun repositoryPlacesOrderAndTriggersAdminNotification() {
    val initialNotificationsCount = HoneycombRepository.notifications.value.size
    val product = HoneycombRepository.products.value.first()

    HoneycombRepository.addToCart(product, 2)
    val order = HoneycombRepository.placeOrder(
      customerName = "Anna Joy",
      customerEmail = "anna@example.com",
      customerAddress = "123 Honey Lane",
      paymentMethod = "Online (PhonePe / UPI)",
      discountAmount = 0.0,
      payeeUpiId = "utkarshrainavilla8@ybl",
      upiTransactionRef = "UPI9876543210",
      isPaidOnline = true
    )

    assertNotNull(order)
    assertEquals(OrderStatus.PENDING, order?.status)
    assertEquals(initialNotificationsCount + 1, HoneycombRepository.notifications.value.size)
    assertEquals(true, order?.isPaidOnline)
    assertEquals("utkarshrainavilla8@ybl", order?.payeeUpiId)
  }

  @Test
  fun adminCanUpdateProductPriceAndStockStatus() {
    val product = HoneycombRepository.products.value.first()
    val testPrice = 1299.00

    HoneycombRepository.updateProductPrice(product.id, testPrice)
    val updated = HoneycombRepository.products.value.find { it.id == product.id }
    assertEquals(testPrice, updated?.price ?: 0.0, 0.01)

    HoneycombRepository.toggleOutOfStock(product.id, true)
    val outOfStockProduct = HoneycombRepository.products.value.find { it.id == product.id }
    assertTrue(outOfStockProduct?.isOutOfStock == true)
  }

  @Test
  fun onlinePaymentWithPhonePeUpiRecordsUpiIdAndTransactionRef() {
    val product = HoneycombRepository.products.value.first()
    HoneycombRepository.addToCart(product, 1)

    val phonePeUpi = "utkarshrainavilla8@ybl"
    val utr = "UPI123456789012"
    HoneycombRepository.updateStoreUpiId(phonePeUpi)
    assertEquals(phonePeUpi, HoneycombRepository.storeUpiId.value)

    val order = HoneycombRepository.placeOrder(
      customerName = "Priya Sharma",
      customerEmail = "priya@example.com",
      customerAddress = "Flat 402, Lotus Greens, Bangalore",
      paymentMethod = "Online (PhonePe / UPI)",
      discountAmount = 0.0,
      payeeUpiId = phonePeUpi,
      upiTransactionRef = utr,
      isPaidOnline = true
    )

    assertNotNull(order)
    assertEquals(phonePeUpi, order?.payeeUpiId)
    assertEquals(utr, order?.upiTransactionRef)
    assertEquals(true, order?.isPaidOnline)
  }

  @Test
  fun cashOnDeliveryOrderSetsIsPaidOnlineToFalse() {
    val product = HoneycombRepository.products.value.first { !it.isOutOfStock }
    HoneycombRepository.addToCart(product, 1)

    val order = HoneycombRepository.placeOrder(
      customerName = "Rahul Verma",
      customerEmail = "rahul@example.com",
      customerAddress = "Sector 14, Gurugram",
      paymentMethod = "Cash on Delivery (COD)",
      discountAmount = 0.0,
      payeeUpiId = null,
      upiTransactionRef = null,
      isPaidOnline = false
    )

    assertNotNull(order)
    assertEquals("Cash on Delivery (COD)", order?.paymentMethod)
    assertEquals(false, order?.isPaidOnline)
  }
}
