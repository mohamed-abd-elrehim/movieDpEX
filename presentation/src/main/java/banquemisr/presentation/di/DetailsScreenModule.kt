package banquemisr.presentation.di

import banquemisr.core.util.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailsScreenModule {


    @Provides
    @Singleton
    @Named("DetailsScreenLogger")
    fun provideLogger(): Logger {
        return Logger(
            tag = "DetailsScreenLogger",
            isDebug = true
        )

    }


}