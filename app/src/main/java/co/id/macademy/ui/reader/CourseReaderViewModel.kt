package co.id.macademy.ui.reader

import androidx.lifecycle.ViewModel
import co.id.macademy.data.ContentEntity
import co.id.macademy.data.ModuleEntity
import co.id.macademy.data.source.AcademyRepository
import co.id.macademy.utils.DataDummy
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

    fun getModules(): ArrayList<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)

    fun getSelectedModule(): ModuleEntity = academyRepository.getContent(courseId, moduleId)
}

