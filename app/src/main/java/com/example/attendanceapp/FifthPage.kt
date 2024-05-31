package com.example.attendanceapp

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.util.Calendar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import com.example.attendanceapp.DataBase.AppViewModel
import com.example.attendanceapp.DataBase.Groups
import java.util.*


@Composable
fun RecordAttdd(navController : NavHostController ,viewModel : AppViewModel ,modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(false) }
    val groups by viewModel.GetallGroupes().observeAsState(initial = emptyList())
    val selectedGroups = remember { mutableStateListOf<Groups>() }

    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf("") }

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
        }, year, month, day
    )
    Scaffold(
        topBar = { TopAppBar("Record Attendance") }
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
            contentAlignment = Alignment.Center
        ) {
            Column() {
                Button(
                    onClick = {
                        datePickerDialog.show()
                    } ,
                    shape = RoundedCornerShape(5.dp) ,
                    modifier = modifier
                        .width(250.dp)
                        .height(70.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.calendar_month_fill0_wght400_grad0_opsz24) ,
                        contentDescription = null ,
                        modifier = Modifier.size(50.dp),
                        tint = Color.Black
                    )

                    Spacer(modifier = modifier.width(10.dp))

                    Text(
                        text = "Session Date " ,
                        fontSize = 21.sp ,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = modifier.height(35.dp))

                Button(
                    onClick = {
                        showDialog = true
                    } ,
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
                        text = "Select Group" ,
                        fontSize = 21.sp ,
                        fontWeight = FontWeight.Bold
                    )
                }
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        confirmButton = {
                            Button(onClick = { showDialog = false }) {
                                Text("Close")
                            }
                        },
                        title = {
                            Text("Groups")
                        },
                        text = {
                            if (groups.isEmpty()) {
                                Text("No groups available")
                            } else {
                                LazyColumn {
                                    items(groups) { group ->
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(vertical = 4.dp)
                                        ) {
                                            Checkbox(
                                                checked = selectedGroups.contains(group),
                                                onCheckedChange = { isChecked ->
                                                    if (isChecked) {
                                                        selectedGroups.add(group)
                                                    } else {
                                                        selectedGroups.remove(group)
                                                    }
                                                }
                                            )
                                            Text(
                                                text = "Group ${group.GroupeNumber} (ID: ${group.GroupeId})",
                                                modifier = Modifier.padding(start = 8.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    )
                }


            }

        }

        Box (
            modifier = Modifier
                .fillMaxSize()
                .run {
                    fillMaxSize()
                        .padding(
                            innerPadding
                        )
                }
                .padding(bottom = 120.dp) ,
            contentAlignment = Alignment.BottomCenter
        ){
            Button(
                onClick = {
                    navController.navigate("8")
                } ,

                shape = RoundedCornerShape(5.dp) ,
                modifier = modifier
                    .width(250.dp)
                    .height(70.dp) ,

                ) {
                Text(
                    text = "Take Attendance" ,
                    fontSize = 21.sp ,
                    fontWeight = FontWeight.Bold
                )

            }
        }
    }
}


