package co.id.macademy.ui.academy

import androidx.lifecycle.ViewModel
import co.id.macademy.data.CourseEntity
import co.id.macademy.data.source.AcademyRepository
import co.id.macademy.utils.DataDummy

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses(): List<CourseEntity> = academyRepository.getAllCourses()
}