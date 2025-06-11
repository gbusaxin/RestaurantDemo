package org.gbu.restaurant.di

import androidx.compose.runtime.compositionLocalOf
import org.gbu.restaurant.business.common.Context
import org.gbu.restaurant.business.core.AppDataStore
import org.gbu.restaurant.business.core.AppDataStoreManager
import org.gbu.restaurant.business.usecase.AddAddressUseCase
import org.gbu.restaurant.business.usecase.BuyProductUseCase
import org.gbu.restaurant.business.usecase.CartListUseCase
import org.gbu.restaurant.business.usecase.CheckTokenUseCase
import org.gbu.restaurant.business.usecase.GetAddressesUseCase
import org.gbu.restaurant.business.usecase.GetHomeUseCase
import org.gbu.restaurant.business.usecase.GetMenuDetailsUseCase
import org.gbu.restaurant.business.usecase.GetOrdersUseCase
import org.gbu.restaurant.business.usecase.GetSearchFilterUseCase
import org.gbu.restaurant.business.usecase.IsOnBoardedUseCase
import org.gbu.restaurant.business.usecase.LogoutUseCase
import org.gbu.restaurant.business.usecase.SearchUseCase
import org.gbu.restaurant.business.usecase.SetOnBoardingUseCase
import org.gbu.restaurant.ui.screens.add_address.viewmodel.AddAddressViewModel
import org.gbu.restaurant.ui.screens.address.viewmodel.AddressViewModel
import org.gbu.restaurant.ui.screens.cart.viewmodel.CartViewModel
import org.gbu.restaurant.ui.screens.checkout.viewmodel.CheckoutViewModel
import org.gbu.restaurant.ui.screens.home.viewmodel.HomeViewModel
import org.gbu.restaurant.ui.screens.menu_detail.viewmodel.MenuDetailsViewModel
import org.gbu.restaurant.ui.screens.my_coupons.viewmodel.MyCouponsViewModel
import org.gbu.restaurant.ui.screens.my_orders.viewmodel.MyOrdersViewModel
import org.gbu.restaurant.ui.screens.on_boarding.viewmodel.OnBoardingViewModel
import org.gbu.restaurant.ui.screens.payment_method.viewmodel.PaymentMethodViewModel
import org.gbu.restaurant.ui.screens.profile.viewmodel.ProfileViewModel
import org.gbu.restaurant.ui.screens.search.viewmodel.SearchViewModel
import org.gbu.restaurant.ui.screens.settings.viewmodel.SettingsViewModel
import org.gbu.restaurant.ui.screens.splash.viewmodel.SplashViewModel
import org.gbu.restaurant.ui.screens.wish_list.viewmodel.WishListViewModel
import org.gbu.restaurant.ui.token_manager.TokenManager
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.koinApplication
import org.koin.dsl.module

fun doInitKoinApplication(
    platformModules: List<Module> = listOf(),
    context: Context
): KoinApplication {
    val koinApplication = koinApplication {
        modules(
            platformModules + listOf(
                module {
                    factory<MenuDetailsViewModel> { MenuDetailsViewModel(getMenuDetailUseCase = get()) }
                    factory<CartViewModel> { CartViewModel(cartListUseCase = get()) }
                    factory<CheckoutViewModel> {
                        CheckoutViewModel(
                            cartListUseCase = get(),
                            getAddressesUseCase = get(),
                            buyProductUseCase = get()
                        )
                    }
                    factory<AddressViewModel> { AddressViewModel(getAddressesUseCase = get()) }
                    factory<AddAddressViewModel> { AddAddressViewModel(addAddressUseCase = get()) }
                    factory<SplashViewModel> { SplashViewModel(isOnBoardedUseCase = get()) }
                    factory<OnBoardingViewModel> { OnBoardingViewModel(setOnBoardingUseCase = get()) }
                    factory<HomeViewModel> { HomeViewModel(homeUseCase = get()) }
                    factory<WishListViewModel> { WishListViewModel() }
                    factory<ProfileViewModel> { ProfileViewModel() }
                    factory<MyOrdersViewModel> { MyOrdersViewModel(getOrdersUseCase = get()) }
                    factory<PaymentMethodViewModel> { PaymentMethodViewModel() }
                    factory<MyCouponsViewModel> { MyCouponsViewModel() }
                    factory<SettingsViewModel> { SettingsViewModel(logoutUseCase = get()) }
                    factory<SearchViewModel> {
                        SearchViewModel(
                            searchUseCase = get(),
                            getSearchFilterUseCase = get()
                        )
                    }
                    single<GetOrdersUseCase> { GetOrdersUseCase(appDataStoreManager = get()) }
                    single<SearchUseCase> { SearchUseCase(appDataStoreManager = get()) }
                    single<GetSearchFilterUseCase> { GetSearchFilterUseCase(appDataStoreManager = get()) }
                    single<GetHomeUseCase> { GetHomeUseCase(appDataStoreManager = get()) }
                    single<GetMenuDetailsUseCase> { GetMenuDetailsUseCase() }
                    single<CartListUseCase> { CartListUseCase() }
                    single<CartListUseCase> { CartListUseCase() }
                    single<GetAddressesUseCase> { GetAddressesUseCase() }
                    single<BuyProductUseCase> { BuyProductUseCase() }
                    single<AddAddressUseCase> { AddAddressUseCase() }
                    single<CheckTokenUseCase> { CheckTokenUseCase(appDataStoreManager = get()) }
                    single<LogoutUseCase> { LogoutUseCase(appDataStoreManager = get()) }
                    single<AppDataStore> { AppDataStoreManager(context = context) }
                    single<TokenManager> {
                        TokenManager(
                            checkTokenUseCase = get(),
                            logoutUseCase = get()
                        )
                    }
                    single<IsOnBoardedUseCase> { IsOnBoardedUseCase(appDataStoreManager = get()) }
                    single<SetOnBoardingUseCase> { SetOnBoardingUseCase(dataStoreManager = get()) }
                }
            ))
        createEagerInstances()
    }
    return startKoin(koinApplication)
}

val LocalKoinApplication = compositionLocalOf<KoinApplication> {
    error("koin application is not initialized")
}