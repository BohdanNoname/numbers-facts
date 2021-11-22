package com.nedashkivskyi.numbers_fact.ui.screen.main_screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nedashkivskyi.numbers_fact.data.model.local.NumbersDataEntity

@Composable
fun NumberCard(
    numberData: NumbersDataEntity,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(6.dp)
            .fillMaxSize()
            .clickable(onClick = onClick),
        elevation = 8.dp
    ) {
        numberData.number.let { number ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
            ) {
                Text(
                    text = "$number - ",
                    modifier = Modifier
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.h4
                )
                Text(
                    text = numberData.text,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .wrapContentWidth(Alignment.End),
                    maxLines = 1,
                    style = MaterialTheme.typography.h5)
            }
        }
    }
}