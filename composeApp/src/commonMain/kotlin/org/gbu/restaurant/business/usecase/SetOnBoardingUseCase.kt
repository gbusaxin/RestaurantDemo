package org.gbu.restaurant.business.usecase

import org.gbu.restaurant.business.constants.DataStoreKeys
import org.gbu.restaurant.business.core.AppDataStore
import org.gbu.restaurant.business.core.BaseDataStoreUseCase
import org.gbu.restaurant.business.data.network.common.ProgressBarState

class SetOnBoardingUseCase(
    private val dataStoreManager: AppDataStore
): BaseDataStoreUseCase<Boolean, Unit>(dataStoreManager) {
    override suspend fun run(params: Boolean) =
        dataStoreManager.setValue(DataStoreKeys.IS_ON_BOARDED, params.toString())

    override val progressBarState: ProgressBarState = ProgressBarState.Idle
}