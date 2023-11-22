package com.example.drumsia.Model

import android.graphics.Bitmap

data class Drum(
 val id: String,
 val midiResourceId: Int,
 var bitmap: Bitmap,
 var scale: Float,
 var xPosition: Float,
 var yPosition: Float,
 val originalYPosition: Float
)