package org.redciudadana.monitorlegislativo.data.api

import org.redciudadana.monitorlegislativo.data.models.Assistance
import org.redciudadana.monitorlegislativo.data.models.HistoryEntry
import org.redciudadana.monitorlegislativo.data.models.Profile
import retrofit2.Call
import retrofit2.http.GET


interface IApi {
    @GET("perfil.json")
    fun getProfiles(): Call<List<Profile>>

    @GET("historial.json")
    fun getHistory(): Call<List<HistoryEntry>>

    @GET("asistencia.json")
    fun getAssistance(): Call<List<Assistance>>
}