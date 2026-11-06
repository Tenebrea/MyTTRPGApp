package com.example.myttrpgapp.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myttrpgapp.navigation.NavRoutes
import org.jetbrains.compose.resources.painterResource

@Composable
fun UiLayoutCompact(
    modifier: Modifier = Modifier,
    currentRoute: NavRoutes,
    changeRoute: (NavRoutes) -> Unit,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                NavRoutes.entries.forEach { route ->
                    NavigationBarItem(
                        selected = currentRoute == route,
                        onClick = { changeRoute(route) },
                        icon = {
                            Icon(
                                painter = painterResource(route.icon),
                                contentDescription = route.name
                            )
                        }
                    )
                }
            }
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.CAMPAIGNS,
            modifier = Modifier.padding(contentPadding)
        ) {
            composable(route = NavRoutes.CAMPAIGNS.toString()) {

            }

            composable(route = NavRoutes.CHARACTERS.toString()) {

            }
            composable(route = NavRoutes.PROFILE.toString()) {

            }
        }
    }
}