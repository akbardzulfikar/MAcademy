package co.id.macademy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.id.macademy.data.source.local.entity.CourseEntity
import co.id.macademy.data.AcademyRepository

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses(): LiveData<List<CourseEntity>> = academyRepository.getAllCourses()
}