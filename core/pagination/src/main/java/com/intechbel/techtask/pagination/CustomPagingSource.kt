package com.intechbel.techtask.pagination

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.intechbel.techtask.shared.ApiResult
import com.intechbel.techtask.shared.common_models.PagingParam

abstract class CustomPagingSource<T : Any, B : Any, Param : PagingParam> : PagingSource<Int, T>() {

    var data: MutableStateFlow<B?> = MutableStateFlow(null)

    val paging: MutableStateFlow<PagingData<T>> = MutableStateFlow(PagingData.empty())

    private var pagingParam: Param? = null

    operator fun invoke(pagingParam: Param, coroutineScope: CoroutineScope) =
        coroutineScope.launch {
            getPagingList(pagingParam).cachedIn(this).collectLatest {
                paging.value = it
            }
        }

    protected abstract fun pagingUseCase(): CustomPagingUseCase<T, B, Param>

    protected abstract fun newInstance(): CustomPagingSource<T, B, Param>

    private fun <T : Any> CustomPagingSource<T, B, Param>.getPagingList(pagingParam: Param): Flow<PagingData<T>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE,
                enablePlaceholders = ENABLE_PLACEHOLDERS,
                initialLoadSize = INITIAL_LOAD_SIZE
            ),
            pagingSourceFactory = {
                val instance = newInstance()
                instance.pagingParam = pagingParam
                instance
            }
        ).flow
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val nextPage = params.key ?: 0
        val param = pagingParam ?: return LoadResult.Error(Exception()) // This should never happen

        val result = pagingUseCase()(nextPage, PAGE_SIZE, param)
        val response = when (result) {
            is ApiResult.Success -> {
                data.value = result.data.data
                result.data.list
            }
            is ApiResult.SuccessNoResponse -> emptyList()
            is ApiResult.Error -> return LoadResult.Error(Exception(result.messageBody))
        }

        val payload = if (result is ApiResult.Success) result.data else CustomPayloadImpl<T, B>(emptyList(), null)
        return LoadResult.Page(
            data = response,
            prevKey = if (nextPage == 0) null else nextPage - 1,
            nextKey = if (response.isEmpty() || pagingUseCase().getPageSize(payload) < PAGE_SIZE - 1) null else nextPage + 1
        )
    }

    companion object {
        const val PAGE_SIZE = 20
        const val PREFETCH_DISTANCE = 1
        const val ENABLE_PLACEHOLDERS = false
        const val INITIAL_LOAD_SIZE = 10
    }
}

