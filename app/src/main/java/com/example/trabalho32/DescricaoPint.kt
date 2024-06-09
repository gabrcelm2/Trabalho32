package com.example.trabalho32

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.trabalho32.databinding.FragmentDescricaoPintBinding
import com.example.trabalho32.databinding.FragmentListaObrasBinding
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Locale

class DescricaoPint : Fragment(), TextToSpeech.OnInitListener {

    private lateinit var binding: FragmentDescricaoPintBinding
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var id:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString("id").toString()
        }
        textToSpeech = TextToSpeech(context, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescricaoPintBinding.inflate(inflater, container, false)
        val view = binding.root

        view.findViewById<Button>(R.id.btvoltar2).setOnClickListener{
            NavHostFragment.findNavController(this).navigate(R.id.action_descricaoPint_to_listaObras)
        }

        val audiodescricaoImageView = view.findViewById<ImageView>(R.id.audiodescricao)
        val descriptionTextView = view.findViewById<TextView>(R.id.descriptionTextView)
        var obra = Firebase.firestore.collection("Obras").document(id).get()
        audiodescricaoImageView.setOnClickListener {
            val text = descriptionTextView.text.toString()
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
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