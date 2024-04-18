package com.example.attendanceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.attendanceapp.ui.theme.AttendanceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AttendanceAppTheme {
                // A surface container using the 'background' color from the theme
                installSplashScreen()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController ,
                        startDestination = "1"
                    ){
                        composable("1"){
                            Houmepg(navController=navController)
                        }
                        composable("2"){
                            MangeGr(navController=navController)
                        }
                        composable("3"){
                            RecordAttd(navController=navController)
                        }
                        composable("4"){
                            ManageAttendace(navController=navController)
                        }
                        composable("5"){
                            AttendanceHistory(navController=navController)
                        }
                        composable("6"){
                            RecordAttd(navController=navController)
                        }
                        composable("7"){
                            AttendanceHistory(navController=navController)
                        }
                        composable("8"){
                            TakeAttn(navController=navController)
                        }
                        composable("9"){
                            HistorySession(navController=navController)
                        }
                        composable("10"){
                            HistoryStudent(navController=navController)
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AttendanceAppTheme {

    }
}