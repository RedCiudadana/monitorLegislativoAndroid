package org.redciudadana.monitorlegislativo.data.api

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import eleccionmp.redciudadana.org.eleccionmp.utils.Storage
import org.redciudadana.monitorlegislativo.data.models.Profile

object ModelStorage {
    private const val diputadosKey = "diputados"

    fun getProfileListFromStorage(context: Context): List<Profile>? {
        val type = Types.newParameterizedType(List::class.java, Profile::class.java)
        val adapter = Moshi.Builder().build().adapter<List<Profile>>(type)
        val key = diputadosKey
        val stored = Storage.getStringPreference(context, key) ?: return null
        return adapter.fromJson(stored)
    }

    fun saveProfileListToStorage(context: Context, profileList: List<Profile>) {
        val type = Types.newParameterizedType(List::class.java, Profile::class.java)
        val adapter = Moshi.Builder().build().adapter<List<Profile>>(type)
        val key = diputadosKey
        Storage.setStringPreference(context, key, adapter.toJson(profileList))
    }

}