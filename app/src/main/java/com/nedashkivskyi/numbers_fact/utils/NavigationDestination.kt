package com.nedashkivskyi.numbers_fact.utils

sealed class NavigationDestination(val destination: String) {
    object MainScreen: NavigationDestination(Constants.MainScreenDestination)
    object DataScreen: NavigationDestination(Constants.DataScreenDestination)
}