package com.example.asd_todo_android.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asd_todo_android.R
import com.example.asd_todo_android.adapters.RecycleViewAdapter
import com.example.asd_todo_android.databinding.ActivityMainBinding
import com.example.asd_todo_android.service.MyJobService
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bindgin = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindgin.root)



        if(SplachActivity.allTasks.isEmpty()){
            bindgin.tasksText.visibility= View.VISIBLE
            bindgin.myTodoRecycle.visibility= View.GONE
        }else{
            bindgin.tasksText.visibility= View.GONE
            bindgin.myTodoRecycle.visibility= View.VISIBLE

        }



        //start initialization code
        var calendar = Calendar.getInstance()
        var arrDate = calendar.time.toString().split(" ")
        var date = "${arrDate[1]} ${arrDate[2]},${arrDate[arrDate.size - 1]}"
        bindgin.date.setText(date)

        //... end initialization code

        bindgin.Add.setOnClickListener {
            var i = Intent(this, add_Task::class.java)
            startActivity(i)
        }

//recycle view Code


        var adapter = RecycleViewAdapter(this, SplachActivity.allTasks)
        bindgin.myTodoRecycle.layoutManager = LinearLayoutManager(this)
        bindgin.myTodoRecycle.adapter = adapter


//end view Code


        var itemTouchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    var dilog = AlertDialog.Builder(this@MainActivity)
                        .setIcon(R.drawable.ic_baseline_delete_outline_24)
                        .setTitle("Delete Task")
                        .setMessage("Are you sure from delete this task ?")
                        .setNegativeButton("delete") { diloge, o ->
                            adapter.deleteItem(viewHolder.adapterPosition)

                            diloge.dismiss()
                            if(SplachActivity.allTasks.isEmpty()){
                                bindgin.tasksText.visibility= View.VISIBLE
                                bindgin.myTodoRecycle.visibility= View.GONE
                            }else{
                                bindgin.tasksText.visibility= View.GONE
                                bindgin.myTodoRecycle.visibility= View.VISIBLE

                            }
                        }
                        .setPositiveButton("cancel") { diloge, o ->
                            adapter.notifyDataSetChanged()
                            diloge.dismiss()

                        }

                        .show()
                }
            }).attachToRecyclerView(bindgin.myTodoRecycle)

    }
}