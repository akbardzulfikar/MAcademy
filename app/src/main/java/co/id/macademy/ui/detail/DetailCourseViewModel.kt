package co.id.macademy.ui.detail

import androidx.lifecycle.ViewModel
import co.id.macademy.data.CourseEntity
import co.id.macademy.data.ModuleEntity
import co.id.macademy.data.source.AcademyRepository
import co.id.macademy.utils.DataDummy

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourses(): CourseEntity = academyRepository.getCourseWithModules(courseId)

    fun getModules(): List<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)
}