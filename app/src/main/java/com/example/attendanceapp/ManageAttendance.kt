package com.example.attendanceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.attendanceapp.ui.theme.AttendanceAppTheme


@Composable
fun ManageAttendace (navController : NavHostController , modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { TopAppBar("Manage Attendance") }
    ) {innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .run {
                    fillMaxSize()
                        .padding(
                            innerPadding
                        )
                }.padding(bottom = 120.dp),
            contentAlignment = Alignment.Center
        ){
            Column {
                Button(
                    onClick = { /*TODO*/ } ,
                    shape = RoundedCornerShape(5.dp) ,
                    modifier = modifier
                        .width(250.dp)
                        .height(70.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.attendance_icon_24) ,
                        contentDescription = null ,
                        modifier = Modifier.size(50.dp)
                    )

                    Spacer(modifier = modifier.width(10.dp))

                    Text(
                        text = "Record Attendance" ,
                        fontSize = 21.sp ,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = modifier.height(35.dp))

                Button(
                    onClick = { /*TODO*/ } ,
                    shape = RoundedCornerShape(5.dp) ,
                    modifier = modifier
                        .width(250.dp)
                        .height(70.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.free_cancellation_fill0_wght400_grad0_opsz24) ,
                        contentDescription = null ,
                        modifier = Modifier.size(50.dp)
                    )

                    Spacer(modifier = modifier.width(10.dp))

                    Text(
                        text = "Manage attendance" ,
                        fontSize = 21.sp ,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
        Box (modifier = Modifier
            .fillMaxWidth()
            .run {
                fillMaxSize()
                    .padding(
                        innerPadding
                    )
            }

            ,
            contentAlignment = Alignment.BottomCenter){

        }
    }
}

