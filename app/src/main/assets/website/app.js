// Honeycomb Stitches - Web Application Logic
// Complete E-Commerce Storefront + Admin Management Portal

// Default Seed Data
const DEFAULT_PRODUCTS = [
  {
    id: "prod-1",
    title: "Bumblebee Amigurumi Plushie",
    category: "Amigurumi",
    price: 849,
    originalPrice: 999,
    rating: 4.9,
    reviewCount: 28,
    isOutOfStock: false,
    stockQuantity: 12,
    description: "Our signature honey bumblebee! Handcrafted with ultra-soft organic velvet yarn, cute embroidered smile, and tiny gossamer wings. Filled with hypoallergenic cloud stuffing.",
    materials: "100% Cotton & Velvet Yarn, Safety Eyes, Hypoallergenic stuffing",
    estimatedCraftTime: "Ready to ship · Handcrafted in 2 days",
    badge: "Bestseller ✨",
    colorHex: "#F59E0B",
    iconType: "🐝",
    salesCount: 142
  },
  {
    id: "prod-2",
    title: "Honeycomb Hexagon Blanket",
    category: "Blankets",
    price: 3499,
    originalPrice: 3999,
    rating: 4.9,
    reviewCount: 15,
    isOutOfStock: false,
    stockQuantity: 4,
    description: "Intricate interlocking honey-golden hexagons stitched together with dreamy cream border. Ultra-soft and cozy for dreamy evenings or afternoon chai.",
    materials: "Merino Wool Blend, Hand-stitched hex motifs (50x60 inches)",
    estimatedCraftTime: "Made to order · 4 days craft time",
    badge: "Staff Pick 💛",
    colorHex: "#D97706",
    iconType: "🧶",
    salesCount: 64
  },
  {
    id: "prod-3",
    title: "Sunny Meadow Crochet Cardigan",
    category: "Sweaters",
    price: 2899,
    originalPrice: 3499,
    rating: 4.8,
    reviewCount: 19,
    isOutOfStock: false,
    stockQuantity: 6,
    description: "Lightweight yet warm balloon-sleeve cardigan with daisy granny squares and carved wooden buttons. Dreamy oversized fit that drapes beautifully.",
    materials: "Soft Bamboo & Cotton Yarn, Carved Wooden Buttons",
    estimatedCraftTime: "Handmade · 3 days craft time",
    badge: "Trending 🌸",
    colorHex: "#E59500",
    iconType: "🌸",
    salesCount: 52
  },
  {
    id: "prod-4",
    title: "Golden Honeycomb Beanie & Mitts",
    category: "Accessories",
    price: 1199,
    originalPrice: 1499,
    rating: 4.9,
    reviewCount: 11,
    isOutOfStock: false,
    stockQuantity: 9,
    description: "Textured waffle and honeycomb knit stitch beanie paired with fingerless mittens. Provides soothing warmth and easy touchscreen use.",
    materials: "Alpaca & Wool Blend, Stretchy Ribbed Brim",
    estimatedCraftTime: "In Stock · Ships within 24h",
    badge: "Cozy Cloud ☁️",
    colorHex: "#FBBF24",
    iconType: "🧣",
    salesCount: 38
  },
  {
    id: "prod-5",
    title: "Beginner Bee & Daisy DIY Kit",
    category: "Kits & Yarn",
    price: 899,
    originalPrice: 1099,
    rating: 5.0,
    reviewCount: 34,
    isOutOfStock: false,
    stockQuantity: 18,
    description: "Everything you need to crochet your first bee! Includes 3 skeins of chunky cotton, ergonomic 4.5mm bamboo hook, stuffing, stitch markers, and video instructions.",
    materials: "3x Skeins Cotton Yarn, 4.5mm Hook, Stitch Markers, Video Guide",
    estimatedCraftTime: "Instant shipping · Beginner friendly",
    badge: "Starter Kit 🪡",
    colorHex: "#10B981",
    iconType: "🪡",
    salesCount: 97
  },
  {
    id: "prod-6",
    title: "Pastel Daisy Granny Square Tote",
    category: "Accessories",
    price: 1299,
    originalPrice: 1599,
    rating: 4.7,
    reviewCount: 14,
    isOutOfStock: false,
    stockQuantity: 5,
    description: "Boho chic shoulder tote bag hand-crocheted from 13 colorful daisy squares with sturdy reinforced inner canvas lining and wide straps.",
    materials: "100% Recycled Cotton Yarn, Reinforced Cotton Canvas Lining",
    estimatedCraftTime: "Handmade · Ready in 2 days",
    badge: "Handmade Love 💖",
    colorHex: "#EC4899",
    iconType: "👜",
    salesCount: 45
  }
];

const DEFAULT_REVIEWS = [
  {
    id: "rev-1",
    productId: "prod-1",
    author: "Ananya Sharma",
    rating: 5,
    date: "18 Oct, 2024",
    title: "Cutest plushie I have ever owned! 🐝",
    comment: "The stitching is so neat, and the velvet yarn is unimaginably soft! It sits on my desk and brightens my workday every single day.",
    verifiedPurchase: true
  },
  {
    id: "rev-2",
    productId: "prod-1",
    author: "Pooja Verma",
    rating: 5,
    date: "25 Oct, 2024",
    title: "My niece loved it so much",
    comment: "Bought this as a birthday present and she hasn't let go of it for a week. Beautiful packaging too with a handwritten note!",
    verifiedPurchase: true
  },
  {
    id: "rev-3",
    productId: "prod-2",
    author: "Rohit & Megha",
    rating: 5,
    date: "02 Nov, 2024",
    title: "Heirloom quality blanket!",
    comment: "The hexagon pattern and color coordination are breathtaking. You can feel the hours of love put into this blanket.",
    verifiedPurchase: true
  },
  {
    id: "rev-4",
    productId: "prod-3",
    author: "Sneha Patel",
    rating: 5,
    date: "12 Nov, 2024",
    title: "Drapes like a dreamy cloud 🌸",
    comment: "Got so many compliments at college! The wooden buttons add such a rustic handmade charm. Worth every rupee.",
    verifiedPurchase: true
  }
];

const DEFAULT_ORDERS = [
  {
    id: "HS-84291",
    customerName: "Pooja Verma",
    phone: "+91 98765 43210",
    address: "B-402, Lotus Meadows, Indira Nagar, Bengaluru, Karnataka 560038",
    items: [
      { productTitle: "Bumblebee Amigurumi Plushie", price: 849, quantity: 1 }
    ],
    subtotal: 849,
    discount: 85,
    shipping: 0,
    total: 764,
    status: "CRAFTING",
    placedAt: "28 Oct, 2024 · 3:45 PM",
    paymentMethod: "PhonePe / FamPay UPI (Online)",
    payeeUpi: "utkarshrainavilla8@ybl",
    isPaidOnline: true,
    upiRef: "PHONEPE-7492104821"
  },
  {
    id: "HS-79104",
    customerName: "Rahul Khanna",
    phone: "+91 98111 22334",
    address: "Flat 12A, Orchid Towers, Vasant Vihar, New Delhi 110057",
    items: [
      { productTitle: "Honeycomb Hexagon Blanket", price: 3499, quantity: 1 }
    ],
    subtotal: 3499,
    discount: 350,
    shipping: 0,
    total: 3149,
    status: "DISPATCHED",
    placedAt: "26 Oct, 2024 · 11:20 AM",
    paymentMethod: "Cash on Delivery (COD)",
    payeeUpi: "utkarshrainavilla8@ybl",
    isPaidOnline: false
  }
];

