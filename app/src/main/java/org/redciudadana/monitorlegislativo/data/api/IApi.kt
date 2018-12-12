package org.redciudadana.monitorlegislativo.data.api

import org.redciudadana.monitorlegislativo.data.models.Profile
import retrofit2.Call
import retrofit2.http.GET


interface IApi {
    @GET("perfil.json")
    fun getProfiles(): Call<List<Profile>>
}