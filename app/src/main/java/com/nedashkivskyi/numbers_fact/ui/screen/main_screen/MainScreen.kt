package com.nedashkivskyi.numbers_fact.ui.screen.main_screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.text.isDigitsOnly
import com.nedashkivskyi.numbers_fact.R
import com.nedashkivskyi.numbers_fact.data.model.local.NumbersDataEntity
import com.nedashkivskyi.numbers_fact.ui.screen.main_screen.component.NumberCard
import com.nedashkivskyi.numbers_fact.ui.screen.main_screen.view_model.MainScreenViewModel
import com.nedashkivskyi.numbers_fact.utils.EventState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.nedashkivskyi.numbers_fact.utils.NavigationDestination


@Composable
fun MainScreen(navController:NavHostController, viewModel: MainScreenViewModel){

    LaunchedEffect(true){
        viewModel.getAllNumbers()
    }

    Column {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            var number by remember { mutableStateOf("") }

            TextField(
                value = number,
                onValueChange = { number = it },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                label = { Text("Input your number") }
            )
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Button(
                    onClick = {
                        if(number.isDigitsOnly()){
                            viewModel.getNumberData(number = number)
                            number = ""
                        }
                    }) {
                    Text(text = "My Number")
                }

                Button(
                    onClick = {
                        viewModel.getRandomNumberData()
                    }) {
                    Text(text = "Random number")
                }
            }

            Row {
                when(val state = viewModel.state.value){
                    is EventState.Success ->
                        CreateLazyColumn(numbersList = state.data!!, navController = navController)
                    is EventState.Error -> {
                        viewModel.getAllNumbers()

                        val context = LocalContext.current
                        Toast
                            .makeText(context,
                                "Something was wrong,\nwe cann't load new numbers!",
                                Toast.LENGTH_LONG )
                            .show()
                    }
                    is EventState.Loading ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .width(300.dp)
                                    .height(300.dp))
                        }
                    is EventState.Empty ->
                        Column(
                            modifier = Modifier
                            .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.empty),
                                contentDescription = "Database is empty"
                            )
                        }
                }
            }
        }
    }
}

@Composable
fun CreateLazyColumn(navController: NavHostController, numbersList: List<NumbersDataEntity>) {
    LazyColumn{
        itemsIndexed(
            items = numbersList
        ){ index, number ->
            NumberCard(
                numberData = number,
                onClick = {
                    navigateToDataScreen(navController = navController, number = number)
                }
            )
        }
    }
}

fun navigateToDataScreen(navController: NavHostController, number: NumbersDataEntity){
    val number = Gson().toJson(number)
    navController.navigate(route = "${NavigationDestination.DataScreen.destination}/$number")
}