package com.sarang.torang.di.addreview_di

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.instagramgallery.di.Instagramgallery_di.GalleryWithPhotoPicker
import com.sarang.torang.RootNavController
import com.sarang.torang.addreview.compose.AddReviewScreen
import com.sarang.torang.addreview.compose.Login

@OptIn(ExperimentalMaterial3Api::class)
fun provideAddReviewScreen(navHostController : RootNavController = RootNavController()
): @Composable (onClose: () -> Unit) -> Unit =
    { onCloseReview ->
        val navController = rememberNavController()
        val dispatch = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
        Box {
            AddReviewScreen(
                galleryScreen = { GalleryWithPhotoPicker(onNext = it.onNext, onClose = { it.onClose.invoke() }) },
                navController = navController,
                onRestaurant = { navController.navigate("addReview") },
                onShared = { onCloseReview()
                             navController.popBackStack("gallery", false) },
                onNext = { navController.navigate("selectRestaurant") },
                onClose = { onCloseReview() },
                onNotSelected = { navController.navigate("addReview") },
                loginScreen = { Login(onLogin = { navHostController.emailLogin() },
                    onBack = { dispatch?.onBackPressed() }) }
            )
        }
    }
