package banquemisr.moviedp.di

import banquemisr.moviedp.MovieNavigationImpl
import banquemisr.presentation.navigation.MovieNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieNavigationModule {
    @Binds
    abstract fun MovieNavigation(
        movieNavigationImpl: MovieNavigationImpl
    ): MovieNavigation
}