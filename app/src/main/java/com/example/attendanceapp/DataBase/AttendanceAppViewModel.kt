package com.example.attendanceapp.DataBase
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    val attendancee =repository.readAllAttendence()

    fun addd(attendance: Attendance) = viewModelScope.launch {
        repository.add(attendance)
    }
    fun remove(attendance: Attendance) = viewModelScope.launch {
        repository.remove(attendance)
    }


    fun getAllDates(): LiveData<List<String>> {
        return repository.getAllDates()
    }
}