package co.id.macademy.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.macademy.R
import co.id.macademy.data.source.local.entity.CourseEntity
import co.id.macademy.ui.reader.CourseReaderActivity
import co.id.macademy.viewmodel.ViewModelFactory
import co.id.macademy.vo.Status
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_course.*
import kotlinx.android.synthetic.main.content_detail_course.*

class DetailCourseActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = DetailCourseAdapter()

        val factory = ViewModelFactory.getInstance(this)

        val viewModel = ViewModelProvider(this, factory)[DetailCourseViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val courseId = extras.getString(EXTRA_COURSE)
            if (courseId != null) {
                viewModel.setSelectedCourse(courseId)
                viewModel.courseModule.observe(this, Observer { courseWithModuleResource ->
                    if (courseWithModuleResource != null) {
                        when (courseWithModuleResource.status) {
                            Status.LOADING -> progress_bar.visibility = View.VISIBLE
                            Status.SUCCESS -> if (courseWithModuleResource.data != null) {
                                progress_bar.visibility = View.GONE
                                adapter.setModules(courseWithModuleResource.data.mModules)
                                adapter.notifyDataSetChanged()
                                populateCourse(courseWithModuleResource.data.mCourse)
                            }
                            Status.ERROR -> {
                                progress_bar.visibility = View.GONE
                                Toast.makeText(
                                    applicationContext,
                                    "Terjadi kesalahan",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                })
            }
        }

        with(rv_module) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailCourseActivity)
            setHasFixedSize(true)
            this.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(rv_module.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun populateCourse(courseEntity: CourseEntity) {
        text_title.text = courseEntity.title
        text_desc.text = courseEntity.description
        text_date.text = resources.getString(R.string.deadline_date, courseEntity.deadline)

        Glide.with(this)
            .load(courseEntity.imagePath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(image_poster)

        btn_start.setOnClickListener {
            val intent = Intent(this@DetailCourseActivity, CourseReaderActivity::class.java)
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseEntity.courseId)
            startActivity(intent)
        }
    }
}
