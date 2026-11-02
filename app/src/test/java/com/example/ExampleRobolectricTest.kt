package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.HoneycombRepository
import com.example.model.OrderStatus
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Before
  fun setUp() {
    HoneycombRepository.resetForTesting()
  }

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Honeycomb", appName)
  }

  @Test
  fun `primary super admin authenticates successfully`() {
    val admin = HoneycombRepository.authenticateAdmin("utkarshrainavilla8@gmail.com", "2550archana")
    assertNotNull(admin)
    assertEquals("utkarshrainavilla8@gmail.com", admin?.email)
    assertEquals("Super Admin", admin?.role)
    assertTrue(admin?.isPrimarySuperAdmin == true)
  }

  @Test
  fun `admin can add additional admin IDs dynamically`() {
    val result = HoneycombRepository.addAdmin("Rohan Das", "rohan@honeycomb.com", "pass4567", "Order Lead")
    assertTrue(result.isSuccess)

    val loggedIn = HoneycombRepository.authenticateAdmin("rohan@honeycomb.com", "pass4567")
    assertNotNull(loggedIn)
    assertEquals("Order Lead", loggedIn?.role)
  }

  @Test
  fun `repository places order and triggers admin notification`() {
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
  }

  @Test
  fun `admin can update product price and stock status`() {
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
  fun `online payment with PhonePe UPI records UPI ID and transaction ref`() {
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
  fun `cash on delivery order sets isPaidOnline to false`() {
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
