package org.gbu.restaurant.business.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.gbu.restaurant.business.data.network.common.DataState
import org.gbu.restaurant.business.data.network.common.MainGenericResponse
import org.gbu.restaurant.business.data.network.common.ProgressBarState

abstract class BaseUseCase<Params, ApiResponse, Result>(
    private val appDataStoreManager: AppDataStore? = null
) {

    abstract suspend fun run(params: Params, token: String): MainGenericResponse<ApiResponse>?

    abstract fun mapApiResponse(apiResponse: MainGenericResponse<ApiResponse>?): Result?

    abstract val progressBarType: ProgressBarState
    abstract val needNetworkState: Boolean
    abstract val createException: Boolean
    abstract val checkToken: Boolean

    fun execute(params: Params): Flow<DataState<Result>> = flow {
        try {
            emit(DataState.Loading())

            val result = run(params, "")// TODO token

            if (result?.status == false && createException) {
                throw Exception(
                    "${result.alert?.title}: ${result.alert?.message}"
                )
            }

            result?.alert?.let {
                if (!createException) emit(
                    DataState.Response(
                        uiComponent = UIComponent.Dialog(alert = it)
                    )
                )
            }

            if (needNetworkState) {
                emit(DataState.NetworkStatus(NetworkState.Good))
            }

            emit(DataState.Data(data = mapApiResponse(result), status = result?.status))
        } catch (e: Exception) {
            if (needNetworkState) {
                emit(DataState.NetworkStatus(NetworkState.Failed))
            }
            e.printStackTrace()
//            emit(handleUseCaseException(e)) // TODO implement
        } finally {
            emit(DataState.Loading(ProgressBarState.Idle))
        }
    }

}