package org.redciudadana.monitorlegislativo.screens.diputado

import org.redciudadana.monitorlegislativo.utils.mvp.IPresenter
import org.redciudadana.monitorlegislativo.utils.mvp.IView

object DiputadoContract {
    interface View: IView {
        fun showProfile()
    }

    interface Presenter: IPresenter<View>
}