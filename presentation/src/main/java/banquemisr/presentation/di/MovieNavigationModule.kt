package banquemisr.presentation.di

import banquemisr.presentation.navigation.MovieNavigation
import banquemisr.presentation.navigation.MovieNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieNavigationModule {
    @Binds
    @Singleton
    abstract fun MovieNavigation(
        movieNavigationImpl: MovieNavigationImpl
    ): MovieNavigation
}