const DEFAULT_ADMINS = [
  {
    id: "admin-primary",
    name: "Utkarsh Raina",
    email: "utkarshrainavilla8@gmail.com",
    password: "2550archana",
    role: "Super Admin",
    isPrimary: true
  }
];

const DEFAULT_NOTIFICATIONS = [
  {
    id: "notif-1",
    title: "New Online Order #HS-84291 ✨",
    message: "Pooja Verma placed an order for ₹764. Paid via PhonePe UPI (utkarshrainavilla8@ybl).",
    time: "28 Oct, 2024",
    isRead: false
  },
  {
    id: "notif-2",
    title: "New COD Order #HS-79104 📦",
    message: "Rahul Khanna ordered Honeycomb Hexagon Blanket (₹3,149) via Cash on Delivery.",
    time: "26 Oct, 2024",
    isRead: true
  }
];

// App State Manager with LocalStorage
class Store {
  constructor() {
    this.products = this.load('hs_products', DEFAULT_PRODUCTS);
    this.reviews = this.load('hs_reviews', DEFAULT_REVIEWS);
    this.orders = this.load('hs_orders', DEFAULT_ORDERS);
    this.admins = this.load('hs_admins', DEFAULT_ADMINS);
    this.notifications = this.load('hs_notifications', DEFAULT_NOTIFICATIONS);
    this.cart = this.load('hs_cart', []);
    this.wishlist = this.load('hs_wishlist', ['prod-1', 'prod-3']);
    this.currentAdmin = this.load('hs_session_admin', null);
    this.storeUpiId = this.load('hs_upi_id', 'utkarshrainavilla8@ybl');
    this.appliedCoupon = null;
  }

  load(key, fallback) {
    try {
      const saved = localStorage.getItem(key);
      return saved ? JSON.parse(saved) : fallback;
    } catch (e) {
      return fallback;
    }
  }

  save(key, val) {
    try {
      localStorage.setItem(key, JSON.stringify(val));
    } catch (e) {
      console.error(e);
    }
  }

  persistAll() {
    this.save('hs_products', this.products);
    this.save('hs_reviews', this.reviews);
    this.save('hs_orders', this.orders);
    this.save('hs_admins', this.admins);
    this.save('hs_notifications', this.notifications);
    this.save('hs_cart', this.cart);
    this.save('hs_wishlist', this.wishlist);
    this.save('hs_session_admin', this.currentAdmin);
    this.save('hs_upi_id', this.storeUpiId);
  }

  formatInr(val) {
    return '₹' + Math.round(val).toLocaleString('en-IN');
  }

  // Cart operations
  addToCart(product, qty = 1) {
    if (product.isOutOfStock) return;
    const existing = this.cart.find(i => i.productId === product.id);
    if (existing) {
      existing.quantity += qty;
    } else {
      this.cart.push({
        productId: product.id,
        title: product.title,
        price: product.price,
        badge: product.badge,
        iconType: product.iconType,
        colorHex: product.colorHex,
        quantity: qty
      });
    }
    this.save('hs_cart', this.cart);
    this.updateCartUI();
  }

  updateCartQty(productId, delta) {
    const item = this.cart.find(i => i.productId === productId);
    if (item) {
      item.quantity += delta;
      if (item.quantity <= 0) {
        this.cart = this.cart.filter(i => i.productId !== productId);
      }
    }
    this.save('hs_cart', this.cart);
    this.updateCartUI();
  }

  removeFromCart(productId) {
    this.cart = this.cart.filter(i => i.productId !== productId);
    this.save('hs_cart', this.cart);
    this.updateCartUI();
  }

  clearCart() {
    this.cart = [];
    this.appliedCoupon = null;
    this.save('hs_cart', this.cart);
    this.updateCartUI();
  }

  toggleWishlist(productId) {
    if (this.wishlist.includes(productId)) {
      this.wishlist = this.wishlist.filter(id => id !== productId);
    } else {
      this.wishlist.push(productId);
    }
    this.save('hs_wishlist', this.wishlist);
    this.renderProducts();
    this.updateNavBadges();
  }

  updateNavBadges() {
    const cartCountEl = document.getElementById('nav-cart-count');
    const wishCountEl = document.getElementById('nav-wish-count');
    const adminNotifBadge = document.getElementById('nav-admin-badge');
    
    const cartItemsCount = this.cart.reduce((sum, item) => sum + item.quantity, 0);
    if (cartCountEl) cartCountEl.innerText = cartItemsCount;
    if (wishCountEl) wishCountEl.innerText = this.wishlist.length;
    
    const unreadCount = this.notifications.filter(n => !n.isRead).length;
    if (adminNotifBadge) {
      if (unreadCount > 0) {
        adminNotifBadge.classList.remove('hidden');
        adminNotifBadge.innerText = unreadCount;
      } else {
        adminNotifBadge.classList.add('hidden');
      }
    }
  }

  updateCartUI() {
    this.updateNavBadges();
    const cartItemsContainer = document.getElementById('cart-items-container');
    const cartSubtotalEl = document.getElementById('cart-subtotal');
    const cartDiscountEl = document.getElementById('cart-discount');
    const cartShippingEl = document.getElementById('cart-shipping');
    const cartTotalEl = document.getElementById('cart-total');
    const emptyCartMsg = document.getElementById('empty-cart-msg');
    const cartFooter = document.getElementById('cart-footer');

    if (!cartItemsContainer) return;

    if (this.cart.length === 0) {
      cartItemsContainer.innerHTML = '';
      if (emptyCartMsg) emptyCartMsg.classList.remove('hidden');
      if (cartFooter) cartFooter.classList.add('hidden');
      return;
    }

    if (emptyCartMsg) emptyCartMsg.classList.add('hidden');
    if (cartFooter) cartFooter.classList.remove('hidden');

    const subtotal = this.cart.reduce((sum, i) => sum + (i.price * i.quantity), 0);
    let discount = 0;
    if (this.appliedCoupon === 'HONEY10') discount = subtotal * 0.10;
    if (this.appliedCoupon === 'CUTE15') discount = subtotal * 0.15;
    if (this.appliedCoupon === 'DREAMY20') discount = subtotal * 0.20;

    const shipping = subtotal > 999 || subtotal === 0 ? 0 : 99;
    const total = Math.max(0, subtotal - discount + shipping);

    cartSubtotalEl.innerText = this.formatInr(subtotal);
    cartDiscountEl.innerText = discount > 0 ? `-${this.formatInr(discount)}` : '₹0';
    cartShippingEl.innerText = shipping === 0 ? 'FREE' : this.formatInr(shipping);
    cartTotalEl.innerText = this.formatInr(total);

    cartItemsContainer.innerHTML = this.cart.map(item => `
      <div class="flex items-center gap-3 p-3 bg-white rounded-xl border border-amber-100 honey-shadow-sm">
        <div class="w-14 h-14 rounded-lg flex items-center justify-center text-2xl" style="background-color: ${item.colorHex}20; color: ${item.colorHex};">
          ${item.iconType || '🧶'}
        </div>
        <div class="flex-1 min-w-0">
          <h4 class="font-bold text-sm text-stone-800 truncate">${item.title}</h4>
          <p class="text-xs text-amber-700 font-semibold">${this.formatInr(item.price)} each</p>
          <div class="flex items-center gap-2 mt-1">
            <button onclick="store.updateCartQty('${item.productId}', -1)" class="w-6 h-6 rounded-full bg-amber-50 hover:bg-amber-200 text-stone-700 text-xs font-bold flex items-center justify-center">-</button>
            <span class="text-xs font-bold text-stone-800 px-1">${item.quantity}</span>
            <button onclick="store.updateCartQty('${item.productId}', 1)" class="w-6 h-6 rounded-full bg-amber-50 hover:bg-amber-200 text-stone-700 text-xs font-bold flex items-center justify-center">+</button>
          </div>
        </div>
        <div class="text-right">
          <p class="font-bold text-stone-800 text-sm">${this.formatInr(item.price * item.quantity)}</p>
          <button onclick="store.removeFromCart('${item.productId}')" class="text-xs text-stone-400 hover:text-red-500 mt-1">
            <i class="fa-solid fa-trash-can"></i>
          </button>
        </div>
      </div>
    `).join('');
  }

