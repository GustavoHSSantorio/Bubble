package gustavo.santorio.mvvmarquithecture.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

open class BaseBroadcastReceiver: BroadcastReceiver() {

    var broadcastType : Array<String>? = null

    override fun onReceive(context: Context?, intent: Intent?) {

    }
}