package banquemisr.data.di

import banquemisr.data.network.repository.MovieRepositoryImpl
import banquemisr.domain.use_case.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
 @Binds
 @Singleton
 abstract fun bindMovieRepository(
  movieRepositoryImpl: MovieRepositoryImpl
 ): IMovieRepository
}