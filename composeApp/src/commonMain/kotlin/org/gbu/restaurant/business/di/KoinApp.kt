package org.gbu.restaurant.business.di

import androidx.compose.runtime.compositionLocalOf
import org.gbu.restaurant.business.common.Context
import org.gbu.restaurant.business.core.AppDataStore
import org.gbu.restaurant.business.core.AppDataStoreManager
import org.gbu.restaurant.business.usecase.AddAddressUseCase
import org.gbu.restaurant.business.usecase.BuyProductUseCase
import org.gbu.restaurant.business.usecase.CartListUseCase
import org.gbu.restaurant.business.usecase.CheckTokenUseCase
import org.gbu.restaurant.business.usecase.GetAddressesUseCase
import org.gbu.restaurant.business.usecase.GetMenuDetailsUseCase
import org.gbu.restaurant.business.usecase.IsOnBoardedUseCase
import org.gbu.restaurant.business.usecase.LogoutUseCase
import org.gbu.restaurant.business.usecase.SetOnBoardingUseCase
import org.gbu.restaurant.ui.screens.add_address.viewmodel.AddAddressViewModel
import org.gbu.restaurant.ui.screens.address.viewmodel.AddressViewModel
import org.gbu.restaurant.ui.screens.cart.viewmodel.CartViewModel
import org.gbu.restaurant.ui.screens.checkout.viewmodel.CheckoutViewModel
import org.gbu.restaurant.ui.screens.menu_detail.viewmodel.MenuDetailsViewModel
import org.gbu.restaurant.ui.screens.on_boarding.viewmodel.OnBoardingViewModel
import org.gbu.restaurant.ui.screens.splash.viewmodel.SplashViewModel
import org.gbu.restaurant.ui.token_manager.TokenManager
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.koinApplication
import org.koin.dsl.module

fun initKoinApp(
    platformModules: List<Module> = listOf(),
    context: Context
): KoinApplication {

    val koinApplication = koinApplication {
        modules(
            listOf(
                module {
                    includes(platformModules)
                    single<MenuDetailsViewModel> { MenuDetailsViewModel(getMenuDetailUseCase = get()) }
                    single<GetMenuDetailsUseCase> { GetMenuDetailsUseCase() }
                    single<CartViewModel> { CartViewModel(cartListUseCase = get()) }
                    single<CartListUseCase> { CartListUseCase() }
                    single<CheckoutViewModel> {
                        CheckoutViewModel(
                            cartListUseCase = get(),
                            getAddressesUseCase = get(),
                            buyProductUseCase = get()
                        )
                    }
                    single<CartListUseCase> { CartListUseCase() }
                    single<GetAddressesUseCase> { GetAddressesUseCase() }
                    single<BuyProductUseCase> { BuyProductUseCase() }
                    single<AddressViewModel> { AddressViewModel(getAddressesUseCase = get()) }
                    single<AddAddressUseCase> { AddAddressUseCase() }
                    single<AddAddressViewModel> { AddAddressViewModel(addAddressUseCase = get()) }
                    single<CheckTokenUseCase> { CheckTokenUseCase(appDataStoreManager = get()) }
                    single<LogoutUseCase> { LogoutUseCase(appDataStoreManager = get()) }
                    single<AppDataStore> { AppDataStoreManager(context = context) }
                    single<TokenManager> {
                        TokenManager(
                            checkTokenUseCase = get(),
                            logoutUseCase = get()
                        )
                    }
                    single<SplashViewModel> { SplashViewModel(isOnBoardedUseCase = get()) }
                    single<IsOnBoardedUseCase> { IsOnBoardedUseCase(appDataStoreManager = get()) }
                    single<OnBoardingViewModel> { OnBoardingViewModel(setOnBoardingUseCase = get()) }
                    single<SetOnBoardingUseCase> { SetOnBoardingUseCase(dataStoreManager = get()) }
                }
            )
        )
        createEagerInstances()
    }
    return startKoin(koinApplication)
}

val LocalKoinApplication = compositionLocalOf {
//    initKoinApp()
}