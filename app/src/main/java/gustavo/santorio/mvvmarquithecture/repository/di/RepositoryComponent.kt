package gustavo.santorio.mvvmarquithecture.repository.di

import dagger.BindsInstance
import dagger.Component
import gustavo.santorio.mvvmarquithecture.remote.APIService
import gustavo.santorio.mvvmarquithecture.repository.BaseRepository
import gustavo.santorio.mvvmarquithecture.scope.RepositoryScope
import kotlin.reflect.KClass

@RepositoryScope
@Component(modules = arrayOf(RepositoryModule::class))
interface RepositoryComponent{

    fun inject(baseRepository: BaseRepository)

    @Component.Builder
    interface Builder{
        fun build() : RepositoryComponent

        @BindsInstance
        fun retrofitClass(retrofitClass : KClass<APIService>) : Builder
    }
}