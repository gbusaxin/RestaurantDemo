package org.gbu.restaurant.business.usecase

import org.gbu.restaurant.business.core.BaseUseCase
import org.gbu.restaurant.business.data.network.common.MainGenericResponse
import org.gbu.restaurant.business.data.network.common.ProgressBarState

class AddAddressUseCase(

) : BaseUseCase<AddAddressUseCase.Params, Nothing, Boolean>() {

    data class Params(
        val address: String,
        val country: String,
        val city: String,
        val state: String,
        val zipCode: String
    )

    override suspend fun run(params: Params, token: String): MainGenericResponse<Nothing>? =
        MainGenericResponse(null, true)

    override fun mapApiResponse(apiResponse: MainGenericResponse<Nothing>?): Boolean? =
        apiResponse?.status

    override val progressBarType: ProgressBarState = ProgressBarState.FullScreenLoading
    override val needNetworkState: Boolean = false
    override val createException: Boolean = false
    override val checkToken: Boolean = true
}