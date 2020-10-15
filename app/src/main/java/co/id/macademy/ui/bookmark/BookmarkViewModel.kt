package co.id.macademy.ui.bookmark

import androidx.lifecycle.ViewModel
import co.id.macademy.data.CourseEntity
import co.id.macademy.utils.DataDummy

class BookmarkViewModel : ViewModel() {

    fun getBookmarks(): List<CourseEntity> = DataDummy.generateDummyCourses()
}