package com.example.attendanceapp


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.attendanceapp.DataBase.AppViewModel
import com.example.attendanceapp.DataBase.Groups


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryStudentt(navController : NavHostController, viewModel : AppViewModel, modifier: Modifier = Modifier) {
    val groups by viewModel.group.collectAsState(initial = emptyList())
    var expanded by remember { mutableStateOf(false) }
    var selectedGroup by remember { mutableStateOf<Groups?>(null) }
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }

    var expandedd by remember { mutableStateOf(false) }
    var textFieldValuee by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = { TopAppBar("History per Student") }
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
                        label = { Text("Student") },
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
                        if (selectedGroup != null) {
                            val student by viewModel.getStudentsByGroupId(selectedGroup!!.GroupeId)
                                .observeAsState(initial = emptyList())
                            student.forEach { it ->
                                DropdownMenuItem(
                                    text = { Text(it.studentName) },
                                    onClick = {
                                        textFieldValuee = TextFieldValue(it.studentName)
                                        expandedd = false
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
