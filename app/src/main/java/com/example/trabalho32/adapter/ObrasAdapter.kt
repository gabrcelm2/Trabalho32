package com.example.trabalho32.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho32.DescricaoPint
import com.example.trabalho32.R
import com.example.trabalho32.databinding.ObrasItemBinding
import com.example.trabalho32.model.Obras


class ObrasAdapter(private val context:
                   Context, private val listaObras: MutableList<Obras>) :
    RecyclerView.Adapter<ObrasAdapter.ObrasViewHolder>() {

    private lateinit var talkObras:MutableList<Obras>

    init {
        talkObras = MutableList(10, { Obras()})
        talkObras.clear()
        talkObras.addAll(listaObras)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObrasViewHolder {

        val itemLista = ObrasItemBinding.inflate(LayoutInflater.from(context), parent, false)

        return ObrasViewHolder(itemLista)
    }

    override fun getItemCount() = listaObras.size

    override fun onBindViewHolder(holder: ObrasViewHolder, position: Int) {
//        holder.imgObra.setImageResource(listaObras[position].img!!)
/*        holder.txtObra.text = listaObras[position].nome
        holder.obraId = listaObras[position].id.toString()

        val bundle = Bundle()
        bundle.putString("id", holder.obraId)

        val fragment = DescricaoPint()
        val fragmentManager = (context as FragmentActivity).supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()*/

//        when (position) {
//            0 -> holder.imgObra.setOnClickListener {
//                val fragment = DescricaoPint()
//                val fragmentManager = (context as FragmentActivity).supportFragmentManager
//                fragmentManager.beginTransaction()
//                    .replace(R.id.container, fragment)
//                    .commit()
//            }
//
//            1 -> holder.imgObra.setOnClickListener {
//                val fragment = DescricaoPint2()
//                val fragmentManager = (context as FragmentActivity).supportFragmentManager
//                fragmentManager.beginTransaction()
//                    .replace(R.id.container, fragment)
//                    .commit()
//            }
//
//            2 -> holder.imgObra.setOnClickListener {
//                val fragment = DescricaoPint3()
//                val fragmentManager = (context as FragmentActivity).supportFragmentManager
//                fragmentManager.beginTransaction()
//                    .replace(R.id.container, fragment)
//                    .commit()
//            }
//
//            3 -> holder.imgObra.setOnClickListener {
//                val fragment = DescricaoPint4()
//                val fragmentManager = (context as FragmentActivity).supportFragmentManager
//                fragmentManager.beginTransaction()
//                    .replace(R.id.container, fragment)
//                    .commit()
//            }
//
//        }
    }

    fun search(query: String) {



        //talkObras.clear()
        listaObras.clear()
        if (query.isEmpty()) {
            listaObras.addAll(talkObras)
            //Log.d("updateRecycle","lista/;"+listaObras.toString())
        } else {
            for (doc in talkObras){

                if(doc.nome!!.toLowerCase()!!.contains(query)){
                    listaObras.add(doc)
                }
            }
            //listaObras.addAll(talkObras.filter { it.nome!!.contains(query, true) })
            Log.d("updateRecycle","lista/;"+listaObras.toString())

        }

        notifyDataSetChanged()

        //return talkObras.isEmpty()
    }


    fun clearSearch() {
        //talkObras.clear()
        //talkObras.addAll(listaObras)
        notifyDataSetChanged()
    }

        inner class ObrasViewHolder(binding: ObrasItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
                var obraId =""
            val imgObra = binding.imgObra
            val txtObra = binding.txtObra
        }
    }