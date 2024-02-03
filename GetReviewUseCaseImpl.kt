package com.sryang.addreview.di.addreview_di

import android.net.http.HttpException
import com.sryang.addreview.data.SelectRestaurantData
import com.sryang.addreview.uistate.AddReviewUiState
import com.sryang.addreview.usecase.GetReviewUseCase
import com.sarang.torang.api.ApiRestaurant
import com.sarang.torang.api.ApiReview
import com.sarang.torang.api.handle
import com.sarang.torang.data.Filter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class GetReviewUseCaseImpl {
    @Provides
    fun provideSelectRestaurantService(apiReview: ApiReview): GetReviewUseCase {
        return object : GetReviewUseCase {
            override suspend fun invoke(reviewId: Int): AddReviewUiState {
                try {
                    return apiReview.getReviewsById(reviewId).toAddReviewUiSteate()
                } catch (e: retrofit2.HttpException) {
                    throw Exception(e.handle())
                }
            }
        }
    }
}
