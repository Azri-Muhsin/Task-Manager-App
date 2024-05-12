package com.example.nekonote

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.nekonote.databinding.ActivityMainBinding
import com.example.nekonote.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalTime


class NewTaskSheet(var taskEntity: TaskEntity?) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var taskViewModel: TaskViewModel
    private var dueTime: LocalTime? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()



        if(taskEntity != null){
            binding.newTaskTitle.text = "Update Task"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(taskEntity!!.title)
            binding.details.text = editable.newEditable(taskEntity!!.description)
            if(taskEntity!!.completionTime != null){
                dueTime = taskEntity!!.completionTime!!
                updateTimeButtonText()
            }
        }
        else{
            binding.newTaskTitle.text = "New Task"
        }
        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.submitButton.setOnClickListener{
            saveAction()
        }

        binding.timePickerButton.setOnClickListener{
           openTimePicker()
        }
    }

    private fun openTimePicker() {
        if (dueTime == null)
            dueTime = LocalTime.now()

        val listener = TimePickerDialog.OnTimeSetListener{ _, selectedHour, selectedMinute ->
            dueTime = LocalTime.of(selectedHour, selectedMinute)
            updateTimeButtonText()

        }

        val dialog = TimePickerDialog(activity, listener, dueTime!!.hour, dueTime!!.minute, true)
        dialog.setTitle("Task Due")
        dialog.show()
    }

    private fun updateTimeButtonText() {
        binding.timePickerButton.text = String.format("%02d:%02d", dueTime!!.hour, dueTime!!.minute)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun saveAction(){

       val title = binding.name.text.toString()
       val description = binding.details.text.toString()
        if (taskEntity == null){
            val newTask = TaskEntity(title, description, dueTime,null)
            taskViewModel.addTaskItem(newTask)
        }
        else{
            taskViewModel.UpdateTaskItem(taskEntity!!.id, title, description, dueTime)

        }
        binding.name.setText("")
        binding.details.setText("")
        dismiss()
    }

}