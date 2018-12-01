package gustavo.santorio.mvvmarquithecture.ui.fragment

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.LocalBroadcastManager
import gustavo.santorio.mvvmarquithecture.application.BaseApplication
import gustavo.santorio.mvvmarquithecture.broadcast.BaseBroadcastReceiver
import gustavo.santorio.mvvmarquithecture.exception.ViewModelNameNotProvidedException
import gustavo.santorio.mvvmarquithecture.ui.activity.BaseActivity
import gustavo.santorio.mvvmarquithecture.ui.viewinterface.BaseViewInterface
import gustavo.santorio.mvvmarquithecture.viewmodel.BaseViewModel
import javax.inject.Inject

open class BaseFragment : Fragment(), BaseViewInterface {

    override fun unregisterBroadcast(vararg receivers: BaseBroadcastReceiver) {
        receivers.forEach {
            LocalBroadcastManager.getInstance(activity!!)
                    .unregisterReceiver(it)
        }
    }

    override fun registerBroadcast(vararg receivers: BaseBroadcastReceiver) {
        receivers.forEach { broadcast ->
            val filter = IntentFilter()
            broadcast.broadcastType!!.forEach {
                filter.addAction(it)
            }
            activity!!.registerReceiver(broadcast, filter)
        }
    }

    override fun setInternetError(message: String) {}

    override fun removeInternetError() {}

    override fun setButtonErrorAnimation() {
    }

    override fun setButtonCheckedAnimation() {
    }

    override fun resetButton() {
    }

    override fun finishView() {

    }

    @Inject
    lateinit var viewModel : BaseViewModel

    companion object {
        fun <T : BaseFragment> newInstance(fmClass : Class<*>, vmClass : Class<*>): T {
            val args = Bundle()
            args.putString("className", vmClass.canonicalName)
            val fragment = Class.forName(fmClass.name).newInstance() as BaseFragment
            fragment.setArguments(args)
            return fragment as T
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val className = arguments?.getString("className")
        if(className == null || className.isEmpty() || className.isBlank())
            throw ViewModelNameNotProvidedException(this.javaClass.canonicalName)
//        DaggerFragmentComponent.builder().fragment(this).context(BaseApplication.app).vmClass(Class.forName(className) as Class<BaseViewModel>).build().inject(this)
        lifecycle.addObserver(viewModel)
        viewModel.view = this
        super.onCreate(savedInstanceState)
    }

    override fun showErrorDialog(s: String) {
        (activity as BaseActivity).showErrorDialog(s)
    }

    override fun showLoading() {
    }

    override fun logoutUser() {

    }

}