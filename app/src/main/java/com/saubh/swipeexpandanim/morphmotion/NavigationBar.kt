package com.saubh.swipeexpandanim.morphmotion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem

@Composable
fun NavigationBar(modifier: Modifier = Modifier) {
    NavigationBar(modifier = modifier) {
        NavigationBarItem(
            icon = { Icons.Default.Home },
            selected = true,
            onClick = { /* Handle Home click */ }
        )
        NavigationBarItem(
            icon = { Icons.Default.Favorite },
            selected = false,
            onClick = { /* Handle Favorite click */ }
        )
        NavigationBarItem(
            icon = { Icons.Default.Settings },
            selected = false,
            onClick = { /* Handle Settings click */ }
        )
    }
}