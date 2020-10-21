package co.id.macademy.data.source

import co.id.macademy.data.CourseEntity
import co.id.macademy.data.ModuleEntity

interface AcademyDataSource {

    fun  getAllCourses(): List<CourseEntity>

    fun getBookmarkedCourses(): List<CourseEntity>

    fun getCourseWithModules(courseId: String): CourseEntity

    fun getAllModulesByCourse(courseId: String): List<ModuleEntity>

    fun getContent(courseId: String, moduleId: String): ModuleEntity
}