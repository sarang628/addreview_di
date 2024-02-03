package com.sryang.addreview.di.addreview_di

import com.sryang.addreview.uistate.Picture
import com.sryang.addreview.usecase.ModReviewUseCase
import com.sarang.torang.api.ApiReview
import com.sarang.torang.data.dao.LoggedInUserDao
import com.sarang.torang.data.dao.PictureDao
import com.sarang.torang.data.dao.ReviewDao
import com.sarang.torang.repository.ReviewRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

@InstallIn(SingletonComponent::class)
@Module
class ModReviewUseCaseImpl {
    @Provides
    fun provideModReviewUseCase(
        revoewRepository: ReviewRepository
    ): ModReviewUseCase {
        return object : ModReviewUseCase {
            override suspend fun invoke(
                reviewId: Int,
                contents: String,
                restaurantId: Int,
                rating: Float,
                files: List<Picture>?,
                uploadedImage: List<Int>?
            ) {
                revoewRepository.updateReview(
                    reviewId = reviewId,
                    contents = contents,
                    restaurantId = restaurantId,
                    rating = rating,
                    files = if (files != null)
                        files.filter { !it.isUploaded }.map { it.url }
                    else ArrayList(),
                    uploadedImage = uploadedImage ?: ArrayList()
                )
            }
        }
    }
}