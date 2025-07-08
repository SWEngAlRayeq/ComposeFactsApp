package app.facts.di

import app.facts.data.api.FactApi
import app.facts.data.repository.FactRepositoryImpl
import app.facts.domain.repository.FactRepository
import app.facts.domain.usecase.GetRandomFactUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFactApi(): FactApi {
        return Retrofit.Builder()
            .baseUrl("https://uselessfacts.jsph.pl/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build()
            )
            .build()
            .create(FactApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFactRepository(api: FactApi): FactRepository {
        return FactRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetRandomFactUseCase(factRepository: FactRepository): GetRandomFactUseCase {
        return GetRandomFactUseCase(factRepository)
    }



}