package com.example.attendanceapp.DataBase


import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class APPRepository(private val APPDao: AttendanceAppDatabase) {
    suspend fun insert(group: Groups) {
        APPDao.APPDao().insertGroup(group)
    }

    suspend fun delete(group: Groups) {
        APPDao.APPDao().deleteGroup(group)
    }

    suspend fun update(group: Groups) {
        APPDao.APPDao().update(group)
    }

    fun readAllGroup () : Flow<List<Groups>> = APPDao.APPDao().readAllDataGroup()
    fun reaAllGroupNotOrdred(): LiveData<List<Groups>> = APPDao.APPDao().getAllGroupsNotOrdred()

    fun getStudentsByGroupId(groupeId: Int): LiveData<List<Students>> {
        return APPDao.APPDao().getStudentsByGroupId(groupeId)
    }
    suspend fun insert(student: Students) {
        APPDao.APPDao().insertStudent(student)
    }


    suspend fun delete(student: Students) {
        APPDao.APPDao().deleteStudent(student)
    }
    fun getAllStudents(): LiveData<List<Students>> = APPDao.APPDao().getAllStudents()


    suspend fun update(student: Students) {
        APPDao.APPDao().update(student)
    }

    suspend fun searchStudentsByName(name: String): List<Students> {
        return APPDao.APPDao().getStudentsByName(name)
    }

    fun readAllAttendence () : Flow<List<Attendance>> = APPDao.APPDao().readAllDataAttendance()

    suspend fun add(attendance: Attendance) {
        APPDao.APPDao().addAttendance(attendance)
    }
    suspend fun remove(attendance: Attendance){
        APPDao.APPDao().removeAttendance(attendance)
    }

    fun getAllDates(): LiveData<List<String>> {
        return APPDao.APPDao().getAllDates()
    }
}