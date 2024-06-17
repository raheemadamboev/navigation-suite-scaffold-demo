package xyz.teamgravity.navigationsuitescaffolddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import xyz.teamgravity.navigationsuitescaffolddemo.ui.theme.NavigationSuiteScaffoldDemoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationSuiteScaffoldDemoTheme {

            }
        }
    }
}