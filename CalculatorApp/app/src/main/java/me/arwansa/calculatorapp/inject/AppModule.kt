package me.arwansa.calculatorapp.inject

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.arwansa.calculatorapp.BuildConfig
import me.arwansa.calculatorapp.data.remote.CalculatorRemoteDataSource
import me.arwansa.calculatorapp.data.remote.CalculatorService
import me.arwansa.calculatorapp.data.repository.CalculatorRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCalculatorService(retrofit: Retrofit): CalculatorService =
        retrofit.create(CalculatorService::class.java)

    @Singleton
    @Provides
    fun provideCalculatorRemoteDataSource(calculatorService: CalculatorService) =
        CalculatorRemoteDataSource(calculatorService)

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: CalculatorRemoteDataSource
    ) = CalculatorRepository(remoteDataSource)

}