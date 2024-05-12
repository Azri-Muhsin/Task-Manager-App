package com.example.nekonote

interface TaskItemListener {

    fun editTaskItem(taskEntity: TaskEntity)
    fun completeTaskItem(taskEntity: TaskEntity)
}