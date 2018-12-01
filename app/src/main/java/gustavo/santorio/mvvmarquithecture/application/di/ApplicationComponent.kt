package gustavo.santorio.mvvmarquithecture.application.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import gustavo.santorio.mvvmarquithecture.application.BaseApplication
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(appliction : BaseApplication)

    @Component.Builder
    interface Builder{
        fun build() : ApplicationComponent

        @BindsInstance
        fun application(application : BaseApplication) : Builder

        @BindsInstance
        fun context(context: Context) : Builder
    }
}