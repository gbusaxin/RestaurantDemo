package org.gbu.restaurant

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import org.gbu.restaurant.decompose.root.RestaurantRootImpl
import org.gbu.restaurant.koin.initKoinApp
import org.koin.dsl.module

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

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

        setContent {
            RestaurantApplication(root)
        }
    }
}