package com.example.asd_todo_android.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.asd_todo_android.model.TaskModel

class MyDataBase(var context: Context) : SQLiteOpenHelper(context, dataBaseName, null, version) {


    companion object {
        var version = 5
        var dataBaseName = "MyTaskDataBase"
    }

    val db = this.writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(TaskModel.createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(TaskModel.dropTable)
        onCreate(db)
    }


    fun addTask(task: TaskModel): Boolean {
        var cont = ContentValues()

        cont.put(TaskModel.TITLECOLUMN, task.title)
        cont.put(TaskModel.NOTECOLUMN, task.note)
        cont.put(TaskModel.DATECOLUMN, task.date)
        cont.put(TaskModel.COLORCOLUMN, task.color)
        cont.put(TaskModel.REMINDCOLUMN, task.remind)
        cont.put(TaskModel.REPEATECOLUMN, task.repeate)
        cont.put(TaskModel.StartCOLUMN, task.startTime)
        cont.put(TaskModel.EndTimeCOLUMN, task.endTime)

        return db.insert(TaskModel.tableNAME, null, cont) > 0
    }

    fun getAllTasks(): ArrayList<TaskModel> {
        var arr = ArrayList<TaskModel>()

        var car = db.rawQuery("SELECT * FROM ${TaskModel.tableNAME} Order by ${TaskModel.StartCOLUMN}", null)
        car.moveToFirst()

        while (!car.isAfterLast) {
            val id = car.getInt(0)
            val title = car.getString(1)
            val note = car.getString(2)
            val date = car.getString(3)
            val color = car.getString(4)
            val remind = car.getString(5)
            val repeate = car.getString(6)
            val starttime = car.getString(7)
            val endtime = car.getString(8)
            var task = TaskModel(id, title, note, date, color, remind, repeate, starttime, endtime)
            arr.add(task)
            car.moveToNext()
        }
        car.close()


        return arr
    }


    fun deleteItem(i: Int): Boolean {

        return db.delete(TaskModel.tableNAME, "${TaskModel.IDCOLUMN}=$i", null) > 0


    }

}