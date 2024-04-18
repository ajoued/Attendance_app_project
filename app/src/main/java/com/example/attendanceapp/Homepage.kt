package com.example.attendanceapp

import android.graphics.Paint.Style
import android.graphics.drawable.shapes.Shape
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.TextStyle


@Composable
fun Houmepg( modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { TopAppBar("Attendance App") }
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
            Column() {
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
                        modifier = Modifier.size(50.dp)
                    )

                    Spacer(modifier = modifier.width(10.dp))

                    Text(
                        text = "Manage Group" ,
                        fontSize = 21.sp ,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = modifier.height(20.dp))

                Button(
                    onClick = { /*TODO*/ } ,
                    shape = RoundedCornerShape(5.dp) ,
                    modifier = modifier
                        .width(250.dp)
                        .height(70.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.edit_calendar_black_24dp) ,
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
            Image(
                painter = painterResource(R.drawable.studentbackground) ,
                contentDescription = null ,
            modifier=modifier
                .padding(bottom = 30.dp)
                .fillMaxWidth()
            )
        }
    }
}


















@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(text : String ,modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.method_draw_image),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = modifier.width(20.dp))
                Text(
                    text = text,
                    style = MaterialTheme.typography.displayLarge,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )


            }
        },
        modifier = modifier.padding(top = 10.dp)
    )
}
