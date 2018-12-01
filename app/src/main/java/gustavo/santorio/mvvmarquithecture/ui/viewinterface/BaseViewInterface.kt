package gustavo.santorio.mvvmarquithecture.ui.viewinterface

import gustavo.santorio.mvvmarquithecture.broadcast.BaseBroadcastReceiver

interface BaseViewInterface {
    fun showErrorDialog(s: String)
    fun showLoading()
    fun finishView()
    fun logoutUser()
    fun setButtonCheckedAnimation()
    fun setButtonErrorAnimation()
    fun resetButton()
    fun registerBroadcast(vararg receivers: BaseBroadcastReceiver)
    fun unregisterBroadcast(vararg receivers: BaseBroadcastReceiver)
    fun setInternetError(message : String)
    fun removeInternetError()
}
