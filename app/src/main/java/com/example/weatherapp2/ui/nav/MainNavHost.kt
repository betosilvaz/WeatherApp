package com.example.weatherapp2.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp2.ui.HomePage
import com.example.weatherapp2.ui.ListPage
import com.example.weatherapp2.ui.MapPage
import com.example.weatherapp2.viewmodel.MainViewModel

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    NavHost(navController, startDestination = Route.Home) {
        composable<Route.Home> { HomePage(modifier = modifier, viewModel = viewModel) }
        composable<Route.List> { ListPage(modifier = modifier, viewModel = viewModel) }
        composable<Route.Map> { MapPage(modifier = modifier, viewModel = viewModel) }
    }
}