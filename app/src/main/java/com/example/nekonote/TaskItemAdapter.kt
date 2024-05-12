package com.example.nekonote

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListView.OnChildClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.nekonote.databinding.TaskEntityCellBinding

class TaskItemAdapter(
    private val taskEntity : List<TaskEntity>,
    private val clickListener: TaskItemListener
): RecyclerView.Adapter<TaskEntityViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskEntityViewHolder {
       val from = LayoutInflater.from(parent.context)
        val binding = TaskEntityCellBinding.inflate(from, parent, false)
        return TaskEntityViewHolder(parent.context, binding, clickListener)
    }

    override fun getItemCount(): Int = taskEntity.size

    override fun onBindViewHolder(holder: TaskEntityViewHolder, position: Int) {
        holder.bindTaskItem(taskEntity[position])
    }
}