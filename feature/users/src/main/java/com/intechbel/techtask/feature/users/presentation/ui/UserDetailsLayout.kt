package com.intechbel.techtask.feature.users.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.intechbel.techtask.design.image.ThImage
import com.intechbel.techtask.design.theme.AppTheme
import com.intechbel.techtask.feature.users.R
import com.intechbel.techtask.feature.users.presentation.core.IUserDetailsComponent
import com.intechbel.techtask.feature.users.presentation.core.UserDetailsIntent
import com.intechbel.techtask.feature_sub.map.presentation.MapCoordinates
import com.intechbel.techtask.feature_sub.map.presentation.MapMarker
import com.intechbel.techtask.feature_sub.map.presentation.ThMap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsLayout(
    component: IUserDetailsComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.stateFlow.collectAsState()
    val user = state.user
    val coordinates = MapCoordinates(
        latitude = user.location.coordinates.latitude,
        longitude = user.location.coordinates.longitude
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.user_details_title)) },
                navigationIcon = {
                    IconButton(onClick = { component.accept(UserDetailsIntent.OnBackClick) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.content_description_back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = AppTheme.dimens.padding.medium,
                    vertical = AppTheme.dimens.padding.small
                ),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.padding.small)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.padding.medium)
            ) {
                ThImage(
                    modifier = Modifier
                        .height(AppTheme.dimens.componentSize.profileImageHeight)
                        .fillMaxWidth(0.35f),
                    image = user.picture,
                    contentDescription = stringResource(R.string.content_description_user_profile_picture)
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${user.name.title} ${user.name.first} ${user.name.last}",
                        fontSize = AppTheme.dimens.fontSize.fontSize20,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(AppTheme.dimens.padding.doubleExtraSmall))
                    DetailRow(label = stringResource(R.string.user_email_label), value = user.email)
                    user.phone?.takeIf { it.isNotBlank() }?.let {
                        DetailRow(label = stringResource(R.string.user_phone_label), value = it)
                    }
                }
            }

            Text(
                text = stringResource(R.string.user_location_title),
                fontSize = AppTheme.dimens.fontSize.fontSize18,
                fontWeight = FontWeight.SemiBold,
            )
            DetailRow(
                label = stringResource(R.string.user_address_label),
                value = user.location.formattedAddress
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(AppTheme.dimens.componentSize.mapHeight)
                    .clip(RoundedCornerShape(AppTheme.dimens.borderRadius.radius16)),
            ) {
                ThMap(
                    modifier = Modifier.fillMaxSize(),
                    markers = listOf(
                        MapMarker(
                            position = coordinates,
                            title = "${user.name.first} ${user.name.last}",
                            snippet = user.location.formattedAddress
                        )
                    ),
                )
            }
        }
    }
}

@Composable
private fun DetailRow(
    label: String,
    value: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.padding.tripleExtraSmall)
    ) {
        Text(
            text = label,
            fontSize = AppTheme.dimens.fontSize.fontSize12,
            fontWeight = FontWeight.Medium,
        )
        Text(
            text = value,
            fontSize = AppTheme.dimens.fontSize.fontSize14,
            fontWeight = FontWeight.Normal,
        )
    }
}
