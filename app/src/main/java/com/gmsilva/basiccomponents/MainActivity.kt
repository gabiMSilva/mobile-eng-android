package com.gmsilva.basiccomponents

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.gmsilva.basiccomponents.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private var noticias: List<NoticiaModel> = listOf()
    private lateinit var adapter: ArrayAdapter<NoticiaModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListView()
    }

    private fun setupListView() {
        noticias = readJson(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = NoticiaAdapter(
            noticias, Glide.with(this), this@MainActivity
        )
    }

    private fun readJson(context: Context): List<NoticiaModel>{
        val listTemp = mutableListOf<NoticiaModel>()

        try {

            val json = context.assets.open("data.json").bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(json)

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val nomeAutor = jsonObject.getString("nomeAutor")
                val imagemAutor = jsonObject.getString("imagemAutor")
                val imagemNoticia = jsonObject.getString("imagemNoticia")
                val titulo = jsonObject.getString("titulo")
                val link = jsonObject.getString("link")
                val data = jsonObject.getString("data")
                val corpo = jsonObject.getString("corpo")

                val noticia = NoticiaModel(
                    imagemNoticia = imagemNoticia,
                    data = data,
                    link = link,
                    nomeAutor = nomeAutor,
                    imagemAutor = imagemAutor,
                    titulo = titulo,
                    corpo = corpo
                )

                listTemp.add(noticia)
            }
        } catch (error: Exception ) {
            println("Erro na leitura do json")
        }
        return listTemp

    }

    override fun openLink(link: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(link)
        startActivity(intent)
    }
}