package com.example.attendanceapp.DataBase

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "Groups")
data class Groups(
    val GroupeNumber : Int,
    @PrimaryKey(autoGenerate = true) val GroupeId : Int  = 0
)



@Entity(tableName = "Students",
    foreignKeys = [ForeignKey(
        entity = Groups::class,
        parentColumns = ["GroupeId"],
        childColumns = ["groupeId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["groupeId"])])
data class Students(
    val studentName : String,
    @PrimaryKey(autoGenerate = true) val StudentID : Int  = 0,
    val groupeId: Int
)




@Entity(tableName = "Attendance")
data class Attendance(
    @PrimaryKey(autoGenerate = true) val attendanceId: Int,
    val date : String
)