  applyCoupon(code) {
    const clean = code.trim().toUpperCase();
    if (['HONEY10', 'CUTE15', 'DREAMY20'].includes(clean)) {
      this.appliedCoupon = clean;
      this.updateCartUI();
      alert(`🎉 Coupon "${clean}" applied successfully!`);
    } else {
      alert('Invalid coupon code. Try HONEY10, CUTE15, or DREAMY20 ✨');
    }
  }

  // Render product grid
  renderProducts(filterCategory = 'ALL', searchQuery = '') {
    const grid = document.getElementById('products-grid');
    if (!grid) return;

    let list = this.products;

    if (filterCategory !== 'ALL') {
      list = list.filter(p => p.category.toLowerCase().includes(filterCategory.toLowerCase()));
    }

    if (searchQuery.trim()) {
      const q = searchQuery.toLowerCase();
      list = list.filter(p => 
        p.title.toLowerCase().includes(q) || 
        p.description.toLowerCase().includes(q) ||
        p.category.toLowerCase().includes(q)
      );
    }

    if (list.length === 0) {
      grid.innerHTML = `
        <div class="col-span-full text-center py-16 bg-white/60 rounded-3xl border border-amber-100 p-8">
          <span class="text-5xl">🍯</span>
          <h3 class="text-xl font-bold text-stone-700 mt-3">No handcrafted items found</h3>
          <p class="text-stone-500 text-sm mt-1">Try another category or clear your search.</p>
        </div>
      `;
      return;
    }

    grid.innerHTML = list.map(product => {
      const isWish = this.wishlist.includes(product.id);
      return `
        <div class="bg-white rounded-3xl p-4 border border-amber-100 honey-shadow-hover relative flex flex-col justify-between">
          <div>
            <!-- Badge & Wishlist -->
            <div class="flex justify-between items-center mb-2">
              <span class="text-xs font-semibold px-2.5 py-1 rounded-full ${product.badge.includes('Bestseller') ? 'badge-gold' : 'badge-blush'}">
                ${product.badge}
              </span>
              <button onclick="store.toggleWishlist('${product.id}')" class="w-8 h-8 rounded-full flex items-center justify-center transition-transform hover:scale-110 ${isWish ? 'text-pink-500 bg-pink-50' : 'text-stone-400 bg-stone-50'}">
                <i class="fa-${isWish ? 'solid' : 'regular'} fa-heart text-sm"></i>
              </button>
            </div>

            <!-- Product Visual Artwork -->
            <div onclick="openProductModal('${product.id}')" class="cursor-pointer h-44 rounded-2xl flex flex-col items-center justify-center relative overflow-hidden group" style="background: linear-gradient(135deg, ${product.colorHex}15, ${product.colorHex}30);">
              <span class="text-6xl transform group-hover:scale-110 transition-transform duration-300 drop-shadow-sm">${product.iconType}</span>
              ${product.isOutOfStock ? `
                <div class="absolute inset-0 bg-stone-900/60 backdrop-blur-xs flex items-center justify-center">
                  <span class="bg-red-500 text-white text-xs font-bold px-3 py-1 rounded-full uppercase tracking-wider">Out of Stock</span>
                </div>
              ` : ''}
              <div class="absolute bottom-2 right-2 opacity-0 group-hover:opacity-100 transition-opacity bg-white/90 text-stone-700 text-xs px-2 py-0.5 rounded-md font-medium shadow-xs">
                Quick View
              </div>
            </div>

            <!-- Category & Title -->
            <div class="mt-3">
              <span class="text-xs font-medium text-amber-700 uppercase tracking-wide">${product.category}</span>
              <h3 onclick="openProductModal('${product.id}')" class="font-bold text-stone-800 text-base leading-snug mt-0.5 cursor-pointer hover:text-amber-600 transition-colors">
                ${product.title}
              </h3>
            </div>

            <!-- Rating & Sales -->
            <div class="flex items-center gap-1.5 mt-1.5 text-xs">
              <div class="flex text-amber-400">
                <i class="fa-solid fa-star"></i>
              </div>
              <span class="font-bold text-stone-700">${product.rating}</span>
              <span class="text-stone-400">(${product.reviewCount} reviews)</span>
              <span class="text-stone-300">·</span>
              <span class="text-amber-800/80 font-medium">${product.salesCount} sold</span>
            </div>
          </div>

          <!-- Price & Action -->
          <div class="mt-4 pt-3 border-t border-amber-50 flex items-center justify-between">
            <div>
              <div class="flex items-baseline gap-1.5">
                <span class="text-lg font-bold text-stone-900">${this.formatInr(product.price)}</span>
                ${product.originalPrice ? `<span class="text-xs text-stone-400 line-through">${this.formatInr(product.originalPrice)}</span>` : ''}
              </div>
              <span class="text-[10px] text-emerald-600 font-semibold">
                ${product.isOutOfStock ? 'Sold out' : `In stock (${product.stockQuantity})`}
              </span>
            </div>

            <button onclick="store.addToCartById('${product.id}')" 
              ${product.isOutOfStock ? 'disabled' : ''}
              class="px-3.5 py-2 rounded-xl text-xs font-bold transition-all flex items-center gap-1.5 ${
                product.isOutOfStock 
                  ? 'bg-stone-200 text-stone-400 cursor-not-allowed' 
                  : 'bg-amber-500 hover:bg-amber-600 active:scale-95 text-white shadow-xs hover:shadow-md'
              }">
              <i class="fa-solid fa-bag-shopping"></i>
              <span>${product.isOutOfStock ? 'Unavailable' : 'Add'}</span>
            </button>
          </div>
        </div>
      `;
    }).join('');
  }

  addToCartById(id) {
    const prod = this.products.find(p => p.id === id);
    if (prod) {
      this.addToCart(prod, 1);
      openCartDrawer();
    }
  }

