package banquemisr.data.di

import banquemisr.data.network.api.MovieDbAPIServices
import banquemisr.data.network.client.MovieDPClient
import banquemisr.data.network.remote.MovieRemoteDataSource
import banquemisr.data.network.repository.MovieRepositoryImpl
import banquemisr.domain.use_case.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

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

    @Provides
    @Singleton
    fun provideMovieRepository(remoteDataSource: MovieRemoteDataSource): MovieRepository {
        return MovieRepositoryImpl(remoteDataSource)
    }


}