package ml.jasoyahdoandehgroup.androidteam.enkrateiaapp.ui.chat

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ml.jasoyahdoandehgroup.androidteam.enkrateiaapp.R
import ml.jasoyahdoandehgroup.androidteam.enkrateiaapp.data.Chat
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ChatFragment : Fragment() {

    private var isRecording = false
    private lateinit var mediaRecorder: MediaRecorder
    private lateinit var floatingButton: FloatingActionButton
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //dummy, just delete
        val chatUser = Chat("001", "1", "22-12-21 22:22:22" , null, false)
        val chatBot = Chat("002", null, "22-12-21","Hi! You need to response for what were you doing now", true)

        val adapter = ChatAdapter()

        adapter.setChat(
            listOf(chatUser, chatBot)
        )

        floatingButton = view.findViewById(R.id.fab_record)
        recyclerView = view.findViewById(R.id.rv_message)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        floatingButton.setOnClickListener {

            if (checkPermission()) {
                isRecording = if (isRecording) {
                    //stop Recording
                    changeDrawableFab(R.drawable.ic_baseline_mic_24)
                    stopRecording()
                    false
                } else {
                    //start recording
                    changeDrawableFab(R.drawable.ic_baseline_stop_24)
                    startRecording()
                    true
                }
            } else { requestPermission() }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun changeDrawableFab(drawId: Int) {
        floatingButton.setImageDrawable(
            resources.getDrawable(
                drawId,
                null
            )
        )
    }

    private fun startRecording() {
        val recordPath = activity?.getExternalFilesDir("/")?.absolutePath
        val formatter = SimpleDateFormat("yyyy_MM_dd_hhmmss", Locale.getDefault())
        val date = Date()
        val recordFile = "${formatter.format(date)}.3gp"

        mediaRecorder = MediaRecorder()
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
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
        return ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
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