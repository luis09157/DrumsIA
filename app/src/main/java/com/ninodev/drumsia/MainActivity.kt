package com.ninodev.drumsia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ninodev.drumsia.Drum.DrumKitView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val drumKitView = DrumKitView(this)
        setContentView(drumKitView)
    }
}