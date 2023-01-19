package com.liau.jetgithub

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import java.lang.Thread.sleep

/**
 * Created by Budiman on 18/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    var keepSplashOnScreen = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition { keepSplashOnScreen }
            setOnExitAnimationListener { it.remove() }
        }
        sleep(2000)
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }
}