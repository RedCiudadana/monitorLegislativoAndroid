package org.redciudadana.monitorlegislativo.screens.diputado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_diputado.*
import org.redciudadana.monitorlegislativo.R
import org.redciudadana.monitorlegislativo.data.models.Profile
import org.redciudadana.monitorlegislativo.screens.main.MainView
import org.redciudadana.monitorlegislativo.utils.glide.GlideApp
import org.redciudadana.monitorlegislativo.utils.glide.RoundCornerTransformation
import org.redciudadana.monitorlegislativo.utils.mvp.BaseFragment

class DiputadoFragment: BaseFragment<DiputadoContract.View, DiputadoContract.Presenter, MainView>(), DiputadoContract.View {

    override var mPresenter: DiputadoContract.Presenter = DiputadoPresenter()

    override fun setTitle() {
        mActivityView?.setTitle("Diputado")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_diputado, container, false)
    }

    private fun getProfile(): Profile {
        return arguments?.getParcelable(MainView.ARG_DIPUTADO) as Profile
    }


    override fun showProfile() {
        val profile = getProfile()

        GlideApp
            .with(context!!)
            .load(profile.fotoUrl)
            .transform(RoundCornerTransformation(context!!.resources))
            .into(diputado_face_image)
        GlideApp
            .with(context!!)
            .load(profile.fotoUrlPartido)
            .into(diputado_partido_image)

        diputado_name.text = profile.nombre
        diputado_department.text = profile.distrito

    }

}