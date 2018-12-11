package org.redciudadana.monitorlegislativo

import android.app.Application
import com.twitter.sdk.android.core.Twitter

class Application: Application() {

    override fun onCreate() {
        super.onCreate()
        Twitter.initialize(this)
    }
}