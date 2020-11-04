package co.id.macademy.ui.academy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.macademy.R
import co.id.macademy.utils.DataDummy
import co.id.macademy.viewmodel.ViewModelFactory
import co.id.macademy.vo.Status
import kotlinx.android.synthetic.main.fragment_academy.*


class AcademyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_academy, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[AcademyViewModel::class.java]
            val courses = viewModel.getCourses()

            val academyAdapter = AcademyAdapter()
            viewModel.getCourses().observe(this, Observer { courses ->
                if (courses != null) {
                    when (courses.status) {
                        Status.LOADING -> progress_bar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            progress_bar.visibility = View.GONE
                            academyAdapter.setCourses(courses.data)
                            academyAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            progress_bar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })


            with(rv_academy) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
            }
        }
    }
}