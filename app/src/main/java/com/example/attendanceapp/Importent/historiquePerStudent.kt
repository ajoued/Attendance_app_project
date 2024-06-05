package com.example.attendanceapp.Importent

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.attendanceapp.DataBase.APPRepository
import com.example.attendanceapp.DataBase.AppViewModel
import com.example.attendanceapp.DataBase.AttendanceAppDatabase
import com.example.attendanceapp.HistorySessionn
import com.example.attendanceapp.HistoryStudentt
import com.example.attendanceapp.MangeGrr


@Composable
fun HistoryStudent(navController : NavHostController, modifier: Modifier = Modifier){
    val contex = LocalContext.current
    val db = AttendanceAppDatabase.getDatabase(contex)
    val repository = APPRepository(db)
    val myViewModel = AppViewModel(repository)

    HistoryStudentt(navController,myViewModel)
}