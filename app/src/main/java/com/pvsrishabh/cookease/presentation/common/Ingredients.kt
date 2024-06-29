package com.pvsrishabh.cookease.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pvsrishabh.cookease.domain.model.ExtendedIngredient

fun LazyListScope.IngredientsList(ingredients: List<ExtendedIngredient>) {
    items(ingredients) { ingredient ->
        IngredientItem(ingredient)
    }
}

@Composable
fun IngredientItem(ingredient: ExtendedIngredient) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Implement ingredient item UI here
        Text(
            text = ingredient.name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f)
        )
    }
}