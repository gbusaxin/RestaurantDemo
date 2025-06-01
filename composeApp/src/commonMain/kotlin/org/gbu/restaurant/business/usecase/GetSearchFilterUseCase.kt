package org.gbu.restaurant.business.usecase

import org.gbu.restaurant.business.core.AppDataStore
import org.gbu.restaurant.business.core.BaseUseCase
import org.gbu.restaurant.business.data.local.entity.SearchFilter
import org.gbu.restaurant.business.data.network.common.MainGenericResponse
import org.gbu.restaurant.business.data.network.common.ProgressBarState
import org.gbu.restaurant.business.data.network.dto.SearchFilterDTO
import org.gbu.restaurant.business.data.network.dto.toSearchFilter

class GetSearchFilterUseCase(
    private val appDataStoreManager: AppDataStore
) : BaseUseCase<Unit, SearchFilterDTO, SearchFilter>(appDataStoreManager) {
    override suspend fun run(params: Unit, token: String): MainGenericResponse<SearchFilterDTO>? =
        MainGenericResponse(
            SearchFilterDTO(
                categories = emptyList(),
                minPrice = 1,
                maxPrice = 30
            ), status = true
        )

    override fun mapApiResponse(apiResponse: MainGenericResponse<SearchFilterDTO>?): SearchFilter? =
        apiResponse?.result?.toSearchFilter()

    override val progressBarType: ProgressBarState = ProgressBarState.LoadingWithLogo
    override val needNetworkState: Boolean = true
    override val createException: Boolean = true
    override val checkToken: Boolean = true
}