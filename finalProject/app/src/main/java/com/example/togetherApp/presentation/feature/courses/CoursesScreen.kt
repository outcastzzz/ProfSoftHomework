package com.example.togetherApp.presentation.feature.courses

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.togetherApp.presentation.feature.courses.model.CoursesAction
import com.example.togetherApp.presentation.navigation.InnerScreens

@Composable
fun CoursesScreen(navController: NavController) {

    val coursesViewModel: CoursesViewModel = hiltViewModel()

    val viewState by coursesViewModel.viewStates().collectAsStateWithLifecycle()
    val viewAction by coursesViewModel.viewActions().collectAsStateWithLifecycle(null)

    CoursesView(viewState = viewState) { event ->
        coursesViewModel.obtainEvent(event)
    }

    when(viewAction) {
        CoursesAction.ClickBack -> {
            navController.popBackStack()
            coursesViewModel.clearAction()
        }
        CoursesAction.ClickCourse -> {
            navController.navigate(InnerScreens.CourseDetails.title)
            coursesViewModel.clearAction()
        }
        null -> {}
    }

}