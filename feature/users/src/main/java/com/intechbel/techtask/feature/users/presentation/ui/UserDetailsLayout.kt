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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.intechbel.techtask.design.image.ThImage
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
                title = { Text(text = "User details") },
                navigationIcon = {
                    IconButton(onClick = { component.accept(UserDetailsIntent.OnBackClick) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
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
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ThImage(
                    modifier = Modifier
                        .height(120.dp)
                        .fillMaxWidth(0.35f),
                    image = user.picture,
                    contentDescription = "User profile picture"
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${user.name.title} ${user.name.first} ${user.name.last}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    DetailRow(label = "Email", value = user.email)
                    user.phone?.takeIf { it.isNotBlank() }?.let {
                        DetailRow(label = "Phone", value = it)
                    }
                }
            }

            Text(
                text = "Location",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
            DetailRow(label = "Address", value = user.location.formattedAddress)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clip(RoundedCornerShape(16.dp)),
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
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
        )
    }
}
