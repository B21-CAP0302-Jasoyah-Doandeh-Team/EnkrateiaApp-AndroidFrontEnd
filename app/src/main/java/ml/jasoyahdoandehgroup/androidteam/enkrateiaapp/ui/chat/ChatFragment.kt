package ml.jasoyahdoandehgroup.androidteam.enkrateiaapp.ui.chat

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ml.jasoyahdoandehgroup.androidteam.enkrateiaapp.R
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ChatFragment : Fragment() {

    private var isRecording = false
    private lateinit var mediaRecorder: MediaRecorder

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val floatingButton = container?.findViewById<FloatingActionButton>(R.id.fab_record)

        floatingButton?.setOnClickListener {
            if (checkPermission()) {
                if (isRecording) {
                    //stop Recording
                    floatingButton.setImageDrawable(
                        resources.getDrawable(
                            R.drawable.ic_baseline_mic_24,
                            null
                        )
                    )
                    stopRecording()
                    isRecording = false
                } else {
                    //start recording
                    floatingButton.setImageDrawable(
                        resources.getDrawable(
                            R.drawable.ic_baseline_stop_24,
                            null
                        )
                    )
                    startRecording()
                    isRecording = true
                }
            } else { requestPermission() }
        }

        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    private fun startRecording() {
        val recordPath = activity?.getExternalFilesDir("/")?.absolutePath
        val formatter = SimpleDateFormat("yyyyMMddhhmmss", Locale.getDefault())
        val date = Date()
        val recordFile = "${formatter.format(date)}.3gp"

        mediaRecorder = MediaRecorder()
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
        mediaRecorder.setOutputFile("$recordPath/$recordFile")

        try {
            mediaRecorder.prepare()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        mediaRecorder.start()
    }

    private fun stopRecording() {
        mediaRecorder.stop()
        mediaRecorder.release()
    }

    private fun checkPermission(): Boolean {
        return context?.let {
            ActivityCompat.checkSelfPermission(
                it,
                Manifest.permission.RECORD_AUDIO
            )
        } != PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        activity?.let {
            ActivityCompat.requestPermissions(
                it,
                arrayOf(
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                111
            )
        }
    }
}