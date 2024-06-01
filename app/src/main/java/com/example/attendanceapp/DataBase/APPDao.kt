package com.example.attendanceapp.DataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface APPDao {
    @Insert
    suspend fun insertStudent(student: Students)

    // Remove student
    @Delete
    suspend fun deleteStudent(student: Students)

    @Query("SELECT * FROM Students WHERE groupeId = :groupeId")
    fun getStudentsByGroupId(groupeId: Int): LiveData<List<Students>>
    @Query("SELECT * FROM Students WHERE studentName LIKE :name")
    suspend fun getStudentsByName(name: String): List<Students>


    // Insert group
    @Insert
    suspend fun insertGroup(group: Groups)

    // Remove group
    @Delete
    suspend fun deleteGroup(group: Groups)

    @Query("SELECT * FROM Groups")
    fun getAllGroupsNotOrdred(): LiveData<List<Groups>>
    @Query("SELECT * FROM Groups ORDER BY GroupeId ASC")
    fun readAllDataGroup(): Flow<List<Groups>>

    // Add attendance
    @Insert
    suspend fun addAttendance(attendance: Attendance)

    // Remove attendance
    @Delete
    suspend fun removeAttendance(attendance: Attendance)


    @Query("SELECT * FROM Attendance ORDER BY attendanceId ASC")
    fun readAllDataAttendance(): Flow<List<Attendance>>


    @Query("SELECT date FROM Attendance")
    fun getAllDates(): LiveData<List<String>>
}