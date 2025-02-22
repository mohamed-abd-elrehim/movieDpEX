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
object ListScreenModule {


    @Provides
    @Singleton
    @Named("ListScreenLogger")
    fun provideLogger(): Logger {
        return Logger(
            tag = "ListScreenLogger",
            isDebug = true
        )

    }


}