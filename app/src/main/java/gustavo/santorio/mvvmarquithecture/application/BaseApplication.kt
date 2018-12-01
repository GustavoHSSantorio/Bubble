package gustavo.santorio.mvvmarquithecture.application

import android.app.Application
import dagger.android.DaggerFragment
import gustavo.santorio.mvvmarquithecture.application.di.ApplicationComponent
import gustavo.santorio.mvvmarquithecture.application.di.DaggerApplicationComponent
import javax.inject.Inject
import gustavo.santorio.mvvmarquithecture.sharedpreference.PreferenceHelper


class BaseApplication : Application() {

    val component : ApplicationComponent by lazy {
        DaggerApplicationComponent.builder().application(this).context(this).build()
    }

    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    override fun onCreate() {
        super.onCreate()
        app = this
        component.inject(this)
    }

    companion object {
        lateinit var app : BaseApplication
    }
}