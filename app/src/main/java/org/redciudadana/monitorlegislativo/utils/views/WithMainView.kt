package org.redciudadana.monitorlegislativo.utils.views

import android.app.Activity
import android.support.annotation.StringRes

/**
 * Created by javier on 1/24/18.
 */

interface ActivityView {
    fun showLoading()
    fun hideLoading()
    fun setTitle(@StringRes title: Int)
    fun setTitle(title: String)
    fun showError(title: String, message: String)
}