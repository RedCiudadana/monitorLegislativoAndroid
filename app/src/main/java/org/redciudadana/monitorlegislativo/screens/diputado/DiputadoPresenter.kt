package org.redciudadana.monitorlegislativo.screens.diputado

import android.view.View
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
            1 -> mView?.showHistory(view, profile)
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


}