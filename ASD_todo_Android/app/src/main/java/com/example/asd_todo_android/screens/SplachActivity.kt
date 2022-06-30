package com.example.asd_todo_android.screens

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import com.example.asd_todo_android.databinding.ActivitySplachBinding
import com.example.asd_todo_android.db.MyDataBase
import com.example.asd_todo_android.model.TaskModel
import com.example.asd_todo_android.service.MyJobService
import kotlin.math.log

class SplachActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivitySplachBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }


        var db = MyDataBase(this)
        if (db.getAllTasks().isNotEmpty()) {
            allTasks.addAll(db.getAllTasks())
        }

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 5000)

    }

    companion object {
        var allTasks = ArrayList<TaskModel>()
    }
}