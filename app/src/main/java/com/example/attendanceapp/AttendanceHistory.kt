package com.example.attendanceapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AttendanceHistory (modifier: Modifier = Modifier) {
    Scaffold (topBar = { TopAppBar(text = "Attendance history") }
    ) {innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .run {
                    fillMaxSize()
                        .padding(
                            innerPadding
                        )
                }
                .padding(bottom = 120.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.home_icon_team_tr),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}