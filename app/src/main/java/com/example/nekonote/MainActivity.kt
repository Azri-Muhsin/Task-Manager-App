package com.example.nekonote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nekonote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskItemListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        binding.addTaskButton.setOnClickListener{

            NewTaskSheet(null).show(supportFragmentManager, "newTaskTag")

        }

        setRecyclerView()


    }

    private fun setRecyclerView() {
        val mainActivity = this
        taskViewModel.taskEntities.observe(this){
            binding.todoListRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TaskItemAdapter(it,mainActivity)
            }
        }
    }

    override fun editTaskItem(taskEntity: TaskEntity) {
        NewTaskSheet(taskEntity).show(supportFragmentManager, "newTaskTag")
    }

    override fun completeTaskItem(taskEntity: TaskEntity) {
       taskViewModel.setCompleted(taskEntity)
    }
}