package com.shakbari.compose.ui.kit.bottomnavigation

import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.shakbari.compose.screen.destinations.Destination
import com.shakbari.compose.screen.destinations.DetailScreenDestination
import com.shakbari.compose.screen.destinations.HomeScreenDestination
import com.shakbari.compose.screen.navDestination

class DestinationBottomNavigation {

    private enum class BottomBarDestination(
        val direction: Destination,
        val icon: ImageVector,
        val title: String
    ) {
        HOME(HomeScreenDestination, Icons.Default.Home, "Home"),
        DETAILS(DetailScreenDestination, Icons.Default.Email, "Details"),
    }

    companion object {

        @Composable
        fun Show(
            navController: NavController
        ) {
            val currentDestination: Destination? = navController.currentBackStackEntryAsState()
                .value?.navDestination

            BottomNavigation(modifier = Modifier.background(Color.Red)){
                BottomBarDestination.values().forEach { destination ->
                    BottomNavigationItem(
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.White.copy(0.5f),
                        alwaysShowLabel = true,
                        selected = currentDestination == destination.direction,
                        onClick = {
                            navController.navigate(destination.direction.route) {
                                popUpTo(destination.direction.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                // restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                destination.icon,
                                contentDescription = destination.title
                            )
                        },
                        label = { Text(destination.title) },
                    )
                }
            }
        }

    }
}

