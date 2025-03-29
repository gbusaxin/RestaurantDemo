package org.gbu.restaurant

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import org.gbu.restaurant.business.di.initKoinApp
import org.gbu.restaurant.decompose.root.RestaurantRootImpl
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
            ), context = application
        )
        val root = RestaurantRootImpl(
            componentContext = defaultComponentContext(),
            koinApplication = koinApplication
        )

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MainView(root = root, appContext = application)
        }
    }
}