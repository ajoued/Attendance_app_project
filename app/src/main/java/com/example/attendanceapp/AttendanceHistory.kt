package com.example.attendanceapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.attendanceapp.ui.theme.AttendanceAppTheme

@Composable
fun AttendanceHistory (navController : NavHostController ,modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { TopAppBar("Manage Attendance") }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .run {
                    fillMaxSize()
                        .padding(
                            innerPadding
                        )
                }.padding(bottom = 120.dp) ,
            contentAlignment = Alignment.Center
        ) {
            Column {
                Button(
                    onClick = { /*TODO*/ } ,
                    shape = RoundedCornerShape(5.dp) ,
                    modifier = modifier
                        .width(250.dp)
                        .height(70.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.home_icon_team_tr) ,
                        contentDescription = null ,
                        modifier = Modifier.size(50.dp),
                        tint = Color.Black
                    )

                    Spacer(modifier = modifier.width(10.dp))

                    Text(
                        text = " Select Group" ,
                        fontSize = 21.sp ,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = modifier.height(150.dp))

                Button(
                    onClick = {
                        navController.navigate("9")
                    } ,
                    shape = RoundedCornerShape(5.dp) ,
                    modifier = modifier
                        .width(250.dp)
                        .height(70.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.history) ,
                        contentDescription = null ,
                        modifier = Modifier.size(50.dp),
                        tint = Color.Black
                    )

                    Spacer(modifier = modifier.width(10.dp))

                    Text(
                        text = "History per Session" ,
                        fontSize = 21.sp ,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = modifier.height(35.dp))

                Button(
                    onClick = {
                        navController.navigate("10")
                    } ,
                    shape = RoundedCornerShape(5.dp) ,
                    modifier = modifier
                        .width(250.dp)
                        .height(70.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.history) ,
                        contentDescription = null ,
                        modifier = Modifier.size(50.dp),
                        tint = Color.Black
                    )

                    Spacer(modifier = modifier.width(10.dp))

                    Text(
                        text = "History per student" ,
                        fontSize = 21.sp ,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
    }
}
