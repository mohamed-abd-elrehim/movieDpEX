package banquemisr.domain.di

import banquemisr.domain.use_case.MovieRepository
import banquemisr.domain.use_case.interactors.GetMovieDetails
import banquemisr.domain.use_case.interactors.GetNowPlayingMovies
import banquemisr.domain.use_case.interactors.GetUpcomingMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {



    @Provides
    @Singleton
    fun provideGetNowPlayingMoviesUseCase(movieRepository: MovieRepository): GetNowPlayingMovies {
        return GetNowPlayingMovies(movieRepository)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(movieRepository: MovieRepository): GetMovieDetails {
        return GetMovieDetails(movieRepository)
    }


    @Provides
    @Singleton
    fun provideGetUpcomingMoviesUseCase(movieRepository: MovieRepository): GetUpcomingMovies {
        return GetUpcomingMovies(movieRepository)
    }
}