  // Customer Checkout & Order Creation
  placeOrder(customerData, paymentMethod, isPaidOnline, upiRef = '') {
    if (this.cart.length === 0) return null;

    const subtotal = this.cart.reduce((sum, i) => sum + (i.price * i.quantity), 0);
    let discount = 0;
    if (this.appliedCoupon === 'HONEY10') discount = subtotal * 0.10;
    if (this.appliedCoupon === 'CUTE15') discount = subtotal * 0.15;
    if (this.appliedCoupon === 'DREAMY20') discount = subtotal * 0.20;

    const shipping = subtotal > 999 ? 0 : 99;
    const total = Math.max(0, subtotal - discount + shipping);

    const orderId = 'HS-' + Math.floor(10000 + Math.random() * 90000);
    const orderItems = this.cart.map(c => ({
      productTitle: c.title,
      price: c.price,
      quantity: c.quantity
    }));

    const now = new Date();
    const formattedDate = now.toLocaleDateString('en-IN', { day: 'numeric', month: 'short', year: 'numeric' }) + ' · ' + now.toLocaleTimeString('en-IN', { hour: '2-digit', minute: '2-digit' });

    const newOrder = {
      id: orderId,
      customerName: customerData.name,
      phone: customerData.phone,
      address: `${customerData.address}, ${customerData.city}, ${customerData.state} - ${customerData.pincode}`,
      items: orderItems,
      subtotal,
      discount,
      shipping,
      total,
      status: 'PENDING',
      placedAt: formattedDate,
      paymentMethod,
      payeeUpi: this.storeUpiId,
      isPaidOnline,
      upiRef: upiRef || (isPaidOnline ? 'UPI-VERIFIED-' + Date.now().toString().slice(-6) : '')
    };

    // Update inventory
    this.cart.forEach(item => {
      const prod = this.products.find(p => p.id === item.productId);
      if (prod) {
        prod.stockQuantity = Math.max(0, prod.stockQuantity - item.quantity);
        prod.isOutOfStock = prod.stockQuantity === 0;
        prod.salesCount += item.quantity;
      }
    });

    // Add to orders
    this.orders.unshift(newOrder);

    // Create Admin Notification
    const notifMsg = isPaidOnline
      ? `${customerData.name} placed order #${orderId} for ${this.formatInr(total)} via PhonePe/UPI (${this.storeUpiId}) ✨`
      : `${customerData.name} placed order #${orderId} for ${this.formatInr(total)} via Cash on Delivery 📦`;

    this.notifications.unshift({
      id: 'notif-' + Date.now(),
      title: isPaidOnline ? `New Online Order #${orderId} 💳` : `New COD Order #${orderId} 📦`,
      message: notifMsg,
      time: 'Just now',
      isRead: false
    });

    this.clearCart();
    this.persistAll();
    this.renderProducts();
    this.updateNavBadges();
    return newOrder;
  }

  // Admin Actions
  updateProductPrice(productId, newPrice) {
    const prod = this.products.find(p => p.id === productId);
    if (prod && newPrice > 0) {
      prod.price = Number(newPrice);
      this.persistAll();
      this.renderProducts();
      this.renderAdminProducts();
      this.renderAdminAnalytics();
    }
  }

  toggleProductStock(productId) {
    const prod = this.products.find(p => p.id === productId);
    if (prod) {
      prod.isOutOfStock = !prod.isOutOfStock;
      if (prod.isOutOfStock) {
        prod.stockQuantity = 0;
      } else if (prod.stockQuantity === 0) {
        prod.stockQuantity = 5;
      }
      this.persistAll();
      this.renderProducts();
      this.renderAdminProducts();
    }
  }

  deleteProduct(productId) {
    if (confirm('Are you sure you want to delete this handcrafted item?')) {
      this.products = this.products.filter(p => p.id !== productId);
      this.persistAll();
      this.renderProducts();
      this.renderAdminProducts();
      this.renderAdminAnalytics();
    }
  }

  addNewProduct(prodData) {
    const newProd = {
      id: 'prod-' + (this.products.length + 1) + '-' + Math.floor(Math.random() * 100),
      title: prodData.title,
      category: prodData.category,
      price: Number(prodData.price),
      originalPrice: prodData.originalPrice ? Number(prodData.originalPrice) : null,
      rating: 5.0,
      reviewCount: 0,
      isOutOfStock: false,
      stockQuantity: Number(prodData.stockQuantity || 10),
      description: prodData.description || 'Handcrafted crochet creation.',
      materials: prodData.materials || 'Soft Yarn, Eco Stuffing',
      estimatedCraftTime: prodData.estimatedCraftTime || 'Handcrafted in 2-3 days',
      badge: prodData.badge || 'New Arrival ✨',
      colorHex: prodData.colorHex || '#F59E0B',
      iconType: prodData.iconType || '🧶',
      salesCount: 0
    };

    this.products.unshift(newProd);
    this.persistAll();
    this.renderProducts();
    this.renderAdminProducts();
    this.renderAdminAnalytics();
  }

  updateOrderStatus(orderId, newStatus) {
    const order = this.orders.find(o => o.id === orderId);
    if (order) {
      order.status = newStatus;
      this.persistAll();
      this.renderAdminOrders();
      this.renderOrdersTracking();
    }
  }

  // Admin team
  addAdmin(name, email, password, role) {
    const cleanEmail = email.trim().toLowerCase();
    if (this.admins.some(a => a.email.toLowerCase() === cleanEmail)) {
      alert('An admin with this email already exists!');
      return false;
    }
    const newAdmin = {
      id: 'admin-' + Date.now(),
      name: name.trim(),
      email: cleanEmail,
      password: password.trim(),
      role: role || 'Workshop Assistant',
      isPrimary: false
    };
    this.admins.push(newAdmin);
    this.persistAll();
    this.renderAdminTeam();
    return true;
  }

  removeAdmin(adminId) {
    const target = this.admins.find(a => a.id === adminId);
    if (!target) return;
    if (target.isPrimary || target.email === 'utkarshrainavilla8@gmail.com') {
      alert('Cannot remove the Primary Super Admin!');
      return;
    }
    if (confirm(`Remove ${target.name} from admin team?`)) {
      this.admins = this.admins.filter(a => a.id !== adminId);
      this.persistAll();
      this.renderAdminTeam();
    }
  }

  // Admin login check
  loginAdmin(email, password) {
    const cleanEmail = email.trim().toLowerCase();
    const cleanPass = password.trim();
    const admin = this.admins.find(a => a.email.toLowerCase() === cleanEmail && a.password === cleanPass);
    if (admin) {
      this.currentAdmin = admin;
      this.save('hs_session_admin', admin);
      return true;
    }
    return false;
  }

  logoutAdmin() {
    this.currentAdmin = null;
    this.save('hs_session_admin', null);
  }

  // Reviews
  addReview(productId, author, rating, title, comment) {
    const newRev = {
      id: 'rev-' + Date.now(),
      productId,
      author: author.trim() || 'Crochet Lover',
      rating: Number(rating),
      date: new Date().toLocaleDateString('en-IN', { day: 'numeric', month: 'short', year: 'numeric' }),
      title: title.trim() || 'Wonderful handmade craft!',
      comment: comment.trim(),
      verifiedPurchase: true
    };
    this.reviews.unshift(newRev);

    // update product rating
    const prodReviews = this.reviews.filter(r => r.productId === productId);
    const avg = prodReviews.reduce((sum, r) => sum + r.rating, 0) / prodReviews.length;
    const prod = this.products.find(p => p.id === productId);
    if (prod) {
      prod.rating = Number(avg.toFixed(1));
      prod.reviewCount = prodReviews.length;
    }

    this.persistAll();
    this.renderProducts();
    return newRev;
  }
}

// Global Store Instance
const store = new Store();

