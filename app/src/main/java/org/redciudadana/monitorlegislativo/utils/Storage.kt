package eleccionmp.redciudadana.org.eleccionmp.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by javier on 2/5/18.
 */

object Storage {
    const val PREFERENCES = "shared"
    fun getStringPreference(context: Context, key: String): String? {
        val preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        return preferences.getString(key, null)
    }

    fun setStringPreference(context: Context, key: String, value: String) {
        val preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }
}