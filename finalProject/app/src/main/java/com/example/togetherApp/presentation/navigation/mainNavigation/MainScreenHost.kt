package com.example.togetherApp.presentation.navigation.mainNavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose123.R
import com.example.togetherApp.presentation.feature.chat.ChatScreen
import com.example.togetherApp.presentation.feature.communityNotes.CNotesScreen
import com.example.togetherApp.presentation.feature.courses.CoursesScreen
import com.example.togetherApp.presentation.feature.main.MainScreen
import com.example.togetherApp.presentation.feature.profile.ProfileScreen
import com.example.togetherApp.presentation.navigation.AppScreens
import com.example.togetherApp.presentation.navigation.InnerScreens
import com.example.togetherApp.presentation.navigation.LocalNavHost
import com.example.togetherApp.presentation.ui.theme.TogetherTheme

@Composable
fun MainScreenHost(modifier: Modifier = Modifier) {

    val externalNavController = LocalNavHost.current
    val mainNavController = rememberNavController()
    val items = MainScreens.entries.toTypedArray()
    val bottomNavigationHeight = 56.dp

    Box(modifier = modifier.fillMaxSize()) {
        NavHost(
            mainNavController,
            modifier = modifier
                .padding(bottom = bottomNavigationHeight)
                .fillMaxHeight(),
            startDestination = MainScreens.Home.route
        ) {
            composable(MainScreens.Home.route) {
                MainScreen(mainNavController) {
                    externalNavController.navigate(AppScreens.Auth.title)
                }
            }
            composable(MainScreens.Notes.route) { }
            composable(MainScreens.Create.route) { Text("Hello, Create") }
            composable(MainScreens.Chat.route) { ChatScreen(mainNavController) }
            composable(MainScreens.Profile.route) {
                ProfileScreen(mainNavController) {
                    externalNavController.navigate(AppScreens.Auth.title)
                }
            }
            composable(InnerScreens.CommunityNotes.title) { CNotesScreen(mainNavController) }
            composable(InnerScreens.AllCourses.title) { CoursesScreen(mainNavController) }

        }
        BottomNavigation(
            modifier = modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .height(bottomNavigationHeight),
            backgroundColor = Color.White,
        ) {
            val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            items.forEachIndexed { index, screen ->
                val isSelected =
                    currentDestination?.hierarchy?.any { it.route == screen.route } == true

                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = when (screen) {
                                MainScreens.Home -> painterResource(R.drawable.ic_home)
                                MainScreens.Notes -> painterResource(R.drawable.ic_notes)
                                MainScreens.Create -> painterResource(R.drawable.ic_create)
                                MainScreens.Chat -> painterResource(R.drawable.ic_chat)
                                MainScreens.Profile -> painterResource(R.drawable.ic_profile)
                            },
                            contentDescription = screen.route,
                            tint = Color.Black
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        mainNavController.navigate(screen.route) {
                            popUpTo(mainNavController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier
                        .padding(
                            start = if (index == 0) 16.dp else 4.dp,
                            end = if (index == items.size - 1) 16.dp else 4.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        )
                        .clip(RoundedCornerShape(50.dp))
                        .background(
                            if (isSelected)
                                TogetherTheme.colors.dividerColor.copy(alpha = 0.4f)
                            else
                                Color.Transparent
                        )

                )
            }
        }
    }
}