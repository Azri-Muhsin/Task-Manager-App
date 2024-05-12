package com.example.nekonote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class TaskViewModel: ViewModel (){

    var taskEntities = MutableLiveData<MutableList<TaskEntity>>()


    init {
        taskEntities.value = mutableListOf()
    }

    fun addTaskItem(newTask : TaskEntity){
        val list = taskEntities.value
        list!!.add(newTask)
        taskEntities.postValue(list)
    }

    fun UpdateTaskItem(id: UUID, title: String, description:String, completionTime: LocalTime?){
        val list = taskEntities.value
        val task = list!!.find { it.id == id }!!
        task.title = title
        task.description = description
        task.completionTime = completionTime
        taskEntities.postValue(list)
    }

    fun setCompleted(taskEntity : TaskEntity){
        val list = taskEntities.value
        val task = list!!.find { it.id == taskEntity.id }!!

        if(task.completionDate == null){
            task.completionDate = LocalDate.now()
        }
        taskEntities.postValue(list)
    }
}