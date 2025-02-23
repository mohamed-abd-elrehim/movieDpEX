package banquemisr.data.di

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
  abstract class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository {
        return movieRepositoryImpl
    }

}

