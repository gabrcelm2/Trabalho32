package com.example.trabalho32.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho32.DescricaoPint
import com.example.trabalho32.R
import com.example.trabalho32.databinding.ObrasItemBinding
import com.example.trabalho32.model.Obras

class ObrasAdapter( private val listaObras: ArrayList<Obras>) : RecyclerView.Adapter<ObrasAdapter.ObrasViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObrasViewHolder {

        val itemLista = LayoutInflater.from(parent.context).inflate(R.layout.obras_item,parent,false)
        return ObrasViewHolder(itemLista)
    }

    override fun getItemCount(): Int {
        return listaObras.size
    }
    override fun onBindViewHolder(holder: ObrasViewHolder, position: Int) {
        //holder.obraId = talkObras[position].id.toString()
        //holder.imgObra.setImageBitmap(BitmapFactory.decodeByteArray(
        //listaObras[position].img!!.toByteArray(),0,listaObras[position].img!!.length))
        holder.txtObra.text = listaObras[position].nome

    }

        inner class ObrasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val imgObra = itemView.findViewById<ImageView>(R.id.imgObra)
            val txtObra = itemView.findViewById<TextView>(R.id.txtObra)
        }
    }