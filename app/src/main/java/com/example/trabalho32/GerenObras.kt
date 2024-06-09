package com.example.trabalho32

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trabalho32.databinding.FragmentGerenObrasBinding
import android.content.Intent
import androidx.fragment.app.replace
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trabalho32.adapter.ObrasAdapter
import com.example.trabalho32.model.Obras


class GerenObras : Fragment() {

    private lateinit var binding: FragmentGerenObrasBinding
    private lateinit var obrasAdapter: ObrasAdapter
    private val listaObras = mutableListOf<Obras>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGerenObrasBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cadastro = arguments?.getString("cadastro")

        binding.txtNomeUsuario.text = "Bem-vindo(a)"
        val recyclerViewObras = binding.recyclerViewObras
        recyclerViewObras.layoutManager = GridLayoutManager(requireContext(),2)
        obrasAdapter = ObrasAdapter(requireContext(), listaObras)
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

//        getObras()

    }

//    private fun getObras(){
//        val obras1 = Obras(R.drawable.pint1,"Amor e Ódio")
//        listaObras.add(obras1)
//
//        val obras2 = Obras(R.drawable.pint2,"Luz na Escuridão")
//        listaObras.add(obras2)
//
//        val obras3 = Obras(R.drawable.pint3,"Um dia com Ela")
//        listaObras.add(obras3)
//
//        val obras4 = Obras(R.drawable.pint4,"Idas e Vindas")
//        listaObras.add(obras4)
//    }
}