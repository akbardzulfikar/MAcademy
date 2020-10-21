package co.id.macademy.ui.bookmark

import androidx.lifecycle.ViewModel
import co.id.macademy.data.CourseEntity
import co.id.macademy.data.source.AcademyRepository
import co.id.macademy.utils.DataDummy

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getBookmarks(): List<CourseEntity> = academyRepository.getBookmarkedCourses()
}