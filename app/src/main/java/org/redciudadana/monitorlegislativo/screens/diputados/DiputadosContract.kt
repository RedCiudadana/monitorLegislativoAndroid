package org.redciudadana.monitorlegislativo.screens.diputados

import org.redciudadana.monitorlegislativo.data.models.Profile
import org.redciudadana.monitorlegislativo.utils.mvp.IPresenter
import org.redciudadana.monitorlegislativo.utils.mvp.IView

object DiputadosContract {

    interface View: IView {
        fun showCandidatesList(list: List<Profile>)
        fun onCandidateSelected(profile: Profile)
    }

    interface Presenter: IPresenter<View> {
        fun onCandidateSelected(profile: Profile)
    }
}
