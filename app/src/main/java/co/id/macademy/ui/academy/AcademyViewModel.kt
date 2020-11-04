package co.id.macademy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import co.id.macademy.data.source.local.entity.CourseEntity
import co.id.macademy.data.AcademyRepository
import co.id.macademy.vo.Resource

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses(): LiveData<Resource<PagedList<CourseEntity>>> = academyRepository.getAllCourses()
}