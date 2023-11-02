package com.ssafyb109.bangrang.view

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ssafyb109.bangrang.ground.LocationService
import com.ssafyb109.bangrang.view.utill.CardItem
import com.ssafyb109.bangrang.view.utill.LocationSelector
import com.ssafyb109.bangrang.viewmodel.EventViewModel
import com.ssafyb109.bangrang.viewmodel.UserViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePage(
    navController: NavHostController,
    userViewModel: UserViewModel
) {
    val eventViewModel: EventViewModel = hiltViewModel()
    val selectedEvent by eventViewModel.selectEvents.collectAsState()

    val currentAddress by userViewModel.currentAddress.collectAsState()
    val activeLocation = remember { mutableStateOf("전국") }

    val filteredEvents = selectedEvent.filter {
        (activeLocation.value == "전국" || it.address.contains(activeLocation.value))
    }

//    LaunchedEffect(Unit) {
//        userViewModel.updateLocation()
//    }

    val context = LocalContext.current

    LaunchedEffect(true) {
        // 위치서비스 시작
        val serviceIntent = Intent(context, LocationService::class.java)
        context.startService(serviceIntent)

        // 이벤트 받아오기
        eventViewModel.selectEvent()
    }


    Column(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {

            // 현 위치 표시
            item {
                Text(
                    text = if (currentAddress != null) {
                        "현위치: $currentAddress"
                    } else {
                        "위치를 가져오는 중..."
                    },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                NaverMap(height = 200.dp, false)
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Text("이번 주말 갈만한 곳", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            item {
                LocationSelector(activeLocation)
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(filteredEvents) { event ->
                        CardItem(event = event, navController)
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Text("내 주위 가볼만한 곳", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(selectedEvent) { event ->
                        CardItem(event = event, navController)
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                CopyrightNotice()
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}