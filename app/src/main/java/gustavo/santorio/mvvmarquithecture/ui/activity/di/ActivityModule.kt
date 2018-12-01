package gustavo.santorio.mvvmarquithecture.ui.activity.di

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import gustavo.santorio.mvvmarquithecture.scope.ActivityScope
import gustavo.santorio.mvvmarquithecture.viewmodel.BaseViewModel

@Module
class ActivityModule {

    @ActivityScope
    @Provides
    fun providesViewModel(activity: AppCompatActivity, vmClass : Class<BaseViewModel>) : BaseViewModel =  ViewModelProviders.of(activity).get(vmClass)
}