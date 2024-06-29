package com.pvsrishabh.cookease.presentation.onboarding

import androidx.annotation.DrawableRes
import com.pvsrishabh.cookease.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Get Started",
        description = "Discover thousands of recipes, curated to suit your taste and dietary preferences.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Easy Recipe Search",
        description = "Quickly find the perfect recipe with our advanced search filters, making meal planning a breeze.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Step-by-Step Instructions",
        description = "Follow easy, step-by-step cooking instructions to create delicious meals every time.",
        image = R.drawable.onboarding3
    )
)
