package co.id.macademy.ui.reader

interface CourseReaderCallback {
    fun moveTo(position: Int, moduleId: String)
}