package com.example.attendanceapp.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Students::class, Groups::class, Attendance::class], version = 1, exportSchema = false)
abstract class AttendanceAppDatabase : RoomDatabase() {
    abstract fun APPDao(): APPDao
    companion object {
        @Volatile
        private var Instance: AttendanceAppDatabase? = null

        fun getDatabase(context: Context): AttendanceAppDatabase {
            synchronized(this) {
                var instance = Instance
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AttendanceAppDatabase::class.java,
                        "attendance_database"
                    ).build()
                }
                return instance
            }
        }
    }
}