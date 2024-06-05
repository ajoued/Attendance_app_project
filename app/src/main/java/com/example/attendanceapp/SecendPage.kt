package com.example.attendanceapp

import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
fun MangeGrr(navController: NavHostController, viewModel: AppViewModel, modifier: Modifier = Modifier) {

    var textfieldState by remember {
        mutableStateOf("")
    }

    val openDialog = remember {
        mutableStateOf(false)
    }

    val openDeleteDialog = remember {
        mutableStateOf(false)
    }

    val empty by remember {
        mutableStateOf("")
    }

    val groups by viewModel.group.collectAsState(initial = emptyList())
    val context = LocalContext.current

    // State for long press dialog
    var selectedGroup by remember { mutableStateOf<Groups?>(null) }
    val openLongPressDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar("Manage Groups") }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .size(635.dp)
                .fillMaxSize()
                .run {
                    fillMaxSize()
                        .padding(innerPadding)
                },
            contentAlignment = Alignment.TopCenter
        ) {
            if (groups.isEmpty()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "No Group available")
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(groups) { group ->
                        Card(
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                            modifier = Modifier.pointerInput(Unit) {
                                detectTapGestures(
                                    onLongPress = {
                                        selectedGroup = group
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
                                        "Group " + group.GroupeNumber,
                                        fontSize = 24.sp,
                                        modifier = Modifier
                                            .padding(14.dp)
                                            .width(220.dp)
                                    )

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
                                                onDismissRequest = { openDeleteDialog.value = false },
                                                title = {
                                                    Text(
                                                        text = "Delete Group",
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
                                                            viewModel.delete(group)
                                                            openDeleteDialog.value = false
                                                            Toast.makeText(
                                                                context,
                                                                "Group removed successfully",
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

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = { openDialog.value = false },
                confirmButton = {
                    Button(onClick = {
                        val existingGroup = groups.find { it.GroupeNumber == textfieldState.toInt() }
                        if (existingGroup == null) {
                            viewModel.insert(
                                Groups(
                                    GroupeId = 0,
                                    GroupeNumber = textfieldState.toInt()
                                )
                            )
                            openDialog.value = false
                            textfieldState = empty
                        } else {
                            Toast.makeText(
                                context,
                                "Group number already exists",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }, enabled = textfieldState != ""
                    ) {
                        Text(text = "ADD")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        openDialog.value = false
                        textfieldState = empty
                    }) {
                        Text(text = "Cancel")
                    }
                },
                title = {
                    Text(
                        text = "Enter the group number",
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
                        value = textfieldState,
                        onValueChange = { textfieldState = it },
                        label = {
                            Text(text = "Group number")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
            )
        }

        // Long Press Dialog
        if (openLongPressDialog.value) {
            var newGroupNumber by remember { mutableStateOf(selectedGroup?.GroupeNumber?.toString() ?: "") }

            AlertDialog(
                onDismissRequest = { openLongPressDialog.value = false },
                confirmButton = {
                    Button(onClick = {
                        selectedGroup?.let { group ->
                            val existingGroup = groups.find { it.GroupeNumber == newGroupNumber.toInt() }
                            if (existingGroup == null) {
                                viewModel.update(
                                    Groups(
                                        GroupeId = group.GroupeId,
                                        GroupeNumber = newGroupNumber.toInt()
                                    )
                                )
                                openLongPressDialog.value = false
                                Toast.makeText(context, "Group number updated successfully", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Group number already exists", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }, enabled = newGroupNumber.isNotEmpty()
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
                        text = "Change Group Number",
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
                        value = newGroupNumber,
                        onValueChange = { newGroupNumber = it },
                        label = {
                            Text(text = "New Group Number")
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
                        .padding(innerPadding)
                }
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column {
                Text(
                    text = "Hold to change group name",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = modifier.height(0.dp))
                Button(
                    onClick = {
                        openDialog.value = true
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(20.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.group_add_fill0_wght400_grad0_opsz24),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = Color.Black
                    )

                    Spacer(modifier = modifier.width(10.dp))

                    Text(
                        text = "Add Group",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
