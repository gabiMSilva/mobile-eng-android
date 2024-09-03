package com.gmsilva.basiccomponents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.gmsilva.basiccomponents.databinding.ItemListBinding

class NoticiaAdapter(
    private val listaNoticia: List<NoticiaModel>,
    private val glide: RequestManager,
    private val onItemClickListener: OnItemClickListener
): RecyclerView.Adapter<NoticiaAdapter.ViewHolder>() {

    private lateinit var binding: ItemListBinding

    inner class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        var titulo = binding.tituloNoticia
        var descricao = binding.corpoNoticia
        var autor = binding.nomeAutor
        var autorImagem = binding.imagemAutor
        var noticiaImagem = binding.imagemNoticia
        val data = binding.dataNoticia
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return listaNoticia.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemAtual = listaNoticia[position]

        holder.titulo.text = itemAtual.titulo
        holder.autor.text = itemAtual.nomeAutor
        holder.descricao.text = itemAtual.corpo
        holder.data.text = itemAtual.data

        holder.itemView.setOnClickListener{
            onItemClickListener.openLink(itemAtual.link)
        }

        glide.load(itemAtual.imagemNoticia)
            .into(holder.noticiaImagem)

        glide.load(itemAtual.imagemAutor)
            .into(holder.autorImagem)
    }
}