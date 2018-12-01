package gustavo.santorio.mvvmarquithecture.service

import android.app.*
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import gustavo.santorio.mvvmarquithecture.application.BaseApplication
import gustavo.santorio.mvvmarquithecture.repository.BaseRepository
import gustavo.santorio.mvvmarquithecture.service.di.DaggerServiceComponent
import gustavo.santorio.mvvmarquithecture.service.di.ServiceComponent
import gustavo.santorio.mvvmarquithecture.sharedpreference.PreferenceHelper
import javax.inject.Inject
import kotlin.reflect.KClass


abstract class BaseService(repositoryClass: KClass<*>?) : Service() {

    val component : ServiceComponent by lazy {
        DaggerServiceComponent.builder().repositoryClass(repositoryClass as KClass<BaseRepository>).context(BaseApplication.app).build()
    }

    @Inject
    protected lateinit var preferenceHelper: PreferenceHelper

    @Inject
    protected lateinit var repository: BaseRepository

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}