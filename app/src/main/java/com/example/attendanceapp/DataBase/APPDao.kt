package com.example.attendanceapp.DataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface APPDao {
    @Insert
    suspend fun insertStudent(student: Students)

    @Update
    suspend fun update(student: Students)

    // Remove student
    @Delete
    suspend fun deleteStudent(student: Students)

    @Query("SELECT * FROM Students WHERE groupeId = :groupeId")
    fun getStudentsByGroupId(groupeId: Int): LiveData<List<Students>>
    @Query("SELECT * FROM Students WHERE studentName LIKE :name")
    suspend fun getStudentsByName(name: String): List<Students>

    @Query("SELECT * FROM Students")
    fun getAllStudents(): LiveData<List<Students>>

    // Insert group
    @Insert
    suspend fun insertGroup(group: Groups)
    @Update
    suspend fun update(group: Groups)

    // Remove group
    @Delete
    suspend fun deleteGroup(group: Groups)

    @Query("SELECT * FROM Groups")
    fun getAllGroupsNotOrdred(): LiveData<List<Groups>>
    @Query("SELECT * FROM Groups ORDER BY GroupeId ASC")
    fun readAllDataGroup(): Flow<List<Groups>>



    // Add attendance

    @Query("SELECT * FROM Attendance ORDER BY attendanceId ASC")
    fun readAllDataAttendance(): Flow<List<Attendance>>


    @Query("SELECT date FROM Attendance")
    fun getAllDates(): LiveData<List<String>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(attendance: Attendance)

    @Update
    suspend fun update(attendance: Attendance)

    @Delete
    suspend fun delete(attendance: Attendance)

    @Query("SELECT * FROM Attendance WHERE studentId = :studentId AND date = :date")
    suspend fun getAttendanceByDate(studentId: Int, date: String): Attendance?

    @Query("SELECT * FROM Attendance WHERE studentId = :studentId")
    fun getAttendanceForStudent(studentId: Int): LiveData<List<Attendance>>

    @Query("SELECT * FROM Attendance")
    fun getAllAttendance(): LiveData<List<Attendance>>


}