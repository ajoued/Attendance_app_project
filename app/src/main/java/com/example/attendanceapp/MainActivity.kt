package com.example.attendanceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.attendanceapp.Importent.HistorySession
import com.example.attendanceapp.Importent.HistoryStudent
import com.example.attendanceapp.Importent.MangeGr
import com.example.attendanceapp.Importent.PageGroupe
import com.example.attendanceapp.Importent.TakeAttn
import com.example.attendanceapp.ui.theme.AttendanceAppTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AttendanceAppTheme {
                // Install Splash Screen
                installSplashScreen()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val showSplash = remember { mutableStateOf(true) }

                    LaunchedEffect(Unit) {
                        delay(3000) // Delay for 3 seconds
                        showSplash.value = false
                    }

                    if (showSplash.value) {
                        SplashScreen()
                    } else {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = "1"
                        ) {
                            composable("1") {
                                Houmepg(navController = navController)
                            }
                            composable("2") {
                                MangeGr(navController = navController)
                            }
                            composable("4") {
                                ManageAttendace(navController = navController)
                            }
                            composable("5") {
                                AttendanceHistory(navController = navController)
                            }
                            composable("7") {
                                AttendanceHistory(navController = navController)
                            }
                            composable("8") {
                                TakeAttn(navController = navController)
                            }
                            composable("9") {
                                HistorySession(navController = navController)
                            }
                            composable("10") {
                                HistoryStudent(navController = navController)
                            }
                            composable("11") {
                                PageGroupe(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            ,
        contentAlignment = Alignment.Center
    ) {
            Image(
                painter = painterResource(id = R.drawable._024),
                contentDescription = "App Logo",
                modifier = Modifier.size(128.dp)
            )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AttendanceAppTheme {

    }
}