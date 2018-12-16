package org.redciudadana.monitorlegislativo.screens.diputados

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_diputados.*
import org.redciudadana.monitorlegislativo.R
import org.redciudadana.monitorlegislativo.data.models.Profile
import org.redciudadana.monitorlegislativo.screens.main.MainView
import org.redciudadana.monitorlegislativo.utils.mvp.BaseFragment

class DiputadosFragment: BaseFragment<DiputadosContract.View, DiputadosContract.Presenter, MainView>(), DiputadosContract.View {

    override var mPresenter: DiputadosContract.Presenter = DiputadosPresenter()
    private lateinit var mAdapter: DiputadosAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_diputados, container, false)
    }

    override fun initCandidatesList(list: List<Profile>?) {
        val mLayoutManager = LinearLayoutManager(context)
        mAdapter = DiputadosAdapter(context!!, this, list)
        diputados_list.setHasFixedSize(true)
        diputados_list.layoutManager = mLayoutManager
        diputados_list.addItemDecoration(
            DividerItemDecoration(
                context,
                mLayoutManager.orientation
            )
        )
        diputados_list.adapter = mAdapter
    }

    override fun setTitle() {
        mActivityView?.setTitle(getString(R.string.title_diputados))
    }

    override fun showCandidatesList(list: List<Profile>) {
        mAdapter.diputados = list
    }

    override fun onCandidateSelected(profile: Profile) {
        mActivityView?.showDiputado(profile)
    }

}