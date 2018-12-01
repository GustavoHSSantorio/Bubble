package gustavo.santorio.mvvmarquithecture.sharedpreference

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(val context: Context) {

    private val app_prefs: SharedPreferences

    init {
        app_prefs = context.getSharedPreferences("Bubble", Context.MODE_PRIVATE)
    }

    fun putProfileType(profileType: Int) {
        val edit = app_prefs.edit()
        edit.putInt("profileType", profileType)
        edit.commit()
    }

    fun getProfileType(): Int {
        return app_prefs.getInt("profileType", 1)
    }

    fun putSuggestedProfileType(profileType: Int) {
        val edit = app_prefs.edit()
        edit.putInt("suggestedProfileType", profileType)
        edit.commit()
    }

    fun getSuggestedProfileType(): Int {
        return app_prefs.getInt("suggestedProfileType", 1)
    }

    fun putFlowInSingUp(isInSingUp : Boolean) {
        val edit = app_prefs.edit()
        edit.putBoolean("isInSingUp", isInSingUp)
        edit.commit()
    }

    fun isFlowInSingUp(): Boolean {
        return app_prefs.getBoolean("isInSingUp", false)
    }
}