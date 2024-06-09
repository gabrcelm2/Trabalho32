package com.example.trabalho32

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho32.adapter.VisitasAdapter
import com.example.trabalho32.model.Visitas
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.util.Util


class GerenVisitas : Fragment() {

    private lateinit var db: DatabaseReference
    private lateinit var visitasRecyclerView: RecyclerView
    private lateinit var visitList: ArrayList<Visitas>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_geren_visitas, container, false)

        visitasRecyclerView = view.findViewById(R.id.recyclerViewVisitas)
        visitasRecyclerView.layoutManager = LinearLayoutManager(context)
        visitasRecyclerView.setHasFixedSize(true)

        visitList = arrayListOf<Visitas>()

        getVisitasData()

        return view
    }

    private fun getVisitasData() {
        val db = FirebaseFirestore.getInstance()
        val visitasRef = db.collection("agendamentos")

        visitasRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val visitList = ArrayList<Visitas>()
                for (document in task.result!!) {
                    val visitas = document.toObject(Visitas::class.java)
                    visitList.add(visitas)
                }
                val vAdapter = VisitasAdapter(visitList)
                visitasRecyclerView.adapter = vAdapter
            } else {
                Log.w("Visitas", "Error getting data", task.exception)
            }
        }
    }
}




