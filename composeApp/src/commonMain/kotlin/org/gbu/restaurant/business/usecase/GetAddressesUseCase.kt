package org.gbu.restaurant.business.usecase

import org.gbu.restaurant.business.core.BaseUseCase
import org.gbu.restaurant.business.data.local.entity.Address
import org.gbu.restaurant.business.data.network.common.MainGenericResponse
import org.gbu.restaurant.business.data.network.common.ProgressBarState

class GetAddressesUseCase(
) : BaseUseCase<Unit, List<Address>, List<Address>>() {
    override suspend fun run(params: Unit, token: String): MainGenericResponse<List<Address>>? {
        return MainGenericResponse(
            listOf(
                Address(
                    id = 1, address = "Svateho Vincenta 2",
                    country = "Slovakia", city = "Bratislava",
                    state = "Bratislavksy kraj", zipCode = "821 03"
                )
            ), true
        )
    }

    override fun mapApiResponse(apiResponse: MainGenericResponse<List<Address>>?): List<Address>? =
        apiResponse?.result


    override val progressBarType: ProgressBarState = ProgressBarState.LoadingWithLogo
    override val needNetworkState: Boolean = true
    override val createException: Boolean = true
    override val checkToken: Boolean = true
}