package com.kevinserrano.apps.leaguenow.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.kevinserrano.apps.leaguenow.ui.activities.HomeActivity

/**
 * Created by Kevin Serrano 28/08/21
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({ HomeActivity.startActivity(this) }, 2000)
    }

}