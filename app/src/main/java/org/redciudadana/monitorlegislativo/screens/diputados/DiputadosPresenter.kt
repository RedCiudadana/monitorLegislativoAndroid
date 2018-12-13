package org.redciudadana.monitorlegislativo.screens.diputados

import org.redciudadana.monitorlegislativo.R
import org.redciudadana.monitorlegislativo.data.api.Api
import org.redciudadana.monitorlegislativo.data.models.Profile
import org.redciudadana.monitorlegislativo.utils.mvp.BasePresenter

class DiputadosPresenter: BasePresenter<DiputadosContract.View>(), DiputadosContract.Presenter {
    override fun onViewCreated() {
        mView?.setTitle()
        mView?.showLoading()
        Api.getProfiles(mView!!.getContext()!!) { profileList, throwable ->
            if (throwable != null) {
                val profiles = mView?.getContext()?.getString(R.string.error_profiles)
                if (profiles != null) {
                    mView?.showError(profiles)
                }
            } else if (profileList != null){
                mView?.showCandidatesList(profileList)
            }
            mView?.hideLoading()
        }
    }

    override fun onCandidateSelected(profile: Profile) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}