package org.gbu.restaurant.business.usecase

import org.gbu.restaurant.business.constants.DataStoreKeys
import org.gbu.restaurant.business.core.AppDataStore
import org.gbu.restaurant.business.core.BaseDataStoreUseCase
import org.gbu.restaurant.business.data.network.common.ProgressBarState

class IsOnBoardedUseCase(
    private val appDataStoreManager: AppDataStore
) : BaseDataStoreUseCase<Unit, Boolean>(appDataStoreManager) {
    override suspend fun run(params: Unit): Boolean =
        appDataStoreManager.readValue(DataStoreKeys.IS_ON_BOARDED).toBoolean()

    override val progressBarState: ProgressBarState = ProgressBarState.Idle
}