package co.id.macademy.ui.reader.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.macademy.R
import co.id.macademy.data.source.local.entity.ModuleEntity
import co.id.macademy.ui.reader.CourseReaderActivity
import co.id.macademy.ui.reader.CourseReaderCallback
import co.id.macademy.ui.reader.CourseReaderViewModel
import co.id.macademy.vo.Status
import kotlinx.android.synthetic.main.fragment_module_list.*

class ModuleListFragment : Fragment(), MyAdapterClickListener {

    companion object {
        val TAG = ModuleListFragment::class.java.simpleName

        fun newInstance(): ModuleListFragment = ModuleListFragment()
    }

    private lateinit var viewModel: CourseReaderViewModel

    private lateinit var adapter: ModuleListAdapter
    private lateinit var courseReaderCallback: CourseReaderCallback

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())[CourseReaderViewModel::class.java]
        adapter = ModuleListAdapter(this)
        viewModel.modules.observe(this, Observer{ moduleEntities ->
            if (moduleEntities != null) {
                when (moduleEntities.status) {
                    Status.LOADING -> progress_bar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        populateRecyclerView(moduleEntities.data as List<ModuleEntity>)
                    }
                    Status.ERROR -> {
                        progress_bar.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        courseReaderCallback = context as CourseReaderActivity
    }

    override fun onItemClicked(position: Int, moduleId: String) {
        courseReaderCallback.moveTo(position, moduleId)
        viewModel.setSelectedModule(moduleId)
    }

    private fun populateRecyclerView(modules: List<ModuleEntity>) {
        progress_bar.visibility = View.GONE
        adapter.setModules(modules)
        rv_module.layoutManager = LinearLayoutManager(context)
        rv_module.setHasFixedSize(true)
        rv_module.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(rv_module.context, DividerItemDecoration.VERTICAL)
        rv_module.addItemDecoration(dividerItemDecoration)
    }
}
