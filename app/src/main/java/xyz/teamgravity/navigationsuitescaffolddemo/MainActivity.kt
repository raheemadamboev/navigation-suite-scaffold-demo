package xyz.teamgravity.navigationsuitescaffolddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.window.core.layout.WindowWidthSizeClass
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import xyz.teamgravity.navigationsuitescaffolddemo.ui.theme.NavigationSuiteScaffoldDemoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationSuiteScaffoldDemoTheme {
                val window = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
                var selectedNavigation by rememberSaveable { mutableStateOf(Navigation.Home) }

                NavigationSuiteScaffold(
                    navigationSuiteItems = {
                        Navigation.get().forEach { navigation ->
                            item(
                                selected = selectedNavigation == navigation,
                                onClick = {
                                    selectedNavigation = navigation
                                },
                                icon = {
                                    Icon(
                                        imageVector = navigation.icon,
                                        contentDescription = null
                                    )
                                },
                                label = {
                                    Text(
                                        text = stringResource(id = navigation.title)
                                    )
                                }
                            )
                        }
                    },
                    layoutType = if (window == WindowWidthSizeClass.EXPANDED) NavigationSuiteType.NavigationDrawer
                    else NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(currentWindowAdaptiveInfo()),
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = stringResource(id = selectedNavigation.title)
                        )
                    }
                }
            }
        }
    }

    enum class Navigation(
        @StringRes val title: Int,
        val icon: ImageVector
    ) {
        Home(
            title = R.string.home,
            icon = Icons.Default.Home,
        ),
        Search(
            title = R.string.search,
            icon = Icons.Default.Search
        ),
        Settings(
            title = R.string.settings,
            icon = Icons.Default.Settings
        );

        companion object {
            fun get(): ImmutableList<Navigation> {
                return entries.toImmutableList()
            }
        }
    }
}