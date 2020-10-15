package co.id.macademy.ui.bookmark

import co.id.macademy.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}

