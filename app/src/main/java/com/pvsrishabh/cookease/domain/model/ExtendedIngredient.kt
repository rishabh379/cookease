package com.pvsrishabh.cookease.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExtendedIngredient(
    val name: String,
): Parcelable