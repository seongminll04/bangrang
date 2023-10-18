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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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
import java.lang.Math.cos
import java.lang.Math.sin

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
                    .height(52.dp),
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
    val isMenuExpanded = remember { mutableStateOf(false) }

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
            BottomBarButton("홈") {
                navController.navigate("Home")
            }
            if (isMenuExpanded.value) {
                ExpandingCenterMenu { selectedLabel ->
                    isMenuExpanded.value = false
                    when (selectedLabel) {
                        "행사" -> navController.navigate("EventPage")
                        "찜" -> navController.navigate("FavoritePage")
                        "랭킹" -> navController.navigate("RankPage")
                        "My방랑" -> navController.navigate("MyPage")
                    }
                }
            }
            BottomBarButton("지도") {
                navController.navigate("MapPage")
            }
        }
    }
}

@Composable
fun CentralButton(isExpanded: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(if (isExpanded) logocolor else Color.Transparent, CircleShape)
            .padding(8.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            tint = if (isExpanded) Color.White else Color.Black
        )
    }
}

@Composable
fun ExpandingCenterMenu(onItemSelected: (String) -> Unit) {
    val items = listOf("행사", "찜", "랭킹", "My방랑")
    val icons = mapOf(
        "행사" to Icons.Default.ShoppingCart,
        "찜" to Icons.Default.Favorite,
        "랭킹" to Icons.Default.Star,
        "My방랑" to Icons.Default.Person
    )

    val distance = 70f  // 반원의 반경

    // Bottom offset 추가
    Box(
        modifier = Modifier
            .size(160.dp)
            .background(logocolor, CircleShape)
            .offset(y = (-160).dp),  // 원이 바텀바를 넘어서 나오도록 offset을 추가
    ) {
        for (i in items.indices) {
            val angle = (i * (360f / items.size)) + 45f // 원을 4등분 하기 위한 각도 설정 (+45f는 시작 각도 조절)
            val offsetX = kotlin.math.cos(Math.toRadians(angle.toDouble())).toFloat() * distance
            val offsetY = kotlin.math.sin(Math.toRadians(angle.toDouble())).toFloat() * distance

            IconButton(
                onClick = { onItemSelected(items[i]) },
                modifier = Modifier.offset(x = offsetX.dp, y = -offsetY.dp)
            ) {
                Icon(imageVector = icons[items[i]]!!, contentDescription = null, tint = Color.White)
            }
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
            "홈" -> Icons.Default.Home
            "지도" -> Icons.Default.LocationOn
            else -> Icons.Default.Home  // 기본값, 이 부분은 필요에 따라 변경 가능합니다.
        }

        Icon(
            imageVector = icon, contentDescription = null, modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp)) // 아이콘과 텍스트 사이 간격
        Text(label)
    }
}