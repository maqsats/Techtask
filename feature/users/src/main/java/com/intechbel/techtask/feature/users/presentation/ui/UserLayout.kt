package com.intechbel.techtask.feature.users.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.intechbel.techtask.design.image.ThImage
import com.intechbel.techtask.design.image.ThPagination
import com.intechbel.techtask.feature.users.R
import com.intechbel.techtask.feature.users.domain.model.User
import com.intechbel.techtask.feature.users.presentation.core.IUserComponent
import com.intechbel.techtask.feature.users.presentation.core.UserIntent

@Composable
fun UserLayout(
    component: IUserComponent,
    modifier: Modifier = Modifier,
) {
    component.stateFlow.collectAsState()

    ThPagination(
        modifier = modifier.fillMaxSize(),
        pagingData = component.usersPagingFlow.collectAsLazyPagingItems(),
        key = { user -> user.email },
        successView = { user ->
            UserItem(
                user = user,
                onUserClick = { selectedUser ->
                    component.accept(UserIntent.OnUserClick(selectedUser))
                }
            )
        },
        loadingContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = stringResource(R.string.user_loading_message))
            }
        },
        emptyContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = stringResource(R.string.user_empty_message))
            }
        }
    )
}

@Composable
private fun UserItem(
    user: User,
    modifier: Modifier = Modifier,
    onUserClick: (User) -> Unit,
) {
    Box(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clickable { onUserClick(user) },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ThImage(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .fillMaxWidth(0.2f),
                image = user.picture
            )
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = "${user.name.title} ${user.name.first} ${user.name.last}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = user.email,
                    fontSize = 14.sp,
                )
                Text(
                    text = listOfNotNull(
                        user.location.city.takeIf { it.isNotBlank() },
                        user.location.country.takeIf { it.isNotBlank() }
                    ).joinToString(", "),
                    fontSize = 12.sp,
                )
            }
        }
    }
}