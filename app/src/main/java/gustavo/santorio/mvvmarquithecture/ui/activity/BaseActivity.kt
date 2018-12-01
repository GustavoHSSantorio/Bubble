package gustavo.santorio.mvvmarquithecture.ui.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleRegistry
import android.content.*
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import gustavo.santorio.mvvmarquithecture.broadcast.BaseBroadcastReceiver
import gustavo.santorio.mvvmarquithecture.ui.activity.di.ActivityComponent
import gustavo.santorio.mvvmarquithecture.ui.activity.di.DaggerActivityComponent
import gustavo.santorio.mvvmarquithecture.ui.viewinterface.BaseViewInterface
import gustavo.santorio.mvvmarquithecture.viewmodel.BaseViewModel
import javax.inject.Inject

@SuppressLint("Registered")
open class BaseActivity(var vmClass : Class<*>) : AppCompatActivity(), BaseViewInterface {

    @Inject
    lateinit var viewModel : BaseViewModel

    val component : ActivityComponent by lazy {
        DaggerActivityComponent.builder().activity(this).context(this).vmClass(vmClass as Class<BaseViewModel>).build()
    }

    override fun setInternetError(message : String) {
    }

    override fun removeInternetError() {
    }

    override fun unregisterBroadcast(vararg receivers: BaseBroadcastReceiver) {
        receivers.forEach {
            LocalBroadcastManager.getInstance(this)
                    .unregisterReceiver(it)
        }
    }

    override fun registerBroadcast(vararg receivers: BaseBroadcastReceiver) {
        receivers.forEach { broadcast ->
            val filter = IntentFilter()
            broadcast.broadcastType!!.forEach {
                filter.addAction(it)
            }
            registerReceiver(broadcast, filter)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        viewModel.view = this
        lifecycle.addObserver(viewModel)
        (lifecycle as LifecycleRegistry).markState(lifecycle.currentState)
    }

    override fun showErrorDialog(s: String) {
    }

    override fun showLoading() {

    }

    override fun finishView() {
        finish()
    }

    override fun logoutUser() {

    }

    override fun setButtonCheckedAnimation() {

    }

    override fun resetButton() {

    }

    override fun setButtonErrorAnimation() {

    }

    override fun startActivity(intent: Intent) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//            super.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
//        else
            super.startActivity(intent)
    }
}