package com.sryang.addreview.di.addreview_di

import com.sryang.addreview.usecase.AddReviewUseCase
import com.sryang.torang_repository.api.ApiReview
import com.sryang.torang_repository.data.dao.LoggedInUserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

@InstallIn(SingletonComponent::class)
@Module
class AddReviewUseCaseImpl {
    @Provides
    fun provideReviewService(
        apiReview: ApiReview,
        loggedInUserDao: LoggedInUserDao
    ): AddReviewUseCase {
        return object : AddReviewUseCase {
            override suspend fun invoke(
                contents: String,
                restaurantId: Int,
                rating: Float,
                files: List<String>
            ) {
                val user = loggedInUserDao.getLoggedInUser1() ?: return

                apiReview.addReview1(
                    user_id = user.userId,
                    contents = contents.toRequestBody(),
                    torang_id = restaurantId,
                    rating = rating,
                    file = filesToMultipart(files)
                )
            }

        }
    }
}

fun filesToMultipart(file: List<String>): ArrayList<MultipartBody.Part> {
    val list = ArrayList<MultipartBody.Part>()
        .apply {
            addAll(
                file.map {
                    val file = File(it)
                    MultipartBody.Part.createFormData(
                        name = "file",
                        filename = file.name,
                        body = file.asRequestBody()
                    )
                }
            )
        }
    return list
}