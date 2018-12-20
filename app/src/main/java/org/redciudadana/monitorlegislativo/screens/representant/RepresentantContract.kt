package org.redciudadana.monitorlegislativo.screens.representant

import org.redciudadana.monitorlegislativo.utils.mvp.IPresenter
import org.redciudadana.monitorlegislativo.utils.mvp.IView

object RepresentantContract {
    interface View: IView {
        fun initDistrictList(list: List<String>?)
        fun updateDistrictList(list: List<String>?)
        fun showDistrictCandidates(district: String)
    }

    interface Presenter: IPresenter<View> {
        fun onDistrictSelect(district: String)
    }


}