package com.borealnetwork.ryobimeter

import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.borealnetwork.ryobimeter.di.koinInjection
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.initialize
import org.koin.android.ext.koin.androidContext

class RyobiMeterApp : Application() {
    companion object Companion {
        lateinit var INSTANCE: RyobiMeterApp
    }

    override fun onCreate() {
        super.onCreate()
        koinInjection {
            androidContext(this@RyobiMeterApp)
        }
        Firebase.initialize(this)
        FirebaseApp.initializeApp(this)
        INSTANCE = this
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) {
            enableEdgeToEdge()
        }

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            App(navController = navController)
        }
    }
}
