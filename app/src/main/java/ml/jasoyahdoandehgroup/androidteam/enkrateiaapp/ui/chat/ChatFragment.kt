package ml.jasoyahdoandehgroup.androidteam.enkrateiaapp.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ml.jasoyahdoandehgroup.androidteam.enkrateiaapp.R

class ChatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val floatingButton = container?.findViewById<FloatingActionButton>(R.id.fab_record)

        floatingButton?.setOnClickListener {

        }

        return inflater.inflate(R.layout.fragment_chat, container, false)
    }
}