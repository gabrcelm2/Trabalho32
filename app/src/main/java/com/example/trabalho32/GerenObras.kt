package com.example.trabalho32

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trabalho32.databinding.FragmentGerenObrasBinding
import android.content.Intent
import android.util.Log
import android.widget.SearchView
import androidx.fragment.app.replace
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho32.adapter.ObrasAdapter
import com.example.trabalho32.adapter.VisitasAdapter
import com.example.trabalho32.model.Obras
import com.example.trabalho32.model.Visitas
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestore.*


class GerenObras : Fragment() {

    private lateinit var binding: FragmentGerenObrasBinding
    private lateinit var obrasAdapter: ObrasAdapter
    private val listaObras = ArrayList<Obras>()
    private lateinit var visitasRecyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var filteredVisitList: ArrayList<Obras>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGerenObrasBinding.inflate(inflater, container, false)
        visitasRecyclerView  = binding.recyclerViewObras
        getVisitasData()
        return binding.root


    }
    private fun getVisitasData() {
        val db = getInstance()
        val visitasRef = db.collection("Obras2")

        visitasRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    val visitas = document.toObject(Obras::class.java)
                    listaObras.add(visitas)
                }
                filteredVisitList = listaObras
                val vAdapter = ObrasAdapter(filteredVisitList)
                visitasRecyclerView.adapter = vAdapter

            } else {
                Log.w("Visitas", "Error getting data", task.exception)
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cadastro = arguments?.getString("cadastro")

        binding.txtNomeUsuario.text = "Bem-vindo(a)"
        val recyclerViewObras = binding.recyclerViewObras

        recyclerViewObras.layoutManager = GridLayoutManager(requireContext(),2)
        obrasAdapter = ObrasAdapter( listaObras)
        recyclerViewObras.setHasFixedSize(true)
        recyclerViewObras.adapter = obrasAdapter



        binding.btgeren.setOnClickListener {
            binding.btgeren.visibility = View.INVISIBLE
            binding.btadd.visibility = View.VISIBLE
            binding.btremover.visibility = View.VISIBLE
        }

        binding.btadd.setOnClickListener {
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout2, addObra())
            fragmentTransaction.commit()
        }

        binding.btremover.setOnClickListener {
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout2, removerObra())
            fragmentTransaction.commit()
        }


    }

}