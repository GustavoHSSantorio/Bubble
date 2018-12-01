package gustavo.santorio.mvvmarquithecture.viewmodel.di

import android.content.Context
import gustavo.santorio.mvvmarquithecture.scope.ViewModelScope
import dagger.BindsInstance
import dagger.Component
import gustavo.santorio.mvvmarquithecture.repository.BaseRepository
import gustavo.santorio.mvvmarquithecture.viewmodel.BaseViewModel
import kotlin.reflect.KClass

@ViewModelScope
@Component(modules = arrayOf(ViewModelModule::class))
interface ViewModelComponent {

    fun inject(baseViewModel: BaseViewModel)

    @Component.Builder
    interface Builder{
        fun build() : ViewModelComponent

        @BindsInstance
        fun repositoryClass(repositoryClass : KClass<BaseRepository>) : Builder

        @BindsInstance
        fun context(context : Context) : Builder
    }
}