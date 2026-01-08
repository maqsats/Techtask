package com.intechbel.techtask.feature.users.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.intechbel.techtask.design.image.PaginationManager
import com.intechbel.techtask.design.image.ThImage
import com.intechbel.techtask.feature.users.domain.model.User
import com.intechbel.techtask.feature.users.presentation.core.IUserComponent
import kotlin.random.Random

@Composable
fun UserLayout(
    component: IUserComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.stateFlow.collectAsState()
    val usersFlow = component.usersPagingFlow
    val users: LazyPagingItems<User> = usersFlow.collectAsLazyPagingItems()

    PaginationManager(
        modifier = modifier.fillMaxSize(),
        pagingData = users,
        key = { _ -> Random.nextLong().toString() },
        successView = { user ->
            UserItem(
                user = user,
            )
        },
        loadingContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "Loading...")
            }
        },
        emptyContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "No users found.")
            }
        }
    )
}

@Composable
private fun UserItem(
    user: User,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 12.dp),
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
            }
        }
    }
}