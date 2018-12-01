package gustavo.santorio.mvvmarquithecture.ui.fragment.di

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import gustavo.santorio.mvvmarquithecture.scope.FragmentScope
import gustavo.santorio.mvvmarquithecture.viewmodel.BaseViewModel

@Module
class FragmentModule {

    @FragmentScope
    @Provides
    fun providesViewModel(fragment: Fragment, vmClass : Class<BaseViewModel>) : BaseViewModel =  ViewModelProviders.of(fragment).get(vmClass)
}