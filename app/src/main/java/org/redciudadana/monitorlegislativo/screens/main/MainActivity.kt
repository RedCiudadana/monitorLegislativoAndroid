package org.redciudadana.monitorlegislativo.screens.main

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.redciudadana.monitorlegislativo.R
import org.redciudadana.monitorlegislativo.data.models.Profile
import org.redciudadana.monitorlegislativo.screens.diputado.DiputadoFragment
import org.redciudadana.monitorlegislativo.screens.diputados.DiputadosFragment
import org.redciudadana.monitorlegislativo.screens.menu.MenuFragment

class MainActivity : AppCompatActivity(), MainView {


    private var mDrawerToggle: ActionBarDrawerToggle? = null
    private var firstAnimation = true

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
                R.id.drawer_candidatos-> {
                    showDiputados()
                }
//                R.id.option_commission -> {
//                    showCommission()
//                }
//                R.id.option_news -> {
//                    showNews()
//                }
//                R.id.option_election_process -> {
//                    showElectionProcess()
//                }
//                R.id.option_contact -> {
//                    showContact()
//                }
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

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        mDrawerToggle?.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (mDrawerToggle?.onOptionsItemSelected(item)!!) {
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
        drawer_layout.closeDrawer(Gravity.START)

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

    override fun showDiputado(profile: Profile) {
        val fragment = DiputadoFragment()
        val arguments = Bundle()
        arguments.putParcelable(MainView.ARG_DIPUTADO, profile)
        fragment.arguments = arguments
        changeFragment(fragment, true)
    }

    override fun showCongressData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showRepresentant() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNews() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(title: String, message: String) {
        AlertDialog.Builder(this, R.style.Base_ThemeOverlay_AppCompat_Dialog_Alert)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Aceptar") { _, _ -> }
            .show()

    }
}
