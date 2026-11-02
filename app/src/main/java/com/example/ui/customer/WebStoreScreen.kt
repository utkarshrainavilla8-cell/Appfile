package com.example.ui.customer

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.activity.compose.BackHandler
import com.example.ui.theme.DreamyCreamBackground
import com.example.ui.theme.HoneyGoldPrimary

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebStoreScreen(
  onBackToApp: () -> Unit
) {
  val context = LocalContext.current
  var webViewRef: WebView? = remember { null }

  BackHandler {
    if (webViewRef?.canGoBack() == true) {
      webViewRef?.goBack()
    } else {
      onBackToApp()
    }
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(DreamyCreamBackground)
  ) {
    // Top Bar with Web Details & Switch Back
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      shape = RoundedCornerShape(20.dp),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        IconButton(
          onClick = onBackToApp,
          modifier = Modifier.size(36.dp)
        ) {
          Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back to Native App",
            tint = Color(0xFF372A1C)
          )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Box(
          modifier = Modifier
            .size(36.dp)
            .background(Color(0xFFFEF3C7), RoundedCornerShape(10.dp)),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Language,
            contentDescription = null,
            tint = HoneyGoldPrimary,
            modifier = Modifier.size(20.dp)
          )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = "Converted Web Store",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF372A1C)
          )
          Text(
            text = "website/index.html · Responsive Website",
            fontSize = 11.sp,
            color = Color(0xFF92400E)
          )
        }

        IconButton(
          onClick = { webViewRef?.reload() },
          modifier = Modifier.size(36.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = "Reload Web View",
            tint = Color(0xFF786450)
          )
        }

        IconButton(
          onClick = {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
              type = "text/plain"
              putExtra(
                Intent.EXTRA_TEXT,
                "Honeycomb Stitches Web App converted and ready in /website folder (index.html, app.js, style.css). Deployable to Vercel, Netlify or GitHub Pages!"
              )
            }
            context.startActivity(Intent.createChooser(shareIntent, "Share Web App Info"))
          },
          modifier = Modifier.size(36.dp)
        ) {
          Icon(
            imageVector = Icons.Default.IosShare,
            contentDescription = "Share",
            tint = HoneyGoldPrimary
          )
        }
      }
    }

    // Embedded Web View rendering the converted website
    Box(
      modifier = Modifier
        .fillMaxSize()
        .weight(1f)
    ) {
      AndroidView(
        factory = { ctx ->
          WebView(ctx).apply {
            layoutParams = ViewGroup.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT,
              ViewGroup.LayoutParams.MATCH_PARENT
            )
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.databaseEnabled = true
            settings.allowFileAccess = true
            settings.allowContentAccess = true
            settings.useWideViewPort = true
            settings.loadWithOverviewMode = true

            webViewClient = object : WebViewClient() {
              override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
              ): Boolean {
                val url = request?.url?.toString() ?: return false
                if (url.startsWith("upi://") || url.startsWith("tel:") || url.startsWith("mailto:")) {
                  try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    ctx.startActivity(intent)
                    return true
                  } catch (_: Exception) {
                    return true
                  }
                }
                return false
              }
            }

            webChromeClient = WebChromeClient()
            loadUrl("file:///android_asset/website/index.html")
            webViewRef = this
          }
        },
        modifier = Modifier.fillMaxSize()
      )
    }
  }
}
