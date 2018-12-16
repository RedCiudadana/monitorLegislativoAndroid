package org.redciudadana.monitorlegislativo.screens.diputado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.alexvasilkov.foldablelayout.UnfoldableView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_diputado.*
import kotlinx.android.synthetic.main.fragment_diputado_general_info.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        diputado_information_list.adapter = DiputadoOptionsAdapter(this)
        initUnfoldable()
    }

    private fun initUnfoldable() {
        touch_interceptor.isClickable = false
        details_layout.visibility = INVISIBLE
        unfoldable_view.setOnFoldingListener(object : UnfoldableView.SimpleFoldingListener() {
            override fun onUnfolding(unfoldableView: UnfoldableView?) {
                touch_interceptor.isClickable = true
                details_layout.visibility = VISIBLE
            }

            override fun onUnfolded(unfoldableView: UnfoldableView?) {
                touch_interceptor.isClickable = false
            }

            override fun onFoldingBack(unfoldableView: UnfoldableView?) {
                touch_interceptor.isClickable = true
            }

            override fun onFoldedBack(unfoldableView: UnfoldableView?) {
                touch_interceptor.isClickable = false
                details_layout.visibility = INVISIBLE
            }
        })
        mActivityView?.setOnBackListener(this::onBackPressed)
        diputado_show_overview.setOnClickListener {
            unfoldable_view.foldBack()
        }
    }

    private fun onBackPressed(): Boolean {
        if (unfoldable_view.isUnfolded || unfoldable_view.isUnfolding) {
            unfoldable_view.foldBack()
            return true
        }
        return false
    }

    override fun showProfile(profile: Profile) {
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
        button_facebook.setOnClickListener { mPresenter.onFacebookPress() }
        button_twitter.setOnClickListener { mPresenter.onTwitterPress() }
        button_call.setOnClickListener { mPresenter.onPhonePress() }
    }

    override fun onOptionPress(view: View, position: Int?) {
        mPresenter.onOptionPress(view, position)
    }


    override fun showGeneralInformation(view: View, profile: Profile) {
        inflateIntoDetails(R.layout.fragment_diputado_general_info)
        clearFindViewByIdCache()
        diputado_general_info_text.text = profile.historialpolitico
        unfoldDetails(view)
    }

    override fun showHistory(view: View, profile: Profile) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAssistance(view: View, profile: Profile) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showVotes(view: View, profile: Profile) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun inflateIntoDetails(layout: Int) {
        details_content.removeAllViews()
        LayoutInflater.from(context).inflate(layout, details_content, true)
    }

    private fun unfoldDetails(view: View) {
        unfoldable_view.unfold(view, details_layout)
    }


}