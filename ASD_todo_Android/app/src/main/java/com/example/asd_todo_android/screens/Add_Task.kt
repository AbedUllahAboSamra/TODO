package com.example.asd_todo_android.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.asd_todo_android.databinding.ActivityAddTaskBinding
import com.example.asd_todo_android.db.MyDataBase
import com.example.asd_todo_android.model.TaskModel
import java.text.SimpleDateFormat
import java.util.*


class add_Task : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // difinition
        var db = MyDataBase(this)

        // end difintion

        //Start initialize

        binding.binckTab.setSelected(true)
        var calendar = Calendar.getInstance()
        var dayDate = SimpleDateFormat("yyyy/MM/dd").format(calendar.time)
        binding.edtDate.setText(dayDate)

        var startTime = SimpleDateFormat("h:mm a").format(calendar.time)

        val df = SimpleDateFormat("HH:mm")

        val d = df.parse(SimpleDateFormat("h:mm").format(calendar.time))
        val cal = Calendar.getInstance()
        cal.time = d
        cal.add(Calendar.MINUTE, 5)
        val endTime = df.format(cal.time)

        binding.edtStartTimse.setText(startTime)
        binding.edtStartTime.setText(endTime)


        //end initialize


//start tab click

        binding.redTab.setOnClickListener {

            binding.redTab.setSelected(true)
            binding.yelloTab.setSelected(false)
            binding.binckTab.setSelected(false)
        }
        binding.yelloTab.setOnClickListener {

            binding.yelloTab.setSelected(true)
            binding.redTab.setSelected(false)
            binding.binckTab.setSelected(false)

        }
        binding.binckTab.setOnClickListener {

            binding.binckTab.setSelected(true)
            binding.yelloTab.setSelected(false)
            binding.redTab.setSelected(false)

        }

//end tab click


        binding.btnAdd.setOnClickListener {
            var color = "red"
            var title = binding.edtTitle.text.toString()
            var note = binding.edtNote.text.toString()
            var edtDate = binding.edtDate.text.toString()
            var edtStartTime = binding.edtStartTime.text.toString()
            var edtEndTimse = binding.edtStartTimse.text.toString()
            var edtRemind = binding.edtRemind.selectedItem.toString()
            var edtRepeat = binding.edtRepeat.selectedItem.toString()
            if (title.isEmpty()
                || note.isEmpty()
                || edtDate.isEmpty()
                || edtStartTime.isEmpty()
                || edtEndTimse.isEmpty()
            ) {
                Toast.makeText(this, "field cant be empty", Toast.LENGTH_SHORT).show()
            } else {
                if (binding.redTab.isSelected) {
                    color = "RED"
                } else if (binding.yelloTab.isSelected) {
                    color = "YELLOW"
                } else {
                    color = "PINK"
                }

                var task = TaskModel(
                    0,
                    title,
                    note,
                    edtDate,
                    color,
                    edtRemind,
                    edtRepeat,
                    edtStartTime,
                    edtEndTimse
                )
                var result = db.addTask(task)

                if (result) {
                    Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show()
                    SplachActivity.allTasks.clear()
                    SplachActivity.allTasks.addAll(db.getAllTasks())
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()

                }

            }


        }



        binding.edtStartTime.setOnClickListener {
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
            val minute = mcurrentTime.get(Calendar.MINUTE)

            var TimePicker = TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    binding.edtStartTime.setText(String.format("%d : %d", hourOfDay, minute))
                }
            }, hour, minute, false).show()
        }
        binding.edtStartTimse.setOnClickListener {
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
            val minute = mcurrentTime.get(Calendar.MINUTE)

            var TimePicker = TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    binding.edtStartTimse.setText(String.format("%d : %d", hourOfDay, minute))
                }
            }, hour, minute, false)
            TimePicker.show()
        }


        binding.edtDate.setOnClickListener {

            var datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                    binding.edtDate.setText("$year/$month/$dayOfMonth")

                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()


        }

        binding.imageView.setOnClickListener {
            this.onBackPressed()
        }


    }

}