package com.example.attendanceapp.DataBase
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
class AppViewModel(val repository: APPRepository) : ViewModel() {

//Student


    fun insert(student: Students) = viewModelScope.launch {
        repository.insert(student)
    }

    fun update(student: Students) = viewModelScope.launch {
        repository.update(student)
    }

    fun delete(student: Students) = viewModelScope.launch {
        repository.delete(student)
    }

    fun getStudentsByGroupId(groupeId: Int): LiveData<List<Students>> {
        return repository.getStudentsByGroupId(groupeId)
    }

    fun getAllStudents(): LiveData<List<Students>> {
        return repository.getAllStudents()
    }

    private val _foundStudents = mutableStateOf<List<Students>>(listOf())
    val foundStudents: MutableState<List<Students>> = _foundStudents

    fun searchStudents(name: String) {
        viewModelScope.launch {
            val students = repository.searchStudentsByName(name)
            _foundStudents.value = students
        }
    }

//Group
    val group =repository.readAllGroup()
    val selectedGroup = MutableStateFlow<Groups?>(null)
    fun insert(group: Groups) = viewModelScope.launch {
        repository.insert(group)
    }

    fun update(group: Groups) = viewModelScope.launch {
        repository.update(group)
    }

    fun delete(group: Groups) = viewModelScope.launch {
        repository.delete(group)
    }

    fun selectGroup(group: Groups) {
        selectedGroup.value = group
    }



    fun GetallGroupes(): LiveData<List<Groups>> {
        return repository.reaAllGroupNotOrdred()
    }


    //Attendenc
    val attendancee =repository.getAllAttendance()

    private val _attendanceByDate = MutableLiveData<Attendance?>()
    val attendanceByDate: LiveData<Attendance?> get() = _attendanceByDate

    fun getAttendanceByDate(studentId: Int, date: String) = viewModelScope.launch(Dispatchers.IO) {
        val attendance = repository.getAttendanceByDate(studentId, date)
        _attendanceByDate.postValue(attendance)
    }

    fun insert(attendance: Attendance) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(attendance)
    }

    fun update(attendance: Attendance) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(attendance)
    }

    fun delete(attendance: Attendance) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(attendance)
    }

    fun getAllDates(): LiveData<List<String>> {
        return repository.getAllDates()
    }
}