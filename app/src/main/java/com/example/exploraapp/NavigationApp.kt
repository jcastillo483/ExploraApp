package com.example.exploraapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exploraapp.ui.elements.AddTouristicPlaceScreen
import com.example.exploraapp.ui.elements.HomeScreen
import com.example.exploraapp.ui.elements.LoginScreen
import com.example.exploraapp.ui.elements.RegisterScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun NavigationApp() {
    val myNavController = rememberNavController()
    var myStartDestination: String

    val auth = Firebase.auth
    val currentUser = auth.currentUser

    if(currentUser != null) { myStartDestination = "home"}
    else { myStartDestination = "login" }


    NavHost(
        navController = myNavController,
        startDestination = myStartDestination,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = "login") {
            LoginScreen(
                onLoginSuccess = {
                    myNavController.navigate("home")
                },
                onNavigateToRegister = {
                    myNavController.navigate("register")
                }
            )
        }
        composable(route = "register") {
            RegisterScreen(
                onRegisterSuccess = { myNavController.navigate("home") },
                onNavigateToLogin = { myNavController.navigate("login") },
                onBackClick = {
                    myNavController.popBackStack()
                }
            )
        }

        composable(route = "home") {
            HomeScreen(onClickAddTouristicPlace =
                { myNavController.navigate("addTouristicPlace") })
        }

        composable(route = "addTouristicPlace") {
            AddTouristicPlaceScreen()
        }

    }
}