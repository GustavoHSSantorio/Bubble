package gustavo.santorio.mvvmarquithecture.ui.activity

import android.app.Activity
import gustavo.santorio.mvvmarquithecture.viewmodel.BaseViewModel
import android.content.Intent
import android.media.audiofx.BassBoost
import android.net.Uri
import android.os.Bundle
import gustavo.santorio.mvvmarquithecture.R
import gustavo.santorio.mvvmarquithecture.service.BubbleService
import android.widget.Toast
import android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION
import android.provider.Settings.canDrawOverlays
import android.os.Build
import android.provider.Settings


class MainActivity : BaseActivity(BaseViewModel::class.java) {

    private val CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {

            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName"))
            startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION)
        } else {
            initializeView()
        }
    }
    private fun initializeView() {
        startService(Intent(this@MainActivity, BubbleService::class.java))
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION) {
            if (resultCode == Activity.RESULT_OK) {
                initializeView()
            } else {
                Toast.makeText(this,
                        "Draw over other app permission not available. Closing the application",
                        Toast.LENGTH_SHORT).show()
                finish()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}