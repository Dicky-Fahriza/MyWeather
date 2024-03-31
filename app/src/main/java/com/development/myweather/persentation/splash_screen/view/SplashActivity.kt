package com.development.myweather

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.development.myweather.persentation.home_screen.view.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Handler untuk menunda beralih ke MainActivity selama 4 detik (4000 milidetik)
        Handler().postDelayed({
            val mainIntent = Intent(this, HomeActivity::class.java)
            startActivity(mainIntent)
            finish() // Menutup activity splash screen agar tidak bisa kembali lagi dengan menekan tombol back
        }, 2000) // Waktu delay dalam milidetik (4000 milidetik = 4 detik)
    }
}
