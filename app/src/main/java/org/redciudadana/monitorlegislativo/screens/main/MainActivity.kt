package org.redciudadana.monitorlegislativo.screens.main

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import org.redciudadana.monitorlegislativo.R
import org.redciudadana.monitorlegislativo.data.models.Profile
import org.redciudadana.monitorlegislativo.screens.diputado.DiputadoFragment
import org.redciudadana.monitorlegislativo.screens.diputados.DiputadosContract
import org.redciudadana.monitorlegislativo.screens.diputados.DiputadosFragment
import org.redciudadana.monitorlegislativo.screens.menu.MenuFragment
import org.redciudadana.monitorlegislativo.screens.news.NewsFragment
import org.redciudadana.monitorlegislativo.screens.representant.RepresentantFragment
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity(), MainView {


    private var mDrawerToggle: ActionBarDrawerToggle? = null
    private var firstAnimation = true
    private var onBackListener: (() -> Boolean)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeDrawer()
        if (savedInstanceState == null) {
            showMainMenu()
        }

    }


    private fun initializeDrawer() {
        setSupportActionBar(toolbar)
        mDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer_layout,
            R.string.drawer_open,
            R.string.drawer_close
        ) {}

        drawer_layout.addDrawerListener(mDrawerToggle as ActionBarDrawerToggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        mDrawerToggle?.syncState()
        setDrawerNavigationListener()
    }

    private fun setDrawerNavigationListener() {
        drawer_navigation.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.drawer_profiles-> showDiputados()
                R.id.drawer_representant -> showRepresentant()
                R.id.drawer_congress_data -> showCongressData()
                R.id.drawer_news -> showNews()
                R.id.drawer_privacy_policy -> showPrivacyPolicy()
                else -> {
                    return@setNavigationItemSelectedListener false
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        mDrawerToggle?.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mDrawerToggle?.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (mDrawerToggle?.onOptionsItemSelected(item) == true) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager
            .beginTransaction()
        if (firstAnimation) {
            firstAnimation = false
        } else {
            transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
        }
        transaction.replace(R.id.main_container, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commitAllowingStateLoss()
        drawer_layout.closeDrawer(GravityCompat.START)

    }

    override fun setTitle(@StringRes title: Int) {
        toolbar.setTitle(title)
    }

    override fun setTitle(title: String) {
        toolbar.title = title
    }



    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    override fun showMainMenu() {
        val fragment = MenuFragment()
        changeFragment(fragment, false)
    }

    override fun showDiputados() {
        val fragment = DiputadosFragment()
        changeFragment(fragment, true)
    }

    override fun showDiputados(district: String) {
        val fragment = DiputadosFragment()
        val args = Bundle()
        args.putString(DiputadosContract.DISTRICT_ARG, district)
        fragment.arguments = args
        changeFragment(fragment, true)
    }

    override fun showDiputado(profile: Profile) {
        val fragment = DiputadoFragment()
        val arguments = Bundle()
        arguments.putParcelable(MainView.ARG_DIPUTADO, profile)
        fragment.arguments = arguments
        changeFragment(fragment, true)
    }

    override fun showCongressData() {
        showError("Próximamente", "")
    }

    override fun showRepresentant() {
        val fragment = RepresentantFragment()
        changeFragment(fragment, true)
    }

    override fun showNews() {
        val fragment = NewsFragment()
        changeFragment(fragment, true)
    }

    override fun showPrivacyPolicy() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://redciudadana.org/privacy-policy-monitor-legislativo"))
        startActivity(intent)
    }

    override fun showError(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Aceptar") { _, _ -> }
            .show()

    }

    override fun setOnBackListener(listener: () -> Boolean) {
        onBackListener = listener
    }

    override fun onBackPressed() {
        if (onBackListener?.invoke() != true) {
            super.onBackPressed()
        }
    }
}
