package com.example.attendanceapp


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController


@Composable
fun PageGroupe( navController : NavHostController ,modifier: Modifier = Modifier) {
    var textfieldstatee by remember {
        mutableStateOf("")
    }
    val openDiloge = remember {
        mutableStateOf(false)
    }
    val GroupListe = remember {
        mutableListOf<String>()
    }
    Scaffold(
        topBar = { TopAppBar("Manage Group ")}
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .size(600.dp)
                .fillMaxSize()
                .run {
                    fillMaxSize()
                        .padding(
                            innerPadding
                        )
                } ,
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn (contentPadding = PaddingValues(16.dp) , verticalArrangement = Arrangement.spacedBy(16.dp)){
                items(GroupListe){ data->
                    Card(elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)) {
                        ListItem(headlineContent = {
                            Text(text = data, style = TextStyle(fontSize = 18.sp, color = Color.Black) ,modifier=modifier.padding(start = 20.dp))
                        })
                    }
                }
            }
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
                    text = " Hold to change student name" ,
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
                        onClick = {
                            openDiloge.value=true
                        } ,
                        shape = RoundedCornerShape(5.dp) ,
                        modifier = modifier
                            .width(170.dp)
                            .height(60.dp)
                            .padding(start = 10.dp)

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.person_add_fill0_wght400_grad0_opsz24) ,
                            contentDescription = null ,
                            modifier = Modifier.size(40.dp),
                            tint = Color.Black
                        )

                        Spacer(modifier = modifier.width(10.dp))

                        Text(
                            text = "Add student" ,
                            fontSize = 15.sp ,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    if(openDiloge.value){
                        Dialog(onDismissRequest = { openDiloge.value = false }) {
                            Box(modifier = modifier
                                .height(200.dp),
                                contentAlignment = Alignment.BottomCenter

                            ){
                                Column(modifier) {
                                    Box(
                                        modifier= modifier
                                            .height(250.dp)
                                            .background(
                                                color = Color(0xFFF3FBFF) ,
                                                shape = RoundedCornerShape(25.dp)
                                            ),
                                        contentAlignment = Alignment.BottomCenter

                                    ) {
                                        Column(
                                            modifier=modifier
                                                .padding(16.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = "Entre the name of student" ,
                                                textAlign = TextAlign.Center ,
                                                modifier = modifier
                                                    .padding(top = 15.dp)
                                                    .fillMaxWidth() ,
                                                color = Color.Black
                                            )
                                            Spacer(modifier = modifier.height(20.dp))
                                            TextField(
                                                value = textfieldstatee,
                                                onValueChange = { textfieldstatee = it },
                                                label = {
                                                    Text(text = "Student name ")
                                                }
                                            )
                                            Spacer(modifier = modifier.height(20.dp))
                                            Row {
                                                Button(
                                                    onClick = {
                                                        GroupListe.add(textfieldstatee)
                                                        textfieldstatee=""
                                                    }, enabled = textfieldstatee!="",
                                                    shape = RoundedCornerShape(5.dp) ,
                                                    modifier = modifier
                                                        .width(100.dp)
                                                        .height(50.dp)
                                                ) {
                                                    Text(
                                                        text = "ADD" ,
                                                        color = Color.Black ,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                }
                                                Spacer(modifier = modifier.width(30.dp))
                                                Button(
                                                    onClick = {
                                                        openDiloge.value=false
                                                        textfieldstatee=""
                                                    },
                                                    shape = RoundedCornerShape(5.dp) ,
                                                    modifier = modifier
                                                        .width(100.dp)
                                                        .height(50.dp)
                                                ){
                                                    Text(
                                                        text = "Close" ,
                                                        color = Color.Black ,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = modifier.width(15.dp))

                    Button(
                        onClick = { /*TODO*/ } ,
                        shape = RoundedCornerShape(5.dp) ,
                        modifier = modifier
                            .width(170.dp)
                            .height(60.dp)
                            .padding(end = 10.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.person_remove_fill0_wght400_grad0_opsz24) ,
                            contentDescription = null ,
                            modifier = Modifier.size(40.dp),
                            tint = Color.Black
                        )

                        Spacer(modifier = modifier.width(10.dp))

                        Text(
                            text = "Remove student" ,
                            fontSize = 15.sp ,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

    }
}