// DOM Initializer
document.addEventListener('DOMContentLoaded', () => {
  store.renderProducts();
  store.updateCartUI();
  setupEventListeners();
  
  // Set store UPI in payment views
  const storeUpiDisplays = document.querySelectorAll('.store-upi-display');
  storeUpiDisplays.forEach(el => el.innerText = store.storeUpiId);
});

// Setup All UI Event Listeners
function setupEventListeners() {
  // Category Navigation Pills
  const catPills = document.querySelectorAll('.cat-pill');
  catPills.forEach(pill => {
    pill.addEventListener('click', () => {
      catPills.forEach(p => p.classList.remove('bg-amber-500', 'text-white'));
      catPills.forEach(p => p.classList.add('bg-white', 'text-stone-700'));
      pill.classList.remove('bg-white', 'text-stone-700');
      pill.classList.add('bg-amber-500', 'text-white');
      const cat = pill.getAttribute('data-cat');
      const searchVal = document.getElementById('search-input')?.value || '';
      store.renderProducts(cat, searchVal);
    });
  });

  // Search Input
  const searchInput = document.getElementById('search-input');
  if (searchInput) {
    searchInput.addEventListener('input', (e) => {
      const activePill = document.querySelector('.cat-pill.bg-amber-500');
      const cat = activePill ? activePill.getAttribute('data-cat') : 'ALL';
      store.renderProducts(cat, e.target.value);
    });
  }

  // Cart Drawer
  document.getElementById('nav-cart-btn')?.addEventListener('click', openCartDrawer);
  document.getElementById('close-cart-btn')?.addEventListener('click', closeCartDrawer);
  document.getElementById('cart-drawer-overlay')?.addEventListener('click', closeCartDrawer);

  // Checkout Button in Cart
  document.getElementById('cart-checkout-btn')?.addEventListener('click', () => {
    closeCartDrawer();
    openCheckoutModal();
  });

  // Admin Portal Button in Nav
  document.getElementById('nav-admin-btn')?.addEventListener('click', () => {
    if (store.currentAdmin) {
      openAdminDashboard();
    } else {
      openAdminLoginModal();
    }
  });

  // Order Tracking Nav Button
  document.getElementById('nav-orders-btn')?.addEventListener('click', openOrderTrackingModal);
}

// --- Cart Drawer Controls ---
function openCartDrawer() {
  const drawer = document.getElementById('cart-drawer');
  const overlay = document.getElementById('cart-drawer-overlay');
  if (drawer && overlay) {
    drawer.classList.remove('translate-x-full');
    overlay.classList.remove('hidden');
    store.updateCartUI();
  }
}

function closeCartDrawer() {
  const drawer = document.getElementById('cart-drawer');
  const overlay = document.getElementById('cart-drawer-overlay');
  if (drawer && overlay) {
    drawer.classList.add('translate-x-full');
    overlay.classList.add('hidden');
  }
}

// --- Product Detail & Reviews Modal ---
let currentViewingProductId = null;

function openProductModal(productId) {
  const product = store.products.find(p => p.id === productId);
  if (!product) return;
  currentViewingProductId = productId;

  const modal = document.getElementById('product-detail-modal');
  const title = document.getElementById('modal-prod-title');
  const cat = document.getElementById('modal-prod-cat');
  const price = document.getElementById('modal-prod-price');
  const origPrice = document.getElementById('modal-prod-orig-price');
  const desc = document.getElementById('modal-prod-desc');
  const materials = document.getElementById('modal-prod-materials');
  const craftTime = document.getElementById('modal-prod-craft-time');
  const badge = document.getElementById('modal-prod-badge');
  const rating = document.getElementById('modal-prod-rating');
  const reviewsCount = document.getElementById('modal-prod-reviews-count');
  const visualIcon = document.getElementById('modal-prod-icon');
  const visualBg = document.getElementById('modal-prod-visual-bg');
  const stockText = document.getElementById('modal-prod-stock');
  const addBtn = document.getElementById('modal-add-cart-btn');

  title.innerText = product.title;
  cat.innerText = product.category;
  price.innerText = store.formatInr(product.price);
  if (product.originalPrice) {
    origPrice.innerText = store.formatInr(product.originalPrice);
    origPrice.classList.remove('hidden');
  } else {
    origPrice.classList.add('hidden');
  }
  desc.innerText = product.description;
  materials.innerText = product.materials;
  craftTime.innerText = product.estimatedCraftTime;
  badge.innerText = product.badge;
  rating.innerText = product.rating;
  reviewsCount.innerText = `(${product.reviewCount} customer reviews)`;
  visualIcon.innerText = product.iconType;
  visualBg.style.background = `linear-gradient(135deg, ${product.colorHex}25, ${product.colorHex}50)`;

  if (product.isOutOfStock) {
    stockText.innerText = 'Currently Out of Stock';
    stockText.className = 'text-xs font-bold text-red-500';
    addBtn.disabled = true;
    addBtn.innerText = 'Out of Stock';
    addBtn.className = 'w-full py-3.5 rounded-2xl bg-stone-200 text-stone-400 font-bold cursor-not-allowed';
  } else {
    stockText.innerText = `In Stock (${product.stockQuantity} handcrafted available)`;
    stockText.className = 'text-xs font-bold text-emerald-600';
    addBtn.disabled = false;
    addBtn.innerHTML = `<i class="fa-solid fa-bag-shopping mr-2"></i> Add to Cart · ${store.formatInr(product.price)}`;
    addBtn.className = 'w-full py-3.5 rounded-2xl bg-amber-500 hover:bg-amber-600 active:scale-98 text-white font-bold shadow-md';
  }

  renderProductReviews(productId);
  modal.classList.remove('hidden');
}

function closeProductModal() {
  document.getElementById('product-detail-modal')?.classList.add('hidden');
  currentViewingProductId = null;
}

function renderProductReviews(productId) {
  const container = document.getElementById('product-reviews-list');
  if (!container) return;

  const reviews = store.reviews.filter(r => r.productId === productId);
  if (reviews.length === 0) {
    container.innerHTML = `
      <div class="text-center py-6 text-stone-400 text-xs">
        No customer reviews yet. Be the first to review this artisanal piece! 🐝
      </div>
    `;
    return;
  }

  container.innerHTML = reviews.map(r => `
    <div class="p-3 bg-stone-50 rounded-xl border border-stone-100">
      <div class="flex items-center justify-between text-xs">
        <div class="flex items-center gap-1.5">
          <span class="font-bold text-stone-800">${r.author}</span>
          ${r.verifiedPurchase ? '<span class="text-[10px] bg-emerald-100 text-emerald-700 px-1.5 py-0.2 rounded font-semibold">Verified Buyer</span>' : ''}
        </div>
        <span class="text-stone-400 text-[11px]">${r.date}</span>
      </div>
      <div class="flex text-amber-400 text-xs my-1">
        ${Array.from({ length: r.rating }).map(() => '<i class="fa-solid fa-star"></i>').join('')}
      </div>
      <h5 class="font-bold text-stone-800 text-xs">${r.title}</h5>
      <p class="text-stone-600 text-xs mt-1 leading-relaxed">${r.comment}</p>
    </div>
  `).join('');
}

function handleAddReviewSubmit(e) {
  e.preventDefault();
  if (!currentViewingProductId) return;

  const author = document.getElementById('review-author-input').value;
  const rating = document.getElementById('review-rating-select').value;
  const title = document.getElementById('review-title-input').value;
  const comment = document.getElementById('review-comment-input').value;

  store.addReview(currentViewingProductId, author, rating, title, comment);
  renderProductReviews(currentViewingProductId);
  
  // Reset form
  document.getElementById('write-review-form').reset();
  alert('✨ Thank you! Your review has been added.');
}

