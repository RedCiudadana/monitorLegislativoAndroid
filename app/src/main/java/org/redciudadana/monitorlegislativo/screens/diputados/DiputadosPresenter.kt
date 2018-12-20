package org.redciudadana.monitorlegislativo.screens.diputados

import org.redciudadana.monitorlegislativo.R
import org.redciudadana.monitorlegislativo.data.api.Api
import org.redciudadana.monitorlegislativo.data.api.ModelStorage
import org.redciudadana.monitorlegislativo.data.models.Profile
import org.redciudadana.monitorlegislativo.utils.mvp.BasePresenter

class DiputadosPresenter: BasePresenter<DiputadosContract.View>(), DiputadosContract.Presenter {
    override fun onViewCreated() {
        val district = mView?.getArguments()?.get(DiputadosContract.DISTRICT_ARG) as? String
        mView?.setTitle()
        mView?.showLoading()
        var cachedProfiles: List<Profile>? = null
        mView?.getContext()?.let {
            cachedProfiles = ModelStorage.getProfileListFromStorage(it)
            Api.getProfiles(it) { profileList, throwable ->
                if (throwable != null) {
                    val profiles = mView?.getContext()?.getString(R.string.error_profiles)
                    if (profiles != null) {
                        mView?.showError(profiles)
                    }
                } else if (profileList != null){
                    mView?.showCandidatesList(filterProfiles(district, profileList) as List<Profile>)
                }
                mView?.hideLoading()
            }
        }
        mView?.initCandidatesList(filterProfiles(district, cachedProfiles))

    }

    fun filterProfiles(district: String?, profiles: List<Profile>?): List<Profile>? {
        if (district == null) return profiles
        return profiles?.filter { it.distrito == district }
    }

}