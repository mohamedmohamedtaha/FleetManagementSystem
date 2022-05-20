package com.example.fleetmanagement.hilt.module

import com.example.fleetmanagement.data.URLFactory
import com.example.fleetmanagement.data.api.AuthenticationService
import com.example.fleetmanagement.data.api.UserLiveDataSource
import com.example.fleetmanagement.data.api.UserRepository
import com.example.fleetmanagement.data.datastore.DataStoreRepository
import com.example.fleetmanagement.utils.Constants.ACCEPT_LANGUAGE
import com.example.fleetmanagement.utils.Constants.AUTHORIZATION
import com.example.fleetmanagement.utils.Constants.BEARER
import com.example.fleetmanagement.utils.Constants.LANGUAGE_ENGLISH
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun baseUrlProvide() = URLFactory.URL

    @Provides
    @Singleton
    internal fun provideHeaderInterceptor(repository: DataStoreRepository): Interceptor {
        fun getAccess() = runBlocking { repository.getAccess() }
        fun getLanguage() = runBlocking { repository.getLanguage() }
        return Interceptor { chain ->
            if (getAccess() != null) {
                val build = chain.request().newBuilder()
                    .addHeader(AUTHORIZATION, BEARER + getAccess())
                    .addHeader(ACCEPT_LANGUAGE, getLanguage() ?: LANGUAGE_ENGLISH).build()
                chain.proceed(build)
            } else {
                val build = chain.request().newBuilder()
                    .addHeader(
                        ACCEPT_LANGUAGE,
                        getLanguage() ?: LANGUAGE_ENGLISH
                    ).build()
                chain.proceed(build)
            }
        }
    }

    @Provides
    @Singleton
    fun okHttpClientProvide(headerInterceptor: Interceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun gsonConverterFactoryProvide(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun rxJava2CallAdapterFactoryProvide(): RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun retrofitProvider(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        BASE_URL: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()

    }

    @Provides
    @Singleton
    fun apiServiceProvide(retrofit: Retrofit) = retrofit.create(AuthenticationService::class.java)

    @Provides
    @Singleton
    fun userLiveDataSourceProvide(userLiveDataSource: UserLiveDataSource): UserRepository =
        userLiveDataSource
}









