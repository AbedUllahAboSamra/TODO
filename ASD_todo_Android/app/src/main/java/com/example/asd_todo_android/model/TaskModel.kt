package com.example.asd_todo_android.model

data class TaskModel(
    var id: Int,
    var title: String,
    var note: String,
    var date: String,
    var color: String,
    var remind: String,
    var repeate: String,
    var startTime: String,
    var endTime: String,
) {

    companion object {
        val tableNAME = "TaskModel"
        val IDCOLUMN = "id"
        val TITLECOLUMN = "title"
        val NOTECOLUMN = "note"
        val DATECOLUMN = "date"
        val COLORCOLUMN = "color"
        val REMINDCOLUMN = "remind"
        val REPEATECOLUMN = "repeate"
        val StartCOLUMN = "startTime"
        val EndTimeCOLUMN = "endTime"


        val createTable = "Create Table $tableNAME(" +
                "$IDCOLUMN INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$TITLECOLUMN TEXT," +
                "$NOTECOLUMN TEXT," +
                "$DATECOLUMN TEXT," +
                "$COLORCOLUMN TEXT," +
                "$REMINDCOLUMN TEXT," +
                "$REPEATECOLUMN TEXT," +
                "$StartCOLUMN TEXT," +
                "$EndTimeCOLUMN TEXT" +
                ") "

        val dropTable = "DROP TABLE IF EXISTS $tableNAME"
    }


}