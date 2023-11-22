package com.example.drumsia

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.media.midi.MidiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.example.drumsia.Reproductor.UtilPlayer
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val drumKitView = DrumKitView(this)
        setContentView(drumKitView)
    }

    private data class Drum(val id: String, val midiResourceId: Int, val bitmap: Bitmap, val scale: Float, var xPosition: Float, var yPosition: Float)

    private class DrumKitView(context: Context) : View(context) {

        private val drums = mutableListOf<Drum>()
        private val midiManager = context.getSystemService(Context.MIDI_SERVICE) as MidiManager

        init {
            // Carga las imágenes desde recursos y establece las escalas y posiciones
            drums.add(Drum("Drum1", R.raw.drum_1, loadBitmap(R.drawable.drum1), 0.1f, 100f, 200f))
            drums.add(Drum("Drum2", R.raw.drum_2, loadBitmap(R.drawable.drum1), 0.1f, 300f, 300f))
            drums.add(Drum("Drum3", R.raw.drum_3, loadBitmap(R.drawable.drum1), 0.1f, 500f, 400f))
            // Agrega más imágenes, escalas y posiciones según sea necesario
            setOnTouchListener { _, event -> handleTouch(event) }
        }

        private fun loadBitmap(resId: Int): Bitmap {
            return BitmapFactory.decodeResource(resources, resId)
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            // Dibuja los tambores en las posiciones especificadas
            for (drum in drums) {
                drawDrum(canvas, drum)
            }
        }

        private fun drawDrum(canvas: Canvas, drum: Drum) {
            // Ajusta el tamaño del tambor con el parámetro de escala asociado
            val scaledBitmap = Bitmap.createScaledBitmap(drum.bitmap, (drum.bitmap.width * drum.scale).toInt(), (drum.bitmap.height * drum.scale).toInt(), true)
            // Dibuja la imagen del tambor en la posición especificada en los ejes X e Y
            canvas.drawBitmap(scaledBitmap, drum.xPosition - scaledBitmap.width / 2f, drum.yPosition - scaledBitmap.height / 2f, null)
        }

        private fun handleTouch(event: MotionEvent): Boolean {
            if (event.action == MotionEvent.ACTION_DOWN) {
                val touchX = event.x
                val touchY = event.y

                println("x:  ${touchX} y: ${touchY}")

                // Verifica si el toque está dentro del área de algún tambor
                for (drum in drums) {
                    if (isTouchInsideDrum(touchX, touchY, drum)) {
                        // Se tocó un tambor, realiza la acción deseada
                        println("Tocaste el tambor ${drum.id}")
                        UtilPlayer.playWav(context,drum.midiResourceId)
                        // Aquí puedes llamar a la función para reproducir el sonido MIDI o WAV
                        // playMidi(drum.midiResourceId)
                        // playWav(context, drum.wavResourceId)

                        break
                    }
                }
            }
            return false
        }

        private fun isTouchInsideDrum(touchX: Float, touchY: Float, drum: Drum): Boolean {
            val scaledWidth = drum.bitmap.width * drum.scale
            val scaledHeight = drum.bitmap.height * drum.scale

            val leftX = drum.xPosition - scaledWidth / 2f
            val rightX = drum.xPosition + scaledWidth / 2f
            val topY = drum.yPosition - scaledHeight / 2f
            val bottomY = drum.yPosition + scaledHeight / 2f

            return touchX >= leftX && touchX <= rightX && touchY >= topY && touchY <= bottomY
        }

    }
}