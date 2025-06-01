package org.gbu.restaurant.business.usecase

import org.gbu.restaurant.business.core.AppDataStore
import org.gbu.restaurant.business.core.BaseUseCase
import org.gbu.restaurant.business.data.local.entity.Category
import org.gbu.restaurant.business.data.local.entity.Search
import org.gbu.restaurant.business.data.network.common.MainGenericResponse
import org.gbu.restaurant.business.data.network.common.ProgressBarState
import org.gbu.restaurant.business.data.network.dto.ProductDTO
import org.gbu.restaurant.business.data.network.dto.SearchDTO
import org.gbu.restaurant.business.data.network.dto.toSearch

class SearchUseCase(
    private val appDataStoreManager: AppDataStore
) : BaseUseCase<SearchUseCase.Params, SearchDTO, Search>(appDataStoreManager){
    data class Params(
        val minPrice: Int? = null,
        val maxPrice: Int? = null,
        val categories: List<Category>? = null,
        val sort: Int?,
        val page: Int
    )

    override suspend fun run(params: Params, token: String): MainGenericResponse<SearchDTO>? =
        MainGenericResponse(
            SearchDTO(
            products = listOf(
                ProductDTO(
                    description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    id = 1L,
                    image = "https://img.freepik.com/free-photo/autumn-symbols-near-plate_23-2147867482.jpg",
                    isLike = false,
                    0,
                    price = 23.00,
                    rate = 3.8,
                    title = "Lorem Ipsum",
                    category = null,
                    gallery = listOf("https://img.freepik.com/free-photo/aesthetic-dinner-table-background_53876-133273.jpg", "https://img.freepik.com/free-photo/empty-plate-farfalle-pasta_176474-6140.jpg")
                )
            )
        ), status = true
        )

    override fun mapApiResponse(apiResponse: MainGenericResponse<SearchDTO>?): Search? = apiResponse?.result?.toSearch()

    override val progressBarType: ProgressBarState = ProgressBarState.ScreenLoading
    override val needNetworkState: Boolean = false
    override val createException: Boolean = false
    override val checkToken: Boolean = true

}