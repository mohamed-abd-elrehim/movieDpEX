package banquemisr.moviedp.di

import banquemisr.moviedp.ui.theme.NavControllerHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
 object NavControllerHolderModule {
    @Provides
    @Singleton
    fun provideNavControllerHolder(): NavControllerHolder = NavControllerHolder()


}
