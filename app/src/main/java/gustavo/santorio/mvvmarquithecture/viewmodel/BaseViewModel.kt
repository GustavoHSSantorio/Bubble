package gustavo.santorio.mvvmarquithecture.viewmodel

import android.arch.lifecycle.*
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Handler
import gustavo.santorio.mvvmarquithecture.application.BaseApplication
import gustavo.santorio.mvvmarquithecture.broadcast.BaseBroadcastReceiver
import gustavo.santorio.mvvmarquithecture.repository.BaseRepository
import gustavo.santorio.mvvmarquithecture.sharedpreference.PreferenceHelper
import gustavo.santorio.mvvmarquithecture.ui.viewinterface.BaseViewInterface
import gustavo.santorio.mvvmarquithecture.viewmodel.di.DaggerViewModelComponent
import gustavo.santorio.mvvmarquithecture.viewmodel.di.ViewModelComponent
import javax.inject.Inject
import kotlin.reflect.KClass

public open class BaseViewModel(repositoryClass: KClass<*>?) : ViewModel(), LifecycleObserver {

    constructor() : this(BaseRepository::class)

    companion object {
        public val CHECK_ANIMATION_DURATION : Long = 1000
    }

    val component : ViewModelComponent by lazy {
        DaggerViewModelComponent.builder().repositoryClass(repositoryClass as KClass<BaseRepository>).context(BaseApplication.app).build()
    }

    val internetReceiver = object : BaseBroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            val cm = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                when(cm.getActiveNetworkInfo()){
                    NetworkInfo.State.DISCONNECTED,
                    NetworkInfo.State.SUSPENDED,
                    NetworkInfo.State.DISCONNECTING,
                    null ->{
                        view.setInternetError("Detectamos um problema de internet. Estamos tentando reconectar...")
                    }
                    else -> {
                        view.removeInternetError()
                    }
                }
        }
    }

    @Inject
    lateinit var repository : BaseRepository

    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    lateinit var view : BaseViewInterface

    init {
        component.inject(this)
    }

    val logoutMutableLiveData by lazy {
        val liveData = MutableLiveData<String>()
        liveData.observeForever {
            logoutUser()
        }
        liveData
    }

    val errorMutableLiveData by lazy {
        val liveData = MutableLiveData<String>()
        liveData.observeForever {
            view.setButtonErrorAnimation()
            Handler().postDelayed({
                view.showErrorDialog(it!!)
                view.resetButton()
            },CHECK_ANIMATION_DURATION)
        }
        liveData
    }

    open fun logoutUser(){
        view.logoutUser()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate(){
        repository.logoutMutableLiveData = logoutMutableLiveData
        repository.errorLiveData = errorMutableLiveData
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume(){}

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart(){
        internetReceiver.broadcastType = arrayOf("android.net.conn.CONNECTIVITY_CHANGE")
        view.registerBroadcast(internetReceiver)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop(){
        view.unregisterBroadcast(internetReceiver)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy(){
        view.unregisterBroadcast(internetReceiver)
    }
}