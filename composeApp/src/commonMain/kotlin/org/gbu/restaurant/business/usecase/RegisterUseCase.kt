package org.gbu.restaurant.business.usecase

import org.gbu.restaurant.business.constants.AUTHORIZATION_BEARER_TOKEN
import org.gbu.restaurant.business.constants.DataStoreKeys
import org.gbu.restaurant.business.core.AppDataStore
import org.gbu.restaurant.business.core.BaseUseCase
import org.gbu.restaurant.business.data.network.common.MainGenericResponse
import org.gbu.restaurant.business.data.network.common.ProgressBarState
import org.gbu.restaurant.business.services.SplashService

class RegisterUseCase(
    private val service: SplashService,
    private val appDataStoreManager: AppDataStore
) : BaseUseCase<RegisterUseCase.Params, String?, String>(appDataStoreManager) {
    data class Params(
        val name: String,
        val email: String,
        val password: String,
    )

    override suspend fun run(params: Params, token: String): MainGenericResponse<String?>? {
        val apiResponse = service.register(params.name, params.email, params.password)

        val result = apiResponse.result

        if (result != null) {
            appDataStoreManager.setValue(
                DataStoreKeys.TOKEN,
                AUTHORIZATION_BEARER_TOKEN + result
            )
            appDataStoreManager.setValue(
                DataStoreKeys.EMAIL,
                params.email
            )
        }
        return apiResponse
    }

    override fun mapApiResponse(apiResponse: MainGenericResponse<String?>?): String? =
        apiResponse?.result

    override val progressBarType: ProgressBarState = ProgressBarState.ButtonLoading
    override val needNetworkState: Boolean = false
    override val createException: Boolean = false
    override val checkToken: Boolean = false
}