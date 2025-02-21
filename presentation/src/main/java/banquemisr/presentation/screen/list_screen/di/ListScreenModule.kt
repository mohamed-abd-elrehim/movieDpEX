package banquemisr.presentation.screen.list_screen.di

import banquemisr.core.util.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
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