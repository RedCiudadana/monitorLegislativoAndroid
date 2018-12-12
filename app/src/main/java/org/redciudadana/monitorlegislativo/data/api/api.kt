package org.redciudadana.monitorlegislativo.data.api

import android.content.Context
import org.redciudadana.monitorlegislativo.data.models.Profile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val BASE_URL = "https://raw.githubusercontent.com/RedCiudadana/Congreso/gh-pages/static-files/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

val api: IApi = retrofit.create(IApi::class.java)

object Api {
    var candidates: List<Profile>? = null

    fun getProfiles(context: Context, callback: ((List<Profile>?, Throwable?) -> Unit)?) {
        if (candidates != null) {
            if (callback != null) callback(candidates, null)
        } else {
            val callResponse = api.getProfiles()
            callResponse.enqueue(object: Callback<List<Profile>> {
                override fun onFailure(call: Call<List<Profile>>, t: Throwable) {
                    val stored = ModelStorage.getProfileListFromStorage(context)
                    if (callback != null) {
                        callback(stored, t)
                    }
                }

                override fun onResponse(call: Call<List<Profile>>, response: Response<List<Profile>>) {
                    val currentCandidates = response.body()
                    if (currentCandidates != null) {
                        ModelStorage.saveProfileListToStorage(context, currentCandidates)
                    }
                    if (callback != null) {
                        callback(currentCandidates, null)
                    }
                }
            })
        }
    }
}