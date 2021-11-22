package com.nedashkivskyi.numbers_fact.ui.screen.data_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nedashkivskyi.numbers_fact.data.model.local.NumbersDataEntity

@Composable
fun DataScreen(number: NumbersDataEntity) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = number.number.toString(),
            style = MaterialTheme.typography.h2)

        Box(modifier = Modifier
            .align(Alignment.CenterHorizontally)) {
            Text(
                text = number.text,
                style = MaterialTheme.typography.h5,)
        }
    }
}