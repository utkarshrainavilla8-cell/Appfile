# 🍯 Honeycomb Stitches · Web Application

A full-fledged, responsive, modern artisanal e-commerce web application converted directly from the Honeycomb Stitches Android mobile app.

---

## 🌟 Features Included

1. **Artisanal Dreamy UI & Aesthetic**:
   - Palette inspired by the warm honeycomb stitches logo: amber gold, creamy whites, blush pink, and honeybees.
   - Built with modern Tailwind CSS and FontAwesome 6 icons.
   - 100% mobile, tablet, and desktop responsive layout.

2. **Customer Storefront**:
   - Product Catalog with categories: Amigurumi Plushies, Honeycomb Blankets, Cardigans & Sweaters, Accessories, DIY Kits.
   - Real-time search by title, description, and material.
   - Prices listed in Indian Rupees (₹) with original strikethrough price and stock indicators.
   - Interactive Wishlist counter.

3. **Product Detail & Customer Reviews**:
   - Large visual previews, materials breakdown, craft time, and stock availability.
   - Customer Reviews section with 5-star ratings.
   - Real-time "Write a Review" form allowing any visitor to submit reviews immediately.

4. **Bag & Shopping Cart**:
   - Slide-out Cart Drawer with quantity adjusters (`+` / `-`) and remove options.
   - Promo coupon codes: `HONEY10` (10% off), `CUTE15` (15% off), `DREAMY20` (20% off).
   - Dynamic shipping calculation (Free on orders over ₹999).

5. **Checkout & Payment Gateway**:
   - **Online Payment via PhonePe / FamPay UPI**:
     - Merchant UPI ID: `utkarshrainavilla8@ybl` (PhonePe / UPI)
     - Live dynamic QR code generation for the exact order total in ₹.
     - Direct deep link button to open UPI apps (PhonePe, GPay, Paytm).
     - Transaction reference / UTR input for instant verification.
   - **Cash on Delivery (COD)**:
     - Full COD checkout support with instant confirmation.

6. **Real-time Order Tracking**:
   - Live 5-stage visual progress timeline:
     1. Order Placed 📝
     2. Handcrafting in Workshop 🧶
     3. Quality Checked & Packed 🎁
     4. Dispatched / Out for Delivery 🚚
     5. Delivered with Love 🏡
   - Full order details, delivery address, and payment method record.

7. **Admin Management Portal & Analytics**:
   - Secure login modal (Email: `utkarshrainavilla8@gmail.com`, Password: `2550archana`).
   - Clean, professional login (passwords are masked with no exposed plaintext cards).
   - **Sales Analytics Dashboard**: Total Revenue (₹), Total Orders, Average Order Value (AOV), Inventory Health, and Top-Selling Products ranking.
   - **Product Inventory Control**:
     - Quick inline price updater (change product price with instant sync).
     - Single-click stock status toggle ("In Stock" / "Out of Stock").
     - Add new handcrafted product with photo emoji, materials, craft time, badge, and description.
     - Delete product.
   - **Order Status Management**: Update status from Placed → Crafting → Dispatched → Delivered.
   - **Real-time Notifications**: Live alerts whenever an online or COD order is placed.
   - **Admin Team**: Add more admin accounts with custom roles (Workshop Manager, Catalog Editor, etc.). Primary Super Admin is protected.
   - **Store UPI Configuration**: Customize merchant UPI ID and payee details.

---

## 🚀 How to Run & Deploy

### Option 1: Open Locally in Any Browser (Zero Install Required!)
Simply double-click `index.html` or open it with Google Chrome, Microsoft Edge, Safari, or Firefox.

### Option 2: Run with Any Local HTTP Server
Using Python:
```bash
cd website
python3 -m http.server 8000
```
Then visit `http://localhost:8000` in your browser.

Using Node.js / npx:
```bash
npx serve website
```

### Option 3: Deploy Online in 1 Click (Free)
- **Netlify**: Drag and drop the `website` folder into [Netlify Drop](https://app.netlify.com/drop).
- **Vercel**: Run `vercel website` or push to GitHub and connect repository.
- **GitHub Pages**: Push this repository to GitHub and enable GitHub Pages on the `website` directory.
- **Firebase Hosting**: Run `firebase deploy --only hosting`.