// Add to cart from modal
function addCurrentModalProductToCart() {
  if (!currentViewingProductId) return;
  const prod = store.products.find(p => p.id === currentViewingProductId);
  if (prod) {
    store.addToCart(prod, 1);
    closeProductModal();
    openCartDrawer();
  }
}

// --- Checkout Modal & PhonePe/UPI Flow ---
let selectedPaymentMethod = 'UPI'; // 'UPI' or 'COD'

function openCheckoutModal() {
  if (store.cart.length === 0) {
    alert('Your cart is empty!');
    return;
  }

  const modal = document.getElementById('checkout-modal');
  const subtotal = store.cart.reduce((sum, i) => sum + (i.price * i.quantity), 0);
  let discount = 0;
  if (store.appliedCoupon === 'HONEY10') discount = subtotal * 0.10;
  if (store.appliedCoupon === 'CUTE15') discount = subtotal * 0.15;
  if (store.appliedCoupon === 'DREAMY20') discount = subtotal * 0.20;
  const shipping = subtotal > 999 ? 0 : 99;
  const total = Math.max(0, subtotal - discount + shipping);

  document.getElementById('checkout-summary-subtotal').innerText = store.formatInr(subtotal);
  document.getElementById('checkout-summary-discount').innerText = discount > 0 ? `-${store.formatInr(discount)}` : '₹0';
  document.getElementById('checkout-summary-shipping').innerText = shipping === 0 ? 'FREE' : store.formatInr(shipping);
  document.getElementById('checkout-summary-total').innerText = store.formatInr(total);
  document.getElementById('checkout-pay-amount-display').innerText = store.formatInr(total);

  // Generate PhonePe / UPI QR Code URL
  const upiId = store.storeUpiId;
  const payeeName = encodeURIComponent('Honeycomb Stitches');
  const upiUrl = `upi://pay?pa=${upiId}&pn=${payeeName}&am=${total}&cu=INR`;
  const qrImgUrl = `https://api.qrserver.com/v1/create-qr-code/?size=180x180&data=${encodeURIComponent(upiUrl)}`;

  const qrImg = document.getElementById('upi-qr-image');
  if (qrImg) qrImg.src = qrImgUrl;

  const upiDeepLink = document.getElementById('upi-deeplink-btn');
  if (upiDeepLink) upiDeepLink.href = upiUrl;

  // Reset payment selection to UPI
  selectPaymentMethod('UPI');

  modal.classList.remove('hidden');
}

function closeCheckoutModal() {
  document.getElementById('checkout-modal')?.classList.add('hidden');
}

function selectPaymentMethod(method) {
  selectedPaymentMethod = method;
  const upiCard = document.getElementById('payment-method-upi-card');
  const codCard = document.getElementById('payment-method-cod-card');
  const upiDetails = document.getElementById('upi-details-section');
  const codDetails = document.getElementById('cod-details-section');

  if (method === 'UPI') {
    upiCard.classList.add('border-amber-500', 'bg-amber-50/50');
    upiCard.classList.remove('border-stone-200');
    codCard.classList.remove('border-amber-500', 'bg-amber-50/50');
    codCard.classList.add('border-stone-200');
    upiDetails.classList.remove('hidden');
    codDetails.classList.add('hidden');
  } else {
    codCard.classList.add('border-amber-500', 'bg-amber-50/50');
    codCard.classList.remove('border-stone-200');
    upiCard.classList.remove('border-amber-500', 'bg-amber-50/50');
    upiCard.classList.add('border-stone-200');
    codDetails.classList.remove('hidden');
    upiDetails.classList.add('hidden');
  }
}

function handleCheckoutSubmit(e) {
  e.preventDefault();

  const name = document.getElementById('checkout-name').value.trim();
  const phone = document.getElementById('checkout-phone').value.trim();
  const address = document.getElementById('checkout-address').value.trim();
  const city = document.getElementById('checkout-city').value.trim();
  const state = document.getElementById('checkout-state').value.trim();
  const pincode = document.getElementById('checkout-pincode').value.trim();

  if (!name || !phone || !address || !city || !pincode) {
    alert('Please fill in all shipping details!');
    return;
  }

  let isPaidOnline = selectedPaymentMethod === 'UPI';
  let upiRef = '';

  if (isPaidOnline) {
    upiRef = document.getElementById('checkout-upi-ref').value.trim();
    if (!upiRef) {
      // Prompt customer if they didn't enter reference
      const confirmProceed = confirm('You selected Online Payment. If you already transferred via PhonePe/UPI, tap OK to complete. (Tip: enter your 12-digit UTR reference for instant verification).');
      if (!confirmProceed) return;
    }
  }

  const paymentLabel = isPaidOnline 
    ? `PhonePe / FamPay UPI (${store.storeUpiId})`
    : 'Cash on Delivery (COD)';

  const customerData = { name, phone, address, city, state, pincode };
  const newOrder = store.placeOrder(customerData, paymentLabel, isPaidOnline, upiRef);

  closeCheckoutModal();
  openOrderSuccessModal(newOrder);
}

// Order Success & Real-Time Tracking
function openOrderSuccessModal(order) {
  const modal = document.getElementById('order-success-modal');
  document.getElementById('success-order-id').innerText = order.id;
  document.getElementById('success-order-total').innerText = store.formatInr(order.total);
  document.getElementById('success-order-payment').innerText = order.paymentMethod;
  document.getElementById('success-order-address').innerText = order.address;

  modal.classList.remove('hidden');
}

function closeOrderSuccessModal() {
  document.getElementById('order-success-modal')?.classList.add('hidden');
}

// --- Order Tracking Screen ---
function openOrderTrackingModal() {
  const modal = document.getElementById('order-tracking-modal');
  store.renderOrdersTracking();
  modal.classList.remove('hidden');
}

function closeOrderTrackingModal() {
  document.getElementById('order-tracking-modal')?.classList.add('hidden');
}

