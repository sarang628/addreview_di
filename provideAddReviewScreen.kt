package com.sarang.torang.di.addreview_di

import android.os.Build
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.instagramgallery.di.Instagramgallery_di.GalleryWithPhotoPicker
import com.sarang.torang.RootNavController
import com.sarang.torang.addreview.compose.AddReviewScreen

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun provideAddReviewScreen(navHostController: RootNavController): @Composable (onClose: () -> Unit) -> Unit =
    { onCloseReview ->
        val navController = rememberNavController()
        val dispatch = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
            AddReviewScreen(
                galleryScreen = { color, onNext, onClose -> GalleryWithPhotoPicker(onNext = onNext, onClose = { onClose.invoke() }) },
                navController = navController,
                onRestaurant = { navController.navigate("addReview") },
                onShared = { navHostController.popBackStack() },
                onNext = { navController.navigate("selectRestaurant") },
                onClose = { onCloseReview.invoke() },
                onNotSelected = { navController.navigate("addReview") },
                onBack = { dispatch?.onBackPressed() },
                onLogin = { navHostController.emailLogin() }
            )
    }
