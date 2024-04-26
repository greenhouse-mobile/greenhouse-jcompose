package com.integradis.greenhouse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.integradis.greenhouse.feature_main.ui.main.GreenhouseMainScreen
import com.integradis.greenhouse.ui.theme.Background
import com.integradis.greenhouse.ui.theme.GreenhouseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
