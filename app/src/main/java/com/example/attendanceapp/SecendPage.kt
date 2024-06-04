package com.example.attendanceapp

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavHostController
import com.example.attendanceapp.DataBase.AppViewModel
import com.example.attendanceapp.DataBase.AttendanceAppDatabase
import com.example.attendanceapp.DataBase.Groups
import com.example.attendanceapp.DataBase.Students
import kotlinx.coroutines.flow.MutableStateFlow
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MangeGrr (navController : NavHostController, viewModel: AppViewModel, modifier: Modifier = Modifier) {

    var textfieldstate by remember {
        mutableStateOf("")
    }

    val openDilog = remember {
        mutableStateOf(false)
    }

    val openDeleteDialog = remember {
        mutableStateOf(false)
    }

    val emty by remember {
        mutableStateOf("")
    }

    val groupesss by viewModel.group.collectAsState(initial = emptyList())
    val context = LocalContext.current
    Scaffold(
        topBar = { TopAppBar("Manage Groups") }
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
            if (groupesss.isEmpty()){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .padding(16.dp))
                {
                    Text(text = "no Group available")
                }
            }else{
                LazyColumn (contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)){
                    items(groupesss){Groups ->
                        Card(
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                        ) {
                            androidx.compose.material3.ListItem(headlineContent = {
                                Row (
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                    ){
                                    Text(
                                        "Group "+Groups.GroupeNumber,
                                        fontSize = 24.sp,
                                        modifier= Modifier.padding(14.dp)
                                    )
                                    Spacer(modifier = modifier.width(160.dp))
                                    IconButton(onClick = {
                                        openDeleteDialog.value = true


                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.delete_24dp) ,
                                            contentDescription = null,
                                            modifier = Modifier.size(50.dp),
                                            tint = Color.Black
                                        )
                                        if (openDeleteDialog.value) {
                                            AlertDialog(
                                                onDismissRequest = { openDeleteDialog.value = false },
                                                title = {
                                                    Text(
                                                        text = "Delete Group",
                                                        textAlign = TextAlign.Center,
                                                        fontWeight = FontWeight.Bold,
                                                        modifier = Modifier.padding(top = 15.dp).fillMaxWidth(),
                                                        color = Color.Black
                                                    )
                                                },
                                                text = {
                                                    Text(
                                                        text = "Are you sure you want to delete this group?",
                                                        textAlign = TextAlign.Center,
                                                        modifier = Modifier.fillMaxWidth(),
                                                        color = Color.Black
                                                    )
                                                },
                                                confirmButton = {
                                                    Button(
                                                        onClick = {
                                                            // Delete the group
                                                            viewModel.delete(Groups)
                                                            openDeleteDialog.value = false
                                                            Toast.makeText(context,  "Group removed successfully", Toast.LENGTH_SHORT). show( )
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


        if(openDilog.value){
            AlertDialog(
                onDismissRequest = { openDilog.value = false },
                confirmButton = {
                    Button(onClick = {

                        val existingGroup = groupesss.find { it.GroupeNumber == textfieldstate.toInt() }
                        if (existingGroup == null) {
                            viewModel.insert(
                                Groups(
                                    GroupeId = 0,
                                    GroupeNumber = textfieldstate.toInt()
                                )
                            )
                            openDilog.value = false
                            textfieldstate = emty
                        }else{
                            Toast.makeText(context,  "Group number already exists", Toast.LENGTH_SHORT). show( )
                        }
                    }, enabled = textfieldstate!=""
                    ) {
                        Text(text = "ADD")
                    }
                },
                dismissButton ={
                    Button(onClick = {
                        openDilog.value = false
                        textfieldstate = emty
                    }
                    ) {
                        Text(text = "Cancel")
                    }
                } ,
                title = {
                    Text(text="Entre the group number",
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
                        value = textfieldstate,
                        onValueChange = { textfieldstate = it },
                        label = {
                            Text(text = "Group number ")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
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
                    text = " Hold to change group name" ,
                    fontSize = 17.sp ,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = modifier.height(20.dp))
                    Button(
                        onClick = {
                            openDilog.value=true
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
                            text = "Add Group" ,
                            fontSize = 15.sp ,
                            fontWeight = FontWeight.Bold
                        )
                    }
            }
        }
    }
}

