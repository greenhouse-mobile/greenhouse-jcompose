package com.integradis.greenhouse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.integradis.greenhouse.screens.feature_main.GreenhouseMainScreen
import com.integradis.greenhouse.ui.theme.GreenhouseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            GreenhouseTheme {
                Box(
                    modifier = Modifier.fillMaxSize().background(Color(0xFFF8F7F6))
                ){
                    GreenhouseMainScreen()
                }
            }
        }
    }
}
