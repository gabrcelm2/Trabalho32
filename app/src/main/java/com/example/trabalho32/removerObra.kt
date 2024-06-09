package com.example.trabalho32

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class removerObra : Fragment() {

    private lateinit var obraList: ListView

    private lateinit var obras: MutableList<String>

    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        db = FirebaseFirestore.getInstance()

        obras = mutableListOf()

    }


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?

    ): View? {

        val view = inflater.inflate(R.layout.fragment_remover_obra, container, false)

        obraList = view.findViewById(R.id.obra_list)


        // Recuperar obras do Firestore

        db.collection("obras")

            .get()

            .addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    for (document in task.result!!) {

                        obras.add(document.id)

                    }

                    val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, obras)

                    obraList.adapter = adapter

                } else {

                    Log.d("Erro", "Erro ao recuperar obras: ${task.exception}")

                }

            }


        // Adicionar listener para o botão de exclusão

        obraList.setOnItemClickListener { _, _, position, _ ->

            val obraId = obras[position]

            db.collection("obras")

                .document(obraId)

                .delete()

                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {

                        obras.removeAt(position)

                        obraList.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, obras)

                    } else {

                        Log.d("Erro", "Erro ao excluir obra: ${task.exception}")

                    }

                }

        }


        return view

    }

}