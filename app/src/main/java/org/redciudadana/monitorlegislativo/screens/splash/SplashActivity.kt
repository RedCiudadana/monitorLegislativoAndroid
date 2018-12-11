package org.redciudadana.monitorlegislativo.screens.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import org.redciudadana.monitorlegislativo.screens.main.MainActivity
import org.redciudadana.monitorlegislativo.R

class SplashActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 2000)
    }
}