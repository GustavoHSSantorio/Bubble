package gustavo.santorio.mvvmarquithecture.application.di

import android.content.Context
import dagger.Module
import dagger.Provides
import gustavo.santorio.mvvmarquithecture.application.BaseApplication
import gustavo.santorio.mvvmarquithecture.sharedpreference.PreferenceHelper
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun providerApplication(moveApplication: BaseApplication) = moveApplication

    @Provides
    @Singleton
    fun providesPreferenceHelper(context: Context) = PreferenceHelper(context)

}