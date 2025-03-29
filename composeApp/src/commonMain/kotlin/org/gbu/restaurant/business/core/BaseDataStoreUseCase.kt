package org.gbu.restaurant.business.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.gbu.restaurant.business.data.network.common.DataState
import org.gbu.restaurant.business.data.network.common.ProgressBarState
import org.gbu.restaurant.utils.handleUseCaseException

abstract class BaseDataStoreUseCase<Params, Result>(
    private val appDataStoreManager: AppDataStore
) {
    abstract suspend fun run(params: Params): Result

    abstract val progressBarState: ProgressBarState

    fun execute(params: Params): Flow<DataState<Result>> = flow {
        try {
            emit(DataState.Loading(progressBarState))
            val result = run(params)
            emit(DataState.Data(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(handleUseCaseException(e))
        } finally {
            emit(DataState.Loading(ProgressBarState.Idle))
        }
    }
}