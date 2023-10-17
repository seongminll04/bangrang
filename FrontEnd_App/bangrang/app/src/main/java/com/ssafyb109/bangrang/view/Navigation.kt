package com.ssafyb109.bangrang.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ssafyb109.bangrang.R
import com.ssafyb109.bangrang.ui.theme.logocolor

@Composable
fun TopBar(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val interactionSource = remember { MutableInteractionSource() }
                Box(modifier = Modifier.padding(top = 24.dp, start = 12.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        Modifier
                            .padding(start = 120.dp)
                            .scale(1.5f)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = { navController.navigate("Home") }
                            )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .background(logocolor)
            )
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .height(72.dp)
    ) {
        // 그림자 추가를 위한 Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.2.dp)
                .shadow(elevation = 0.2.dp, shape = RectangleShape)
        )
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomBarButton("행사") {
                navController.navigate("EventPage")
            }
            BottomBarButton("찜") {
                navController.navigate("FavoritePage")
            }
            if (currentDestination == "Home") {
                HighlightedBottomBarButton(icon = Icons.Default.LocationOn, color = logocolor) {
                    navController.navigate("MapPage")
                }
            } else {
                HighlightedBottomBarButton(icon = Icons.Default.Home, color = logocolor) {
                    navController.navigate("Home")
                }
            }
            BottomBarButton("랭킹") {
                navController.navigate("RankPage")
            }
            BottomBarButton("My방랑") {
                navController.navigate("MyPage")
            }
        }
    }
}

@Composable
fun HighlightedBottomBarButton(icon: ImageVector, color: Color, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable(onClick = onClick)
            .widthIn(min = 84.dp, max = 100.dp)
            .offset(y = (-16).dp)  // 위쪽으로 오프셋
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .background(color = color, shape = CircleShape)
                .align(Alignment.CenterHorizontally)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(40.dp))
        }
    }
}


@Composable
fun BottomBarButton(label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable(onClick = onClick)
            .widthIn(min = 64.dp, max = 80.dp)
    ) {
        val icon = when (label) {
            "행사" -> Icons.Default.ShoppingCart
            "찜" -> Icons.Default.Star
            "메인" -> Icons.Default.Home
            "지도" -> Icons.Default.LocationOn
            "랭킹" -> Icons.Default.Star
            "My방랑" -> Icons.Default.Person
            else -> Icons.Default.Home
        }

        Icon(
            imageVector = icon, contentDescription = null, modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp)) // 아이콘과 텍스트 사이 간격
        Text(label)
    }
}
