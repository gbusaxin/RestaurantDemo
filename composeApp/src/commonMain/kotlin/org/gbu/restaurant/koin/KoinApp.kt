package org.gbu.restaurant.koin

import androidx.compose.runtime.compositionLocalOf
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.koinApplication
import org.koin.dsl.module

fun initKoinApp(
    platformModules: List<Module> = listOf()
) : KoinApplication{

    val koinApplication = koinApplication {
        modules(
            listOf(
                module { includes(platformModules) }
            )
        )
        createEagerInstances()
    }
    return startKoin(koinApplication)
}

val LocalKoinApplication = compositionLocalOf {
    initKoinApp()
}