Store.prototype.renderOrdersTracking = function() {
  const container = document.getElementById('orders-tracking-list');
  if (!container) return;

  if (this.orders.length === 0) {
    container.innerHTML = `
      <div class="text-center py-12 text-stone-500">
        <span class="text-4xl">📦</span>
        <p class="font-bold mt-2">No orders placed yet</p>
        <p class="text-xs text-stone-400">Discover our handcrafted crochet items and place your first order!</p>
      </div>
    `;
    return;
  }

  const statusSteps = ['PENDING', 'CRAFTING', 'PACKED', 'DISPATCHED', 'DELIVERED'];

  container.innerHTML = this.orders.map(order => {
    const currentStepIdx = statusSteps.indexOf(order.status);
    return `
      <div class="p-5 bg-white rounded-2xl border border-amber-100 honey-shadow-sm mb-4">
        <div class="flex flex-wrap items-center justify-between border-b border-amber-50 pb-3 gap-2">
          <div>
            <span class="font-bold text-base text-stone-800">Order #${order.id}</span>
            <p class="text-xs text-stone-400">${order.placedAt}</p>
          </div>
          <div class="text-right">
            <span class="font-bold text-amber-700 text-base">${this.formatInr(order.total)}</span>
            <span class="text-xs block text-stone-500">${order.paymentMethod}</span>
          </div>
        </div>

        <!-- Tracking Timeline -->
        <div class="my-5">
          <div class="flex items-center justify-between relative">
            <div class="absolute left-0 right-0 top-1/2 -translate-y-1/2 h-1 bg-stone-100 z-0"></div>
            <div class="absolute left-0 top-1/2 -translate-y-1/2 h-1 bg-amber-500 z-0 transition-all duration-500"
              style="width: ${Math.max(0, (currentStepIdx / (statusSteps.length - 1)) * 100)}%;"></div>

            ${[
              { label: 'Placed', icon: '📝' },
              { label: 'Crafting', icon: '🧶' },
              { label: 'Packed', icon: '🎁' },
              { label: 'Dispatched', icon: '🚚' },
              { label: 'Delivered', icon: '🏡' }
            ].map((step, idx) => {
              const isDone = idx <= currentStepIdx;
              return `
                <div class="relative z-10 flex flex-col items-center">
                  <div class="w-8 h-8 rounded-full flex items-center justify-center text-xs font-bold transition-all ${
                    isDone ? 'bg-amber-500 text-white shadow-xs' : 'bg-stone-200 text-stone-500'
                  }">
                    ${isDone ? step.icon : idx + 1}
                  </div>
                  <span class="text-[10px] mt-1 font-semibold ${isDone ? 'text-amber-700' : 'text-stone-400'}">${step.label}</span>
                </div>
              `;
            }).join('')}
          </div>
        </div>

        <!-- Items Summary -->
        <div class="bg-amber-50/50 rounded-xl p-3 text-xs">
          <p class="font-bold text-stone-700 mb-1">Items:</p>
          <ul class="space-y-1 text-stone-600">
            ${order.items.map(it => `
              <li class="flex justify-between">
                <span>${it.quantity}x ${it.productTitle}</span>
                <span class="font-medium">${this.formatInr(it.price * it.quantity)}</span>
              </li>
            `).join('')}
          </ul>
          <p class="text-[11px] text-stone-400 mt-2 border-t border-amber-100/60 pt-1.5">
            📍 Deliver to: ${order.customerName} · ${order.address}
          </p>
        </div>
      </div>
    `;
  }).join('');
};

// --- Admin Portal & Management Dashboard ---
function openAdminLoginModal() {
  document.getElementById('admin-login-modal')?.classList.remove('hidden');
}

function closeAdminLoginModal() {
  document.getElementById('admin-login-modal')?.classList.add('hidden');
}

function handleAdminLogin(e) {
  e.preventDefault();
  const email = document.getElementById('admin-email-input').value;
  const pass = document.getElementById('admin-pass-input').value;

  if (store.loginAdmin(email, pass)) {
    closeAdminLoginModal();
    openAdminDashboard();
    store.updateNavBadges();
  } else {
    alert('❌ Invalid admin email or password. Please verify your credentials.');
  }
}

function openAdminDashboard() {
  const modal = document.getElementById('admin-dashboard-modal');
  if (!modal) return;

  document.getElementById('admin-logged-name').innerText = store.currentAdmin?.name || 'Administrator';
  document.getElementById('admin-logged-role').innerText = store.currentAdmin?.role || 'Super Admin';
  
  store.renderAdminAnalytics();
  store.renderAdminProducts();
  store.renderAdminOrders();
  store.renderAdminNotifications();
  store.renderAdminTeam();

  modal.classList.remove('hidden');
}

function closeAdminDashboard() {
  document.getElementById('admin-dashboard-modal')?.classList.add('hidden');
}

function handleAdminLogout() {
  store.logoutAdmin();
  closeAdminDashboard();
  store.updateNavBadges();
  alert('Logged out of Admin Portal.');
}

// Switch tabs inside Admin Portal
function switchAdminTab(tabName) {
  const tabs = ['analytics', 'products', 'orders', 'notifications', 'team', 'settings'];
  tabs.forEach(t => {
    const btn = document.getElementById(`admin-tab-btn-${t}`);
    const section = document.getElementById(`admin-section-${t}`);
    if (t === tabName) {
      btn?.classList.add('bg-amber-500', 'text-white');
      btn?.classList.remove('bg-white', 'text-stone-600');
      section?.classList.remove('hidden');
    } else {
      btn?.classList.remove('bg-amber-500', 'text-white');
      btn?.classList.add('bg-white', 'text-stone-600');
      section?.classList.add('hidden');
    }
  });
}

// Admin Renders
Store.prototype.renderAdminAnalytics = function() {
  const totalRevenue = this.orders.reduce((sum, o) => sum + o.total, 0);
  const totalOrders = this.orders.length;
  const aov = totalOrders > 0 ? totalRevenue / totalOrders : 0;
  const outOfStockCount = this.products.filter(p => p.isOutOfStock).length;

  document.getElementById('admin-stat-revenue').innerText = this.formatInr(totalRevenue);
  document.getElementById('admin-stat-orders').innerText = totalOrders;
  document.getElementById('admin-stat-aov').innerText = this.formatInr(aov);
  document.getElementById('admin-stat-stock').innerText = `${outOfStockCount} Out of Stock`;

  // Top Products List
  const topList = document.getElementById('admin-top-products-list');
  if (topList) {
    const sorted = [...this.products].sort((a, b) => b.salesCount - a.salesCount).slice(0, 4);
    topList.innerHTML = sorted.map((p, idx) => `
      <div class="flex items-center justify-between p-2.5 bg-stone-50 rounded-xl">
        <div class="flex items-center gap-2">
          <span class="w-6 h-6 rounded-full bg-amber-100 text-amber-800 text-xs font-bold flex items-center justify-center">#${idx + 1}</span>
          <div>
            <h5 class="text-xs font-bold text-stone-800">${p.title}</h5>
            <span class="text-[11px] text-stone-400">${p.category} · ${this.formatInr(p.price)}</span>
          </div>
        </div>
        <div class="text-right">
          <span class="text-xs font-bold text-amber-700">${p.salesCount} sold</span>
        </div>
      </div>
    `).join('');
  }
};

Store.prototype.renderAdminProducts = function() {
  const container = document.getElementById('admin-products-table-body');
  if (!container) return;

  container.innerHTML = this.products.map(p => `
    <tr class="border-b border-stone-100 hover:bg-stone-50/50">
      <td class="py-3 px-4">
        <div class="flex items-center gap-2.5">
          <span class="text-2xl">${p.iconType}</span>
          <div>
            <h5 class="font-bold text-xs text-stone-800">${p.title}</h5>
            <span class="text-[10px] text-stone-400">${p.category}</span>
          </div>
        </div>
      </td>
      <td class="py-3 px-4">
        <div class="flex items-center gap-1">
          <span class="text-xs text-stone-500 font-bold">₹</span>
          <input type="number" value="${p.price}" 
            onchange="store.updateProductPrice('${p.id}', this.value)"
            class="w-20 px-2 py-1 bg-stone-50 border border-stone-200 rounded text-xs font-bold text-stone-800 focus:bg-white focus:border-amber-500 outline-none" />
        </div>
      </td>
      <td class="py-3 px-4">
        <button onclick="store.toggleProductStock('${p.id}')"
          class="px-2.5 py-1 rounded-full text-[11px] font-bold transition-all ${
            p.isOutOfStock 
              ? 'bg-red-100 text-red-700 hover:bg-red-200' 
              : 'bg-emerald-100 text-emerald-700 hover:bg-emerald-200'
          }">
          ${p.isOutOfStock ? '❌ Out of Stock' : '✅ In Stock (' + p.stockQuantity + ')'}
        </button>
      </td>
      <td class="py-3 px-4 text-xs font-medium text-stone-600">
        ${p.salesCount} units
      </td>
      <td class="py-3 px-4 text-right">
        <button onclick="store.deleteProduct('${p.id}')" class="text-stone-400 hover:text-red-500 text-xs px-2 py-1">
          <i class="fa-solid fa-trash"></i>
        </button>
      </td>
    </tr>
  `).join('');
};

