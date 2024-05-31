package com.example.attendanceapp


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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.attendanceapp.DataBase.AppViewModel
import com.example.attendanceapp.DataBase.Students


@OptIn(ExperimentalMaterial3Api::class)
@Composable
 fun PageGroupee(navController : NavHostController, viewModel: AppViewModel, modifier: Modifier = Modifier) {
    val studentName = remember { mutableStateOf("") }
    var textfieldstatee by remember {
        mutableStateOf("")
    }
    val openDiloge = remember {
        mutableStateOf(false)
    }

    val emty by remember {
        mutableStateOf("")
    }


    val selectedGroup by viewModel.selectedGroup.collectAsState()

    val Students by viewModel.getStudentsByGroupId(0).observeAsState(initial = emptyList())
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
            if (Students.isEmpty()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .padding(16.dp))
                {
                    Text(text = "no student available")
                }
            }else{
                LazyColumn (contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)){
                    items(Students){
                        Card(
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        ) {
                            androidx.compose.material3.ListItem(headlineContent = {
                                Row (
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ){
                                    Text(
                                        it.studentName,
                                        fontSize = 24.sp,
                                        modifier= Modifier.padding(14.dp)
                                    )
                                    Spacer(modifier = modifier.width(160.dp))
                                    IconButton(onClick = {
                                        viewModel.delete(it)
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.delete_24dp) ,
                                            contentDescription = null,
                                            modifier = Modifier.size(50.dp),
                                            tint = Color.Black
                                        )
                                    }
                                }
                            })
                        }
                    }
                }
            }
        }
        if(openDiloge.value){
            AlertDialog(
                onDismissRequest = { openDiloge.value = false },
                confirmButton = {
                    Button(onClick = {
                        viewModel.insert(Students(studentName = textfieldstatee, StudentID = 0, groupeId = 0))
                        openDiloge.value = false
                        textfieldstatee = emty
                    }, enabled = textfieldstatee!=""
                    ) {
                        Text(text = "ADD")
                    }
                },
                dismissButton ={
                    Button(onClick = {
                        openDiloge.value = false
                        textfieldstatee = emty
                    }
                    ) {
                        Text(text = "Cancel")
                    }
                } ,
                title = {
                    Text(text="Entre the name of student",
                        textAlign = TextAlign.Center ,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier
                            .padding(top = 15.dp)
                            .fillMaxWidth() ,
                        color = Color.Black
                    )
                },
                text = {
                    TextField(
                        value = textfieldstatee,
                        onValueChange = { textfieldstatee = it },
                        label = {
                            Text(text = "Student name ")
                        }
                    )
                }
            )
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
                .fillMaxWidth(),
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
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(20.dp)

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.group_add_fill0_wght400_grad0_opsz24) ,
                            contentDescription = null ,
                            modifier = Modifier.size(40.dp),
                            tint = Color.Black
                        )

                        Spacer(modifier = modifier.width(10.dp))

                        Text(
                            text = "Add Student" ,
                            fontSize = 15.sp ,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
