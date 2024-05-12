package com.example.nekonote

import android.content.Context
import android.graphics.Paint
import android.widget.ExpandableListView.OnChildClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.nekonote.databinding.TaskEntityCellBinding
import java.time.format.DateTimeFormatter

class TaskEntityViewHolder(
    private val context: Context,
    private val binding: TaskEntityCellBinding,
    private val clickListener: TaskItemListener
): RecyclerView.ViewHolder(binding.root) {

   private  val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

    fun bindTaskItem(taskEntity: TaskEntity){
        binding.title.text = taskEntity.title

        if(taskEntity.isCompleted()){
            binding.title.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.dueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        binding.completeButton.setImageResource(taskEntity.imageResource())
        binding.completeButton.setColorFilter(taskEntity.imageColor(context))

        binding.completeButton.setOnClickListener{
            clickListener.completeTaskItem(taskEntity)
        }

        binding.taskCellContainer.setOnClickListener{
            clickListener.editTaskItem(taskEntity)
        }

        if(taskEntity.completionTime != null)
            binding.dueTime.text  = timeFormat.format(taskEntity.completionTime)
        else
            binding.dueTime.text = ""
    }
}