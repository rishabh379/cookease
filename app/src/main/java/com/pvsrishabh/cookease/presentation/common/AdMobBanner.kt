package com.pvsrishabh.cookease.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AdMobBanner(adUnitId: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val adView = remember { AdView(context) }
    val adRequest = remember { AdRequest.Builder().build() }

    AndroidView(
        factory = {
            adView.apply {
                setAdSize(AdSize.BANNER)
                this.adUnitId = adUnitId
                loadAd(adRequest)
            }
        },
        modifier = modifier
    )
}