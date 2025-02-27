package banquemisr.data.di

import banquemisr.data.network.remote.IMovieRemoteDataSource
import banquemisr.data.network.remote.MovieRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {
    @Binds
    @Singleton
    abstract fun bindMovieRemote(
        movieRemoteImpl: MovieRemoteDataSourceImpl
    ): IMovieRemoteDataSource
}