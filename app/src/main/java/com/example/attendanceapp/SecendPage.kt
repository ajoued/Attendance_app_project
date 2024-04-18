package com.example.attendanceapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun MangeGr (navController : NavHostController , modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { TopAppBar("Manage Groups") }
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
         Spacer(modifier = modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .run {
                    fillMaxSize()
                        .padding(
                            innerPadding
                        )
                }
                .fillMaxWidth()

            ,
            contentAlignment = Alignment.BottomCenter
        ){
            Column {
                Text(
                    text = " Hold to change group name" ,
                    fontSize = 17.sp ,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = modifier.height(20.dp))
                Row(modifier = modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()) {
                    Button(
                        onClick = { /*TODO*/ } ,
                        shape = RoundedCornerShape(5.dp) ,
                        modifier = modifier
                            .width(170.dp)
                            .height(60.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.group_add_fill0_wght400_grad0_opsz24) ,
                            contentDescription = null ,
                            modifier = Modifier.size(40.dp),
                            tint = Color.Black
                        )

                        Spacer(modifier = modifier.width(10.dp))

                        Text(
                            text = "Add Group" ,
                            fontSize = 15.sp ,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = modifier.width(15.dp))

                    Button(
                        onClick = { /*TODO*/ } ,
                        shape = RoundedCornerShape(5.dp) ,
                        modifier = modifier
                            .width(170.dp)
                            .height(60.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.group_remove_fill0_wght400_grad0_opsz24) ,
                            contentDescription = null ,
                            modifier = Modifier.size(40.dp),
                            tint = Color.Black
                        )

                        Spacer(modifier = modifier.width(10.dp))

                        Text(
                            text = "Remove Group" ,
                            fontSize = 15.sp ,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}