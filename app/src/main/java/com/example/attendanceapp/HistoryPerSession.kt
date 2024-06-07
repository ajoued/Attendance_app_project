package com.example.attendanceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.attendanceapp.DataBase.AppViewModel
import com.example.attendanceapp.DataBase.Groups
import com.example.attendanceapp.ui.theme.AttendanceAppTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorySessionn(navController : NavHostController,viewModel : AppViewModel ,modifier: Modifier = Modifier) {
    val groups by viewModel.group.collectAsState(initial = emptyList())
    var expanded by remember { mutableStateOf(false) }
    var selectedGroup by remember { mutableStateOf<Groups?>(null) }
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }

    val dates by viewModel.getAllDates().observeAsState(initial = emptyList())
    var expandedd by remember { mutableStateOf(false) }
    var textFieldValuee by remember { mutableStateOf(TextFieldValue("")) }
    var selectedDate by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = { TopAppBar("History per session") }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .run {
                    fillMaxSize()
                        .padding(
                            innerPadding
                        )
                }
                .padding(bottom = 120.dp) ,
            contentAlignment = Alignment.TopCenter
        ) {
            Row {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                    modifier=Modifier.width(150.dp)
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
                        modifier=Modifier.width(150.dp)
                    ) {
                        groups.forEach { group ->
                            DropdownMenuItem(
                                text = { Text("Group ${group.GroupeNumber}") },
                                onClick = {
                                    selectedGroup = group
                                    textFieldValue = TextFieldValue("Group ${group.GroupeNumber}")
                                    expanded = false
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
                Spacer(modifier = modifier.width(10.dp))
                ExposedDropdownMenuBox(
                    expanded = expandedd,
                    onExpandedChange = { expandedd = !expandedd },
                    modifier = Modifier.width(150.dp)
                ) {
                    TextField(
                        value = textFieldValuee,
                        onValueChange = { textFieldValuee = it },
                        label = { Text("Session") },
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedd)
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedd,
                        onDismissRequest = { expandedd = false },
                        modifier = Modifier.width(150.dp)
                    ) {
                        dates.forEach { date ->
                            DropdownMenuItem(
                                text = { Text(date) },
                                onClick = {
                                    textFieldValuee = TextFieldValue(date)
                                    expandedd = false
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
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
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ){

        }
    }
}


