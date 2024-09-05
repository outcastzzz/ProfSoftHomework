package com.example.togetherApp.presentation.feature.main.model

import com.example.togetherApp.domain.entity.get.Course
import com.example.togetherApp.domain.entity.get.Note

sealed class MainEvent {
    data object OpenAllCoursesClicked: MainEvent()
    data object OpenUserNotesClicked: MainEvent()
    data object OpenCommunityNotesClicked: MainEvent()
    class ClickNote(val note: Note): MainEvent()
    class ClickCourse(val course: Course): MainEvent()
    class SearchInputChanged(val newValue: String): MainEvent()
}