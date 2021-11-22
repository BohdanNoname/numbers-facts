package com.nedashkivskyi.numbers_fact.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.nedashkivskyi.numbers_fact.data.model.local.NumbersDataEntity
import com.nedashkivskyi.numbers_fact.ui.screen.data_screen.DataScreen
import com.nedashkivskyi.numbers_fact.ui.screen.main_screen.MainScreen
import com.nedashkivskyi.numbers_fact.ui.screen.main_screen.view_model.MainScreenViewModel
import com.nedashkivskyi.numbers_fact.ui.theme.NumbersfactTheme
import com.nedashkivskyi.numbers_fact.utils.NavigationDestination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainScreenViewModel: MainScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NumbersfactTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AppNavigation(mainScreenViewModel = mainScreenViewModel)
                }
            }
        }
    }

    @Composable
    fun AppNavigation(mainScreenViewModel: MainScreenViewModel) {
        val navController  = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = NavigationDestination.MainScreen.destination
        ){
            composable(NavigationDestination.MainScreen.destination){
                MainScreen(navController = navController, viewModel = mainScreenViewModel)
            }
            composable(
                route = "${NavigationDestination.DataScreen.destination}/{number}",
                arguments = listOf(
                    navArgument("number") { type = NavType.StringType }
                )
                ){ backStackEntry ->
                backStackEntry?.arguments?.getString("number")?.let { json ->
                    val number = Gson().fromJson(json, NumbersDataEntity::class.java)
                    DataScreen(number = number)
                }
            }
        }
    }
}