package co.id.macademy.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.id.macademy.data.source.local.entity.CourseEntity
import co.id.macademy.data.AcademyRepository

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getBookmarks(): LiveData<List<CourseEntity>> = academyRepository.getBookmarkedCourses()
}