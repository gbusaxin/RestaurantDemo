package org.gbu.restaurant.business.koin

import androidx.compose.runtime.compositionLocalOf
import org.gbu.restaurant.business.usecase.GetMenuDetailsUseCase
import org.gbu.restaurant.ui.screens.menudetail.viewmodel.MenuDetailsViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.koinApplication
import org.koin.dsl.module

fun initKoinApp(
    platformModules: List<Module> = listOf()
): KoinApplication {

    val koinApplication = koinApplication {
        modules(
            listOf(
                module {
                    includes(platformModules)
                    single<MenuDetailsViewModel> { MenuDetailsViewModel(getMenuDetailUseCase = get()) }
                    single<GetMenuDetailsUseCase> { GetMenuDetailsUseCase() }
                }
            )
        )
        createEagerInstances()
    }
    return startKoin(koinApplication)
}

val LocalKoinApplication = compositionLocalOf {
    initKoinApp()
}