Store.prototype.renderAdminOrders = function() {
  const container = document.getElementById('admin-orders-table-body');
  if (!container) return;

  container.innerHTML = this.orders.map(order => `
    <tr class="border-b border-stone-100 hover:bg-stone-50/50">
      <td class="py-3 px-4">
        <span class="font-bold text-xs text-stone-800">#${order.id}</span>
        <p class="text-[10px] text-stone-400">${order.placedAt}</p>
      </td>
      <td class="py-3 px-4">
        <h5 class="font-bold text-xs text-stone-800">${order.customerName}</h5>
        <span class="text-[10px] text-stone-400">${order.phone}</span>
      </td>
      <td class="py-3 px-4">
        <span class="font-bold text-xs text-amber-800">${this.formatInr(order.total)}</span>
        <span class="text-[10px] block text-stone-400">${order.paymentMethod}</span>
      </td>
      <td class="py-3 px-4">
        <select onchange="store.updateOrderStatus('${order.id}', this.value)"
          class="px-2 py-1 text-xs rounded-lg font-bold border border-amber-200 bg-amber-50/50 text-stone-700 outline-none">
          <option value="PENDING" ${order.status === 'PENDING' ? 'selected' : ''}>📝 Order Placed</option>
          <option value="CRAFTING" ${order.status === 'CRAFTING' ? 'selected' : ''}>🧶 Handcrafting</option>
          <option value="PACKED" ${order.status === 'PACKED' ? 'selected' : ''}>🎁 Quality Packed</option>
          <option value="DISPATCHED" ${order.status === 'DISPATCHED' ? 'selected' : ''}>🚚 Dispatched</option>
          <option value="DELIVERED" ${order.status === 'DELIVERED' ? 'selected' : ''}>🏡 Delivered</option>
          <option value="CANCELLED" ${order.status === 'CANCELLED' ? 'selected' : ''}>❌ Cancelled</option>
        </select>
      </td>
    </tr>
  `).join('');
};

Store.prototype.renderAdminNotifications = function() {
  const container = document.getElementById('admin-notifications-list');
  if (!container) return;

  if (this.notifications.length === 0) {
    container.innerHTML = '<p class="text-xs text-stone-400 p-4">No notifications yet.</p>';
    return;
  }

  container.innerHTML = this.notifications.map(n => `
    <div class="p-3 bg-white rounded-xl border border-stone-200 flex items-start justify-between gap-3 ${n.isRead ? 'opacity-70' : 'border-amber-300 bg-amber-50/20'}">
      <div>
        <h5 class="text-xs font-bold text-stone-800">${n.title}</h5>
        <p class="text-xs text-stone-600 mt-0.5">${n.message}</p>
        <span class="text-[10px] text-stone-400 mt-1 block">${n.time}</span>
      </div>
      ${!n.isRead ? `
        <button onclick="markNotificationRead('${n.id}')" class="text-[10px] text-amber-700 hover:underline font-bold whitespace-nowrap">
          Mark Read
        </button>
      ` : ''}
    </div>
  `).join('');
};

function markNotificationRead(notifId) {
  const notif = store.notifications.find(n => n.id === notifId);
  if (notif) {
    notif.isRead = true;
    store.persistAll();
    store.renderAdminNotifications();
    store.updateNavBadges();
  }
}

Store.prototype.renderAdminTeam = function() {
  const container = document.getElementById('admin-team-list');
  if (!container) return;

  container.innerHTML = this.admins.map(admin => `
    <div class="p-3 bg-white rounded-xl border border-stone-200 flex items-center justify-between">
      <div class="flex items-center gap-3">
        <div class="w-9 h-9 rounded-full bg-amber-100 text-amber-800 font-bold flex items-center justify-center text-sm">
          ${admin.name.charAt(0)}
        </div>
        <div>
          <h5 class="text-xs font-bold text-stone-800 flex items-center gap-1.5">
            ${admin.name}
            ${admin.isPrimary ? '<span class="text-[9px] bg-amber-100 text-amber-800 px-1.5 py-0.2 rounded font-bold">Primary</span>' : ''}
          </h5>
          <span class="text-[11px] text-stone-400">${admin.email} · ${admin.role}</span>
        </div>
      </div>
      <div>
        ${!admin.isPrimary ? `
          <button onclick="store.removeAdmin('${admin.id}')" class="text-xs text-stone-400 hover:text-red-500 p-1">
            <i class="fa-solid fa-user-minus"></i>
          </button>
        ` : '<span class="text-[11px] text-stone-400 italic">Protected</span>'}
      </div>
    </div>
  `).join('');
};

function handleAddAdminSubmit(e) {
  e.preventDefault();
  const name = document.getElementById('new-admin-name').value;
  const email = document.getElementById('new-admin-email').value;
  const pass = document.getElementById('new-admin-pass').value;
  const role = document.getElementById('new-admin-role').value;

  if (store.addAdmin(name, email, pass, role)) {
    document.getElementById('add-admin-form').reset();
    alert(`🎉 Successfully added ${name} to the Honeycomb Workshop Admin team!`);
  }
}

// Add New Product modal controls
function openAddProductModal() {
  document.getElementById('add-product-modal')?.classList.remove('hidden');
}

function closeAddProductModal() {
  document.getElementById('add-product-modal')?.classList.add('hidden');
}

function handleAddProductSubmit(e) {
  e.preventDefault();
  const title = document.getElementById('new-prod-title').value;
  const category = document.getElementById('new-prod-category').value;
  const price = document.getElementById('new-prod-price').value;
  const originalPrice = document.getElementById('new-prod-orig-price').value;
  const stockQuantity = document.getElementById('new-prod-stock').value;
  const description = document.getElementById('new-prod-desc').value;
  const materials = document.getElementById('new-prod-materials').value;
  const craftTime = document.getElementById('new-prod-craft').value;
  const iconType = document.getElementById('new-prod-icon').value;
  const badge = document.getElementById('new-prod-badge').value;

  store.addNewProduct({
    title, category, price, originalPrice, stockQuantity, description, materials, estimatedCraftTime: craftTime, iconType, badge
  });

  closeAddProductModal();
  document.getElementById('add-product-form').reset();
  alert('✨ Handcrafted product added to catalog successfully!');
}

function handleUpdateUpiSettings(e) {
  e.preventDefault();
  const newUpi = document.getElementById('settings-upi-id').value.trim();
  if (newUpi) {
    store.storeUpiId = newUpi;
    store.save('hs_upi_id', newUpi);
    document.querySelectorAll('.store-upi-display').forEach(el => el.innerText = newUpi);
    alert(`✅ Store UPI ID updated to ${newUpi}. Customer payments will be credited to this ID!`);
  }
}
