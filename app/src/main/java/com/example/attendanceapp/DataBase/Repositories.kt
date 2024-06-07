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
    fun getAllDates(): LiveData<List<String>> {
        return APPDao.APPDao().getAllDates()
    }

    suspend fun insert(attendance: Attendance) {
        APPDao.APPDao().insert(attendance)
    }

    suspend fun update(attendance: Attendance) {
        APPDao.APPDao().update(attendance)
    }

    suspend fun delete(attendance: Attendance) {
        APPDao.APPDao().delete(attendance)
    }

    fun getAttendanceForStudent(studentId: Int): LiveData<List<Attendance>> {
        return APPDao.APPDao().getAttendanceForStudent(studentId)
    }

    fun getAllAttendance(): LiveData<List<Attendance>> {
        return APPDao.APPDao().getAllAttendance()
    }

    suspend fun getAttendanceByDate(studentId: Int, date: String): Attendance? {
        return APPDao.APPDao().getAttendanceByDate(studentId, date)
    }
}