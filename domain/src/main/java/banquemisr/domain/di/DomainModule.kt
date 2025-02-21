package banquemisr.domain.di

import banquemisr.domain.use_case.MovieRepository
import banquemisr.domain.use_case.interactors.FetchMovieDetails
import banquemisr.domain.use_case.interactors.FetchNowPlayingMovies
import banquemisr.domain.use_case.interactors.FetchUpcomingMovies
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
    fun provideFetchNowPlayingMoviesUseCase(movieRepository: MovieRepository): FetchNowPlayingMovies {
        return FetchNowPlayingMovies(movieRepository)
    }

    @Provides
    @Singleton
    fun provideFetchMovieDetailsUseCase(movieRepository: MovieRepository):FetchMovieDetails {
        return FetchMovieDetails(movieRepository)
    }


    @Provides
    @Singleton
    fun provideFetchUpcomingMoviesUseCase(movieRepository: MovieRepository): FetchUpcomingMovies {
        return FetchUpcomingMovies(movieRepository)
    }
}
