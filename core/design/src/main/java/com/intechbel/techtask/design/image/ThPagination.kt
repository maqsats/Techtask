package com.intechbel.techtask.design.image

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import androidx.paging.compose.LazyPagingItems
import com.intechbel.techtask.design.R
import com.intechbel.techtask.design.theme.AppTheme

@Composable
fun <T : Any> ThPagination(
    modifier: Modifier = Modifier,
    pagingData: LazyPagingItems<T>,
    successView: @Composable (data: T) -> Unit,
    key: (data: T) -> String,
    loadingContent: @Composable () -> Unit,
    emptyContent: @Composable () -> Unit = {},
    reverseLayout: Boolean = false,
) {
    val lazyListState = rememberLazyListState()

    if (pagingData.loadState.refresh is NotLoading) {
        if (pagingData.itemCount == 0) {
            emptyContent()
        }
    }
    LazyColumn(
        modifier = modifier,
        reverseLayout = reverseLayout,
        state = lazyListState
    ) {
        when (pagingData.loadState.refresh) {
            is Loading -> {
                item {
                    loadingContent()
                }
            }

            is NotLoading -> {
                items(
                    count = pagingData.itemCount,
                    key = { index -> pagingData[index]?.let { key(it) } ?: index },
                ) {
                    pagingData[it]?.let { data ->
                        successView(data)
                    }
                }
            }

            is LoadState.Error -> {
                item {
                    PaginationOnError {
                        pagingData.retry()
                    }
                }
            }
        }

        when (pagingData.loadState.append) {
            Loading -> {
                item {
                    PaginationLoader()
                }
            }

            is LoadState.Error -> {
                item {
                    PaginationOnError {
                        pagingData.retry()
                    }
                }
            }

            else -> Unit
        }
        when (pagingData.loadState.prepend) {
            Loading -> {
                item {
                    PaginationLoader()
                }
            }

            is LoadState.Error -> {
                item {
                    PaginationOnError {
                        pagingData.retry()
                    }
                }
            }

            else -> Unit
        }
    }

}

@Composable
private fun PaginationLoader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = AppTheme.dimens.padding.medium),
        contentAlignment = Alignment.Center
    ) {
        Text(stringResource(R.string.pagination_loading))
    }
}

@Composable
private fun PaginationOnError(retry: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .background(
                    color = androidx.compose.material3.MaterialTheme.colorScheme.errorContainer,
                    shape = RoundedCornerShape(AppTheme.dimens.borderRadius.radius8)
                )
                .padding(AppTheme.dimens.padding.medium)
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
                tint = androidx.compose.material3.MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier
                    .size(AppTheme.dimens.iconSize.medium)
                    .background(
                        color = androidx.compose.material3.MaterialTheme.colorScheme.errorContainer,
                        shape = RoundedCornerShape(AppTheme.dimens.borderRadius.radius4)
                    )
                    .padding(AppTheme.dimens.padding.tripleExtraSmall)
                    .clickable { retry() }
            )
        }
    }
}
