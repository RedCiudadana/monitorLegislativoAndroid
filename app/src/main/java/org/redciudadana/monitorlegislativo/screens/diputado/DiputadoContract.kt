package org.redciudadana.monitorlegislativo.screens.diputado

import android.view.View
import org.redciudadana.monitorlegislativo.data.models.Profile
import org.redciudadana.monitorlegislativo.utils.mvp.IPresenter
import org.redciudadana.monitorlegislativo.utils.mvp.IView

object DiputadoContract {
    val options = arrayOf("Información general", "Historial político", "Asistencia", "Votación")

    interface View: IView {
        fun showProfile(profile: Profile)
        fun onOptionPress(view: android.view.View, position: Int?)
        fun showGeneralInformation(view: android.view.View, profile: Profile)
        fun showHistory(view: android.view.View, profile: Profile)
        fun showAssistance(view: android.view.View, profile: Profile)
        fun showVotes(view: android.view.View, profile: Profile)
    }

    interface Presenter: IPresenter<View> {
        fun onFacebookPress()
        fun onTwitterPress()
        fun onPhonePress()
        fun onOptionPress(view: android.view.View, position: Int?)
    }



}