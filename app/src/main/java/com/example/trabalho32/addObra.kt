package com.example.trabalho32

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.ByteArrayOutputStream


class addObra : Fragment() {

    private var nomeObra: EditText? = null
    private var autorObra: EditText? = null
    private var descricaoObra: EditText? = null
    private lateinit var imageView: ImageView
    private var imageString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_obra, container, false)
        nomeObra = view.findViewById<EditText>(R.id.NomeObra)
        imageView = view.findViewById<ImageView>(R.id.imageViewObra)
        autorObra = view.findViewById<EditText>(R.id.AutorObra)
        descricaoObra = view.findViewById<EditText>(R.id.DescricaoObra)

        imageView.setOnClickListener {
            openGallery()
        }

        view.findViewById<Button>(R.id.buttonSalvar).setOnClickListener {
            when {
                nomeObra?.text.isNullOrEmpty() -> {
                    mensagem(it, "Preencha o campo: Nome da Obra!", "#004af5")
                }
                autorObra?.text.isNullOrEmpty() -> {
                    mensagem(it, "Preencha o campo: Autor da Obra!", "#004af5")
                }
                descricaoObra?.text.isNullOrEmpty() -> {
                    mensagem(it, "Preencha o campo: Descrição da Obra!", "#004af5")
                }
                imageView.drawable == null -> {
                    mensagem(it, "Selecione uma imagem!", "#004af5")
                }
                else -> {
                    val nome = nomeObra?.text.toString()
                    val autor = autorObra?.text.toString()
                    val descricao = descricaoObra?.text.toString()
                    Firebase.firestore.collection("Obras2").add(mapOf(
                        "nome" to nome,
                        "imagem" to imageString,
                        "autor" to autor,
                        "descricao" to descricao,
                    ))
                    mensagem(it, "Obra salva!", "#008000") // green color for success
                }
            }
        }

        return view
    }

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            data?.data?.let { uri ->
                imageView.setImageURI(uri)

                // Converter o Bitmap do ImageView em um array de bytes
                val byteArray = getByteArrayFromImageView(imageView)

                // Converter o array de bytes em uma string base64
                imageString = Base64.encodeToString(byteArray, Base64.DEFAULT)
            }
        }
    }

    private fun getByteArrayFromImageView(imageView: ImageView): ByteArray? {
        val drawable = imageView.drawable as BitmapDrawable
        val bitmap = drawable.bitmap

        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        return byteArrayOutputStream.toByteArray()
    }

    private fun mensagem(view: View, mensagem: String, cor: String) {
        val snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.WHITE)
        snackbar.show()
    }
}