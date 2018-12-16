package org.redciudadana.monitorlegislativo.screens.main

import org.redciudadana.monitorlegislativo.data.models.Profile
import org.redciudadana.monitorlegislativo.utils.views.ActivityView

interface MainView : ActivityView {
    fun showMainMenu()
    fun showDiputados()
    fun showDiputado(profile: Profile)
    fun showRepresentant()
    fun showCongressData()
    fun showNews()
    fun setOnBackListener(listener: () -> Boolean)

    companion object {
        const val ARG_DIPUTADO = "diputado"
    }
}