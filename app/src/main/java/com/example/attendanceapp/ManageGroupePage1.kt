package com.example.attendanceapp


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.attendanceapp.DataBase.AppViewModel
import com.example.attendanceapp.DataBase.Groups
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
    var selectedGroup by remember { mutableStateOf<Groups?>(null) }
    val groups by viewModel.group.collectAsState(initial = emptyList())
    var expanded by remember { mutableStateOf(false)}
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }

    var text : String = ""
    val Students by viewModel.getAllStudents().observeAsState(initial = emptyList())

    val openDeleteDialog = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    var selectedStudent by remember { mutableStateOf<Students?>(null) }
    val openLongPressDialog = remember { mutableStateOf(false) }


    Scaffold(
        topBar = { TopAppBar("Manage Group ")}
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .size(635.dp)
                .fillMaxSize()
                .run {
                    fillMaxSize()
                        .padding(
                            innerPadding
                        )
                } ,
            contentAlignment = Alignment.TopCenter
        ) {
            Column() {
                Row(
                    modifier.padding(start = 10.dp, top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.person_search_24dp),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp),
                        tint = Color.Black
                    )
                    TextField(
                        value = studentName.value,
                        onValueChange = { studentName.value = it },
                        label = { Text("Search Student") },
                        modifier = Modifier
                            .width(290.dp)
                            .background(color = Color(0xFFF3FBFF)),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = { /* handle action */ })
                    )


                }
                val filteredStudents = if (studentName.value.isEmpty()) {
                    Students
                } else {
                    Students.filter { it.studentName.contains(studentName.value, ignoreCase = true) }
                }

                Spacer(modifier = modifier.height(10.dp))
                if (filteredStudents.isEmpty()) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .padding(16.dp)
                    )
                    {
                        Text(text = "no student available")
                    }
                } else {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(filteredStudents){student->
                            Card(
                                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                                modifier = Modifier.pointerInput(Unit) {
                                    detectTapGestures(
                                        onLongPress = {
                                            selectedStudent = student
                                            openLongPressDialog.value = true
                                        }
                                    )
                                }
                            ) {
                                androidx.compose.material3.ListItem(headlineContent = {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = student.studentName,
                                            fontSize = 17.sp,
                                            modifier = Modifier
                                                .padding(14.dp)
                                                .width(220.dp)
                                        )

                                        Spacer(modifier = modifier.width(20.dp))

                                        IconButton(onClick = {
                                            openDeleteDialog.value = true
                                        }) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.delete_24dp),
                                                contentDescription = null,
                                                modifier = Modifier.size(50.dp),
                                                tint = Color.Black
                                            )
                                            if (openDeleteDialog.value) {
                                                AlertDialog(
                                                    onDismissRequest = {
                                                        openDeleteDialog.value = false
                                                    },
                                                    title = {
                                                        Text(
                                                            text = "Delete Student",
                                                            textAlign = TextAlign.Center,
                                                            fontWeight = FontWeight.Bold,
                                                            modifier = Modifier
                                                                .padding(top = 15.dp)
                                                                .fillMaxWidth(),
                                                            color = Color.Black
                                                        )
                                                    },
                                                    text = {
                                                        Text(
                                                            text = "Are you sure you want to delete this student?",
                                                            textAlign = TextAlign.Center,
                                                            modifier = Modifier.fillMaxWidth(),
                                                            color = Color.Black
                                                        )
                                                    },
                                                    confirmButton = {
                                                        Button(
                                                            onClick = {
                                                                // Delete the group
                                                                viewModel.delete(student)
                                                                openDeleteDialog.value = false
                                                                Toast.makeText(
                                                                    context,
                                                                    "Student removed successfully",
                                                                    Toast.LENGTH_SHORT
                                                                ).show()
                                                            }
                                                        ) {
                                                            Text("Yes")
                                                        }
                                                    },
                                                    dismissButton = {
                                                        Button(
                                                            onClick = {
                                                                openDeleteDialog.value = false
                                                            }
                                                        ) {
                                                            Text("No")
                                                        }
                                                    }
                                                )
                                            }
                                        }
                                    }
                                })
                            }
                        }

                    }
                }
            }
        }
        if (openLongPressDialog.value && selectedStudent != null) {
            var newStudentName by remember { mutableStateOf(selectedStudent?.studentName ?: "") }

            AlertDialog(
                onDismissRequest = { openLongPressDialog.value = false },
                confirmButton = {
                    Button(onClick = {
                        selectedStudent?.let { student ->
                            viewModel.update(
                                Students(
                                    StudentID = student.StudentID,
                                    studentName = newStudentName,
                                    groupeId = student.groupeId
                                )
                            )
                            openLongPressDialog.value=false
                            Toast.makeText(
                                context,
                                "Student name updated successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }, enabled = newStudentName.isNotEmpty()
                    ) {
                        Text(text = "Update")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        openLongPressDialog.value = false
                    }) {
                        Text(text = "Cancel")
                    }
                },
                title = {
                    Text(
                        text = "Change Student Name",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier
                            .padding(top = 15.dp)
                            .fillMaxWidth(),
                        color = Color.Black
                    )
                },
                text = {
                    TextField(
                        value = newStudentName,
                        onValueChange = { newStudentName = it },
                        label = {
                            Text(text = "New Student Name")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        )
                    )
                }
            )
        }
        if(openDiloge.value){
            AlertDialog(
                onDismissRequest = { openDiloge.value = false },
                confirmButton = {
                    Button(onClick = {
                        if (selectedGroup!=null && textfieldstatee!=null) {
                            viewModel.insert(
                                Students(
                                    studentName = textfieldstatee,
                                    StudentID = 0,
                                    groupeId = selectedGroup!!.GroupeId
                                )
                            )
                            text = selectedGroup!!.GroupeNumber.toString()
                            openDiloge.value = false
                            textfieldstatee = emty
                        }
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
                    Column {
                        TextField(
                            value = textfieldstatee,
                            onValueChange = { textfieldstatee = it },
                            label = {
                                Text(text = "Student name ")
                            }
                        )
                        Spacer(modifier =Modifier.height(30.dp))
                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = { expanded = !expanded },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            TextField(
                                value = textFieldValue,
                                onValueChange = { textFieldValue = it },
                                label = { Text("Group") },
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = expanded
                                    )
                                },
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth()
                            )
                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier.width(150.dp)
                            ) {
                                groups.forEach { group ->
                                    DropdownMenuItem(
                                        text = { Text("Group ${group.GroupeNumber}") },
                                        onClick = {
                                            selectedGroup = group
                                            textFieldValue =
                                                TextFieldValue("Group ${group.GroupeNumber}")
                                            expanded = false
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }
                },

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
                Spacer(modifier = modifier.height(0.dp))
                Row(modifier = modifier

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
                            painter = painterResource(R.drawable.person_add_fill0_wght400_grad0_opsz24) ,
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
