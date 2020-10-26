package co.id.macademy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.id.macademy.data.source.local.entity.CourseEntity
import co.id.macademy.data.source.local.entity.ModuleEntity
import co.id.macademy.data.AcademyRepository

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourses(): LiveData<CourseEntity> = academyRepository.getCourseWithModules(courseId)

    fun getModules(): LiveData<List<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId)
}