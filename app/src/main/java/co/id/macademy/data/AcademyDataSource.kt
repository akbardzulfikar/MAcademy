package co.id.macademy.data

import co.id.macademy.data.source.local.entity.CourseEntity
import co.id.macademy.data.source.local.entity.ModuleEntity

interface AcademyDataSource {

    fun  getAllCourses(): List<CourseEntity>

    fun getBookmarkedCourses(): List<CourseEntity>

    fun getCourseWithModules(courseId: String): CourseEntity

    fun getAllModulesByCourse(courseId: String): List<ModuleEntity>

    fun getContent(courseId: String, moduleId: String): ModuleEntity
}