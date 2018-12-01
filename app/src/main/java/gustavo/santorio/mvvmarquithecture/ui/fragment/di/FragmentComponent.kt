package gustavo.santorio.mvvmarquithecture.ui.fragment.di

import android.content.Context
import android.support.v4.app.Fragment
import dagger.BindsInstance
import dagger.Component
import gustavo.santorio.mvvmarquithecture.scope.FragmentScope
import gustavo.santorio.mvvmarquithecture.ui.fragment.BaseFragment
import gustavo.santorio.mvvmarquithecture.viewmodel.BaseViewModel

@FragmentScope
@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(baseFragment: BaseFragment)

    @Component.Builder
    interface Builder {
        fun build() : FragmentComponent

        @BindsInstance
        fun vmClass(vmClass : Class<BaseViewModel>) : Builder

        @BindsInstance
        fun fragment(fragment: Fragment) : Builder

        @BindsInstance
        fun context(context : Context) : Builder
    }
}