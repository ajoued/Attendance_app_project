package com.example.attendanceapp


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.attendanceapp.ui.theme.AttendanceAppTheme


@Composable
fun TakeAttn(navController : NavHostController ,modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { TopAppBar("Taking Attendance") }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .run {
                    fillMaxSize()
                        .padding(
                            innerPadding
                        )
                } ,
            contentAlignment = Alignment.Center
        ) {

        }
    }
}
