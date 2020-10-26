package co.id.macademy.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.id.macademy.data.source.local.entity.ModuleEntity
import co.id.macademy.data.AcademyRepository
import java.util.ArrayList

class CourseReaderViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    private lateinit var courseId: String
    private lateinit var moduleId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId = moduleId
    }

    fun getModules(): LiveData<List<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId)

    fun getSelectedModule(): LiveData<ModuleEntity> = academyRepository.getContent(courseId, moduleId)
}

