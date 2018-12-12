package org.redciudadana.monitorlegislativo.screens.main

import org.redciudadana.monitorlegislativo.utils.views.ActivityView

interface MainView : ActivityView {
    fun showMainMenu()
    fun showCandidates()
    fun showRepresentant()
    fun showCongressData()
    fun showNews()
}