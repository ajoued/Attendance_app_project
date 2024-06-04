package com.example.attendanceapp


import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.attendanceapp.DataBase.AppViewModel
import com.example.attendanceapp.DataBase.Groups
import com.example.attendanceapp.ui.theme.AttendanceAppTheme
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TakeAttnn(navController : NavHostController, viewModel : AppViewModel, modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(false) }
    val groups by viewModel.GetallGroupes().observeAsState(initial = emptyList())
    var selectedGroup by remember { mutableStateOf<Groups?>(null) }

    val Students by viewModel.getStudentsByGroupId(
        if (selectedGroup != null) selectedGroup!!.GroupeId else 0
    ).observeAsState(initial = emptyList())


    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf("") }
    var GroupSe by remember { mutableStateOf(0) }

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val uncheckedIcon = painterResource(R.drawable.radio_button_unchecked_black_24dp)
    val checkedIcon = painterResource(R.drawable.check_circle_outline_black_24dp)

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
        }, year, month, day
    )



    Scaffold(
        topBar = { TopAppBar("Taking Attendance") }
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
            if (Students.isEmpty()) {
                // Check if it's because no group is selected or there are no students
                if (selectedGroup == null) {
                    Text("Please select a group")
                } else {
                    Text("No students in this group")
                }
            }else{
                LazyColumn (contentPadding = PaddingValues(16.dp) ,verticalArrangement = Arrangement.spacedBy(16.dp)){

                    items(Students){
                        val cardIndex = Students.indexOf(it)
                        var icon by remember { mutableStateOf(uncheckedIcon)
                        }
                        Card(
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    icon = if (icon == uncheckedIcon) checkedIcon else uncheckedIcon
                                }
                        ) {
                            ListItem(headlineContent = {
                                Row (
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ){
                                    Text(
                                        it.studentName,
                                        fontSize = 24.sp,
                                        modifier= Modifier.padding(14.dp).width(220.dp)
                                    )
                                    Spacer(modifier = modifier.width(20.dp))
                                    Icon(
                                        painter = icon,
                                        contentDescription = "Toggle Icon",
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
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
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ){
                Column (){
                    Row (){
                        Text(text = "Date selected : $selectedDate")
                        Spacer(modifier = modifier.width(20.dp))
                        if (selectedGroup!=null){
                            Text(text = "Group selected : $GroupSe")
                        }else{
                            Text(text = "Group : ")
                        }
                    }
                    Row(
                        modifier
                            .padding(bottom = 20.dp, start = 7.dp, end = 7.dp)
                            .fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                datePickerDialog.show()
                                      },
                            shape = RoundedCornerShape(5.dp),
                            modifier = modifier
                                .width(160.dp)
                                .height(70.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.calendar_month_fill0_wght400_grad0_opsz24),
                                contentDescription = null,
                                modifier = Modifier.size(45.dp),
                                tint = Color.Black
                            )


                            Spacer(modifier = modifier.width(8.dp))

                            Text(
                                text = "Date ",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = modifier.width(20.dp))

                        Button(
                            onClick = {
                                showDialog = true
                                      },
                            shape = RoundedCornerShape(5.dp),
                            modifier = modifier
                                .width(160.dp)
                                .height(70.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.home_icon_team_tr),
                                contentDescription = null,
                                modifier = Modifier.size(45.dp),
                                tint = Color.Black
                            )
                            Spacer(modifier = modifier.width(8.dp))

                            Text(
                                text = "Group",
                                fontSize = 15.sp,
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
                                                                    RadioButton(
                                                                        selected = selectedGroup == group,
                                                                        onClick = {
                                                                            selectedGroup = group
                                                                            GroupSe = selectedGroup!!.GroupeNumber
                                                                        }
                                                                    )
                                                                    Text(
                                                                        text = "Group ${group.GroupeNumber}",
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
                            }

                        }
                    }
                }
