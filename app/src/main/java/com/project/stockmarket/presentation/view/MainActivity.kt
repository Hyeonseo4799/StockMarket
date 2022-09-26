package com.project.stockmarket.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.stockmarket.presentation.Screen
import com.project.stockmarket.presentation.ui.theme.StockMarketTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockMarketTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SearchScreen.route
                    ) {
                        composable(route = Screen.SearchScreen.route) {
                            SearchScreen(navController = navController)
                        }
                        composable(route = Screen.DetailScreen.route + "/{isinCode}" + "/{corpNumber}") {
                            DetailScreen(
                                isinCode = it.arguments!!.getString("isinCode")!!,
                                corpNumber = it.arguments!!.getString("corpNumber")!!,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}