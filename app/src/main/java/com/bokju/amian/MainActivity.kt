package com.bokju.amian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bokju.amian.navigation.AmianNavHost
import com.bokju.amian.ui.theme.AmianTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmianTheme {
                AmianNavHost()
            }
        }
    }
}