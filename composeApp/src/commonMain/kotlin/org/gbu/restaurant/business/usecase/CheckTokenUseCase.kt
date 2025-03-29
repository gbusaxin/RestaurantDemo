package org.gbu.restaurant.business.usecase

import org.gbu.restaurant.business.constants.DataStoreKeys
import org.gbu.restaurant.business.core.AppDataStore
import org.gbu.restaurant.business.core.BaseDataStoreUseCase
import org.gbu.restaurant.business.data.network.common.ProgressBarState

class CheckTokenUseCase(
    private val appDataStoreManager: AppDataStore
):BaseDataStoreUseCase<Unit, Boolean>(appDataStoreManager) {
    override suspend fun run(params: Unit): Boolean =
        (appDataStoreManager.readValue(DataStoreKeys.TOKEN) ?: "").isNotEmpty()

    override val progressBarState: ProgressBarState = ProgressBarState.ButtonLoading
}