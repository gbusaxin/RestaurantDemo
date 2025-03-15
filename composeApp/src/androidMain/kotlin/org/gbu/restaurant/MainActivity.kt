package org.gbu.restaurant

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import org.gbu.restaurant.decompose.root.RestaurantRoot
import org.gbu.restaurant.decompose.root.RestaurantRootImpl
import org.gbu.restaurant.business.koin.initKoinApp
import org.koin.dsl.module

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val koinApplication = initKoinApp(
            listOf(
                module {
                    single<Context> { applicationContext }
                    single<Activity> { this@MainActivity }
                }
            )
        )
        val root = RestaurantRootImpl(
            componentContext = defaultComponentContext(),
            koinApplication = koinApplication
        )

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MainView(root = root)
        }
    }
}