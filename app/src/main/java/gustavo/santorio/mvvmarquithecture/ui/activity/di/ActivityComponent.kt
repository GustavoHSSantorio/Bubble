package gustavo.santorio.mvvmarquithecture.ui.activity.di

import android.content.Context
import android.support.v7.app.AppCompatActivity
import dagger.BindsInstance
import dagger.Component
import gustavo.santorio.mvvmarquithecture.scope.ActivityScope
import gustavo.santorio.mvvmarquithecture.ui.activity.BaseActivity
import gustavo.santorio.mvvmarquithecture.viewmodel.BaseViewModel

@ActivityScope
@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(baseActivity: BaseActivity)

    @Component.Builder
    interface Builder {
        fun build() : ActivityComponent

        @BindsInstance
        fun vmClass(vmClass : Class<BaseViewModel>) : Builder

        @BindsInstance
        fun activity(activity: AppCompatActivity) : Builder

        @BindsInstance
        fun context(context : Context) : Builder

    }
}