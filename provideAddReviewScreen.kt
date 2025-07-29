package com.sarang.torang.di.addreview_di

import android.os.Build
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.RequiresApi
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.sarang.torang.RootNavController
import com.sarang.torang.addreview.compose.AddReviewScreen

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun provideAddReviewScreen(navHostController: RootNavController): @Composable (onClose: () -> Unit) -> Unit =
    { onCloseReview ->
        val navController = rememberNavController()
        val dispatch = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
        AddReviewScreen(
            galleryScreen = { color, onNext, onClose ->
                Scaffold(
                    topBar = { TopAppBar(title = {}, navigationIcon = { IconButton({ onClose.invoke(null) }) { Icon(Icons.AutoMirrored.Default.ArrowBack, "") } }) }
                ) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(16.dp),
                            text = "구글 정책상 사진에 직접 접근할 수 없어, 업로드 기능은 현재 이용이 불가 합니다.."
                        )
                    }
                }
                /*GalleryNavHost(onNext = onNext, onClose = { onClose.invoke(null) }, onBack = { onClose.invoke(null) })*/
            },
            navController = navController,
            onRestaurant = { navController.navigate("addReview") },
            onShared = { navHostController.popBackStack() },
            onNext = { navController.navigate("selectRestaurant") },
            onClose = { onCloseReview.invoke() },
            onNotSelected = { navController.navigate("addReview") },
            onBack = {
                dispatch?.onBackPressed()
            },
            onLogin = { navHostController.emailLogin() }
        )
    }