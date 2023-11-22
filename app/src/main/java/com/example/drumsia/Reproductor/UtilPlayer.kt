package com.example.drumsia.Reproductor

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import java.io.IOException

class UtilPlayer {
    companion object{
        fun playWav(context: Context, wavResourceId: Int) {
            val mediaPlayer = MediaPlayer()

            try {
                // Configurar atributos de audio para la reproducción
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val audioAttributes = AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()

                    mediaPlayer.setAudioAttributes(audioAttributes)
                } else {
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
                }

                // Cargar el archivo WAV desde recursos
                val fileDescriptor = context.resources.openRawResourceFd(wavResourceId)
                mediaPlayer.setDataSource(
                    fileDescriptor.fileDescriptor,
                    fileDescriptor.startOffset,
                    fileDescriptor.length
                )
                fileDescriptor.close()

                // Preparar y reproducir el archivo WAV
                mediaPlayer.prepare()
                mediaPlayer.start()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}