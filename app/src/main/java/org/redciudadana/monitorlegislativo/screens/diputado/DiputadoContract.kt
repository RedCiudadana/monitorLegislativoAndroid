package org.redciudadana.monitorlegislativo.screens.diputado

import org.redciudadana.monitorlegislativo.data.models.Assistance
import org.redciudadana.monitorlegislativo.data.models.HistoryEntry
import org.redciudadana.monitorlegislativo.data.models.Profile
import org.redciudadana.monitorlegislativo.utils.mvp.IPresenter
import org.redciudadana.monitorlegislativo.utils.mvp.IView

object DiputadoContract {
    val options = arrayOf("Información general", "Historial político", "Asistencia", "Votación")

    interface View: IView {
        fun showProfile(profile: Profile)
        fun onOptionPress(view: android.view.View, position: Int?)
        fun showGeneralInformation(view: android.view.View, profile: Profile)
        fun showHistory(view: android.view.View, historyEntryList: List<HistoryEntry>?)
        fun updateHistory(historyEntryList: List<HistoryEntry>?)
        fun showAssistance(view: android.view.View, assistance: Assistance?)
        fun updateAssistance(assistance: Assistance?)
        fun showVotes(view: android.view.View, profile: Profile)
    }

    interface Presenter: IPresenter<View> {
        fun onFacebookPress()
        fun onTwitterPress()
        fun onPhonePress()
        fun onOptionPress(view: android.view.View, position: Int?)
    }



}