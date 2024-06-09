package com.example.trabalho32

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho32.adapter.ObrasAdapter
import com.example.trabalho32.databinding.FragmentListaObrasBinding
import com.example.trabalho32.model.Obras


class ListaObras : Fragment() {

    private lateinit var binding: FragmentListaObrasBinding
    private lateinit var obrasAdapter: ObrasAdapter
    private val listaObras = mutableListOf<Obras>()
    private lateinit var recyclerView:RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaObrasBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchView()
        val cadastro = arguments?.getString("cadastro")

        binding.txtNomeUsuario.text = "Bem-vindo(a)"
        recyclerView = binding.recyclerViewObras
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        obrasAdapter = ObrasAdapter(requireContext(), listaObras)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = obrasAdapter
        binding.btvoltar.setOnClickListener {
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout, Inicioo())
            fragmentTransaction.commit()
        }
    }

    private fun initSearchView() {
        binding.pesquisa.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                obrasAdapter.search(query)
                obrasAdapter.notifyDataSetChanged()
                   Log.d("SimpleSearchView", "Submit:$query")
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                Log.d("SimpleSearchView", "Submit:$query")
                return false
            }
        })

        binding.pesquisa.setOnSearchClickListener {
        }

        binding.pesquisa.setOnCloseListener {
            obrasAdapter.clearSearch()
            true
        }
    }

//    private fun getObras() {
//        val obras1 = Obras(R.drawable.pint1, "Amor e Ódio")
//        listaObras.add(obras1)
//
//        val obras2 = Obras(R.drawable.pint2, "Luz na Escuridão")
//        listaObras.add(obras2)
//
//        val obras3 = Obras(R.drawable.pint3,"Um dia com Ela")
//        listaObras.add(obras3)
//
//        val obras4 = Obras(R.drawable.pint4,"Idas e Vindas")
//        listaObras.add(obras4)
//    }
}