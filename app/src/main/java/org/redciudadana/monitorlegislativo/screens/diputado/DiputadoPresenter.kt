package org.redciudadana.monitorlegislativo.screens.diputado

import android.os.Handler
import android.util.Log
import android.view.View
import org.redciudadana.monitorlegislativo.data.api.Api
import org.redciudadana.monitorlegislativo.data.api.ModelStorage
import org.redciudadana.monitorlegislativo.data.models.HistoryEntry
import org.redciudadana.monitorlegislativo.data.models.Profile
import org.redciudadana.monitorlegislativo.screens.main.MainView
import org.redciudadana.monitorlegislativo.utils.mvp.BasePresenter
import org.redciudadana.monitorlegislativo.utils.openUrl


val numberRegex = Regex("""(\d+)""")

class DiputadoPresenter: BasePresenter<DiputadoContract.View>(), DiputadoContract.Presenter {
    val profile: Profile
    get() = mView?.getArguments()?.getParcelable(MainView.ARG_DIPUTADO) as Profile

    override fun onViewCreated() {
        mView?.showProfile(profile)

    }

    override fun onFacebookPress() {
        openUrlOnClick(profile.fb)
    }

    override fun onPhonePress() {
        openUrlOnClick(getPhoneNumberUrl(profile.telefono))
    }

    override fun onTwitterPress() {
        openUrlOnClick(buildTwitterUrl(profile.tw))
    }

    override fun onOptionPress(view: View, position: Int?) {
        when (position) {
            0 -> mView?.showGeneralInformation(view, profile)
            1 -> prepareHistoryAndShow(view)
            2 -> mView?.showAssistance(view, profile)
            3 -> mView?.showVotes(view, profile)
        }
    }

    private fun openUrlOnClick(string: String?) {
        if (string == null) {
            mView?.showError("Información no disponible")
        }
        openUrl(mView?.getContext(), string)
    }

    private fun buildTwitterUrl(twitterAccount: String?): String? {
        if (twitterAccount != null && !twitterAccount.isEmpty()) {
            return String.format("https://twitter.com/%s", twitterAccount)
        }
        return null
    }

    fun getPhoneNumberUrl(rawData: String?): String? {
        if (rawData != null) {
            val result = numberRegex.find(rawData)
            if (result != null) {
                return String.format("tel:%s", result.value)
            }
        }

        return null
    }

    fun prepareHistoryAndShow(view: View) {
        mView?.showLoading()
        mView?.getContext()?.let {
            val cachedHistory = ModelStorage.getHistoryEntryList(it)
            mView?.showHistory(view, filterHistory(cachedHistory, profile))
            Api.getHistoryEntryList(it) { response, error ->
                mView?.hideLoading()
                if (error != null) {
                    mView?.showError("No se pudo cargar la información")
                } else {
                    mView?.updateHistory(filterHistory(response, profile))
                }
            }
        }
    }

    fun filterHistory(list: List<HistoryEntry>?, profile: Profile?): List<HistoryEntry>? {
        return list
            ?.sortedBy { it.ano?.toInt() }
            ?.filter { it.perfil == profile?.id }
    }

}