package gustavo.santorio.mvvmarquithecture.service.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import gustavo.santorio.mvvmarquithecture.repository.BaseRepository
import gustavo.santorio.mvvmarquithecture.scope.ServiceScope
import gustavo.santorio.mvvmarquithecture.service.BaseService
import kotlin.reflect.KClass


@ServiceScope
@Component(modules = arrayOf(ServiceModule::class))
interface ServiceComponent {

    fun inject(baseService: BaseService)

    @Component.Builder
    interface Builder {
        fun build() : ServiceComponent

        @BindsInstance
        fun repositoryClass(repositoryClass : KClass<BaseRepository>) : Builder

        @BindsInstance
        fun context(context : Context) : Builder

    }
}