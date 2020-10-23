package co.id.macademy.ui.bookmark

import co.id.macademy.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}

