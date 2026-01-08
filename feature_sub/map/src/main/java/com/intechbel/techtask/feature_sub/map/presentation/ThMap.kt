package com.intechbel.techtask.feature_sub.map.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import java.util.UUID

data class MapCoordinates(
    val latitude: Double,
    val longitude: Double,
)

data class MapMarker(
    val id: String = UUID.randomUUID().toString(),
    val position: MapCoordinates,
    val title: String? = null,
    val snippet: String? = null,
)

@Composable
fun ThMap(
    markers: List<MapMarker>,
    modifier: Modifier = Modifier,
    cameraPositionState: CameraPositionState = rememberCameraPositionState {
        val target = markers.firstOrNull()?.position ?: DEFAULT_COORDINATES
        position = CameraPosition.fromLatLngZoom(target.toLatLng(), DEFAULT_ZOOM)
    },
    properties: MapProperties = MapProperties(),
    uiSettings: MapUiSettings = MapUiSettings(zoomControlsEnabled = false),
    onMarkerClick: (MapMarker) -> Boolean = { false },
) {
    GoogleMap(
        modifier = modifier,
        properties = properties,
        uiSettings = uiSettings,
        cameraPositionState = cameraPositionState,
    ) {
        markers.forEach { marker ->
            Marker(
                state = rememberMarkerState(position = marker.position.toLatLng()),
                title = marker.title,
                snippet = marker.snippet,
                onClick = {
                    onMarkerClick(marker)
                }
            )
        }
    }
}

private fun MapCoordinates.toLatLng(): LatLng = LatLng(latitude, longitude)

private const val DEFAULT_ZOOM = 6f
private val DEFAULT_COORDINATES = MapCoordinates(0.0, 0.0)

