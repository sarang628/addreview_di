package com.sryang.addreview.di.addreview_di

import com.sryang.addreview.uistate.Picture
import com.sryang.addreview.usecase.ModReviewUseCase
import com.sryang.torang_repository.api.ApiReview
import com.sryang.torang_repository.data.dao.LoggedInUserDao
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
        apiReview: ApiReview,
        loggedInUserDao: LoggedInUserDao
    ): ModReviewUseCase {
        return object : ModReviewUseCase {
            override suspend fun invoke(
                reviewId: Int?,
                contents: String,
                restaurantId: Int,
                rating: Float,
                files: List<Picture>?,
                uploadedImage: List<Int>?
            ) {
                val user = loggedInUserDao.getLoggedInUser1() ?: return

                apiReview.addReview1(
                    review_id = reviewId,
                    user_id = user.userId,
                    contents = contents.toRequestBody(),
                    torang_id = restaurantId,
                    rating = rating,
                    file = if (files != null) filesToMultipart(files.filter { !it.isUploaded }
                        .map { it.url }) else null,
                    uploadedImage = uploadedImage
                )
            }

        }
    }
}