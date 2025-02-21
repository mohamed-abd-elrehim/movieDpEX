package banquemisr.data.di

import banquemisr.data.network.api.MovieDbAPIServices
import banquemisr.data.network.client.MovieDPClient
import banquemisr.data.network.remote.MovieRemoteDataSource
import banquemisr.data.network.repository.MovieRepositoryImpl
import banquemisr.domain.use_case.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideMovieDbApiService(): MovieDbAPIServices {
        return MovieDPClient.apiService // From your Retrofit setup
    }


    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(apiService: MovieDbAPIServices): MovieRemoteDataSource {
        return MovieRemoteDataSource(apiService)
    }






}