package com.pvsrishabh.cookease.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.pvsrishabh.cookease.R

@Composable
fun CustomCard(modifier: Modifier = Modifier, text: String, textValue: String) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(5.dp),
        shape = CardDefaults.outlinedShape
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    id = R.color.body
                )
            )
            Text(
                text = textValue,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    id = R.color.orange
                )
            )
        }
    }
}