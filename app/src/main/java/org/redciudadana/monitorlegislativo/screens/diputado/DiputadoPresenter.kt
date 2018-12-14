package org.redciudadana.monitorlegislativo.screens.diputado

import org.redciudadana.monitorlegislativo.utils.mvp.BasePresenter

class DiputadoPresenter: BasePresenter<DiputadoContract.View>(), DiputadoContract.Presenter {
    override fun onViewCreated() {
        mView?.showProfile()
    }

}