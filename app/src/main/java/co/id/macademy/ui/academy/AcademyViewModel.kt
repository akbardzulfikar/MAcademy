package co.id.macademy.ui.academy

import androidx.lifecycle.ViewModel
import co.id.macademy.data.CourseEntity
import co.id.macademy.utils.DataDummy

class AcademyViewModel : ViewModel() {

    fun getCourses(): List<CourseEntity> = DataDummy.generateDummyCourses()
}