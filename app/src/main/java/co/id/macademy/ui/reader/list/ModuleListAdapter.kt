package co.id.macademy.ui.reader.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import co.id.macademy.R
import co.id.macademy.data.source.local.entity.ModuleEntity
import co.id.macademy.ui.detail.DetailCourseAdapter
import kotlinx.android.synthetic.main.items_module_list.view.*
import java.util.ArrayList

class ModuleListAdapter internal constructor(private val listener: MyAdapterClickListener) : RecyclerView.Adapter<ModuleListAdapter.ModuleViewHolder>() {
    private val listModules = ArrayList<ModuleEntity>()

    internal fun setModules(modules: List<ModuleEntity>?) {
        if (modules == null) return
        this.listModules.clear()
        this.listModules.addAll(modules)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_module_list_custom, parent, false)
        return ModuleViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ModuleViewHolder, position: Int) {
        val module = listModules[position]
        viewHolder.bind(module)
        viewHolder.itemView.setOnClickListener {
            listener.onItemClicked(viewHolder.adapterPosition, listModules[viewHolder.adapterPosition].moduleId)
        }
    }

    override fun getItemCount(): Int = listModules.size

    inner class ModuleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textTitle: TextView = itemView.findViewById(R.id.text_module_title)
        fun bind(module: ModuleEntity) {
            textTitle.text = module.title
        }
    }
}

internal interface MyAdapterClickListener {
    fun onItemClicked(position: Int, moduleId: String)
}


