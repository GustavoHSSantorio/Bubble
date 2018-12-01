package gustavo.santorio.mvvmarquithecture.service.di

import android.content.Context
import dagger.Module
import dagger.Provides
import gustavo.santorio.mvvmarquithecture.repository.BaseRepository
import gustavo.santorio.mvvmarquithecture.scope.ServiceScope
import gustavo.santorio.mvvmarquithecture.sharedpreference.PreferenceHelper
import kotlin.reflect.KClass


@Module
class ServiceModule {

    @Provides
    @ServiceScope
    fun providesSharedPreferences(context: Context) = PreferenceHelper(context)

    @Provides
    @ServiceScope
    fun providesRepository(repositoryClass : KClass<BaseRepository>) : BaseRepository {
        val clazz = Class.forName(repositoryClass.qualifiedName)
        return clazz.newInstance() as BaseRepository
    }
}