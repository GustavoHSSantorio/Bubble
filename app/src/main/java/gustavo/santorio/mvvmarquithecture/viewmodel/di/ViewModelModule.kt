package gustavo.santorio.mvvmarquithecture.viewmodel.di

import android.content.Context
import gustavo.santorio.mvvmarquithecture.scope.ViewModelScope
import dagger.Module
import dagger.Provides
import gustavo.santorio.mvvmarquithecture.repository.BaseRepository
import gustavo.santorio.mvvmarquithecture.sharedpreference.PreferenceHelper
import kotlin.reflect.KClass

@Module
class ViewModelModule {

    @Provides
    @ViewModelScope
    fun providesRepository(repositoryClass : KClass<BaseRepository>) : BaseRepository{
        val clazz = Class.forName(repositoryClass.qualifiedName)
        return clazz.newInstance() as BaseRepository
    }

    @Provides
    @ViewModelScope
    fun providesSharedPreferences(context : Context) : PreferenceHelper = PreferenceHelper(context)
}