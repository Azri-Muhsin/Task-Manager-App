package com.example.nekonote

import android.content.Context
import androidx.core.content.ContextCompat
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class TaskEntity(

    var title : String,
    var description: String,
    var completionTime: LocalTime?,
    var completionDate: LocalDate?,
    var id: UUID = UUID.randomUUID()

) {

    fun isCompleted() = completionDate != null
    fun imageResource(): Int = if(isCompleted()) R.drawable.checked_24 else R.drawable.unchecked_24
   fun imageColor(context: Context): Int = if(isCompleted()) green(context) else black(context)

    private fun purple(context: Context) = ContextCompat.getColor(context, R.color.purple)
    private fun green(context: Context) = ContextCompat.getColor(context, R.color.green)
    private fun red(context: Context) = ContextCompat.getColor(context, R.color.red)
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)


}