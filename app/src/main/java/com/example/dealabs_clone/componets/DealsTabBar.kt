package com.example.dealabs_clone

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DealsTabBar() {
    val tabs = listOf("LES + HOT", "POUR VOUS", "HOT", "NOUVEAUX", "COMMENTÉS")
    val (selectedTabIndex, setSelectedTabIndex) = remember { mutableStateOf(0) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF333333))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(tabs.size) { index ->
            val tab = tabs[index]
            Box(
                modifier = Modifier
                    .clickable { setSelectedTabIndex(index) }
                    .padding(8.dp)
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tab,
                    color = if (selectedTabIndex == index) MaterialTheme.colorScheme.primary else Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}


@Preview
@Composable
private fun DealsTabBarPreview() {
    DealsTabBar()
}