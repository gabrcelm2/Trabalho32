package com.example.trabalho32

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import com.example.trabalho32.databinding.FragmentDescricaoPint3Binding
import java.util.Locale


class DescricaoPint3 : Fragment(), android.speech.tts.TextToSpeech.OnInitListener {

    private lateinit var binding: FragmentDescricaoPint3Binding
    private lateinit var textToSpeech: android.speech.tts.TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        textToSpeech = android.speech.tts.TextToSpeech(context, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescricaoPint3Binding.inflate(inflater, container, false)
        val view = binding.root

        view.findViewById<Button>(R.id.btvoltar2).setOnClickListener{
            NavHostFragment.findNavController(this).navigate(R.id.action_descricaoPint3_to_listaObras)
        }

        val audiodescricaoImageView = view.findViewById<ImageView>(R.id.audiodescricao)
        val descriptionTextView = view.findViewById<TextView>(R.id.descriptionTextView)

        audiodescricaoImageView.setOnClickListener {
            val text = descriptionTextView.text.toString()
            textToSpeech.speak(text, android.speech.tts.TextToSpeech.QUEUE_FLUSH, null, null)
        }

        return view
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val portugues = Locale("pt", "BR")
            textToSpeech.language = portugues
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        textToSpeech.shutdown()
    }
}