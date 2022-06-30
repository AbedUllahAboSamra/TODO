package com.example.asd_todo_android.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.ColorSpace
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.asd_todo_android.databinding.DesignTaskRecycleViewBinding
import com.example.asd_todo_android.db.MyDataBase
import com.example.asd_todo_android.model.TaskModel

class RecycleViewAdapter(var context: Context, var arr: ArrayList<TaskModel>) :
    RecyclerView.Adapter<RecycleViewAdapter.myViewHollder>() {

    var db = MyDataBase(context)
    class myViewHollder(var binding: DesignTaskRecycleViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHollder {
        var binding =
            DesignTaskRecycleViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return myViewHollder(binding)

    }

    override fun onBindViewHolder(holder: myViewHollder, position: Int) {
        holder.binding.textStartTime.text = arr[position].startTime
        holder.binding.textEndTime.text = arr[position].endTime
        holder.binding.textTitle.text = arr[position].title
        holder.binding.textNote.text = arr[position].note

        if (arr[position].color == "YELLOW") {
            holder.binding.backgroundTask.setBackgroundColor(Color.rgb(95, 67, 142))
        } else if (arr[position].color == "RED") {
            holder.binding.backgroundTask.setBackgroundColor(Color.rgb(244, 95, 108))

        } else {
            holder.binding.backgroundTask.setBackgroundColor(Color.rgb(218, 189, 80))

        }


    }

    override fun getItemCount(): Int {
        return arr.size
    }

    fun deleteItem(index: Int) {
        var bool = db.deleteItem(arr[index].id)
        if (bool){
            arr.removeAt(index)
            notifyDataSetChanged()
        }
    }
}