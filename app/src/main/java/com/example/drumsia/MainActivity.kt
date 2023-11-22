package com.example.drumsia

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.LinearInterpolator
import com.example.drumsia.Drum.DrumKitView
import com.example.drumsia.Model.Drum
import com.example.drumsia.Reproductor.UtilPlayer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val drumKitView = DrumKitView(this)
        setContentView(drumKitView)
    }

}