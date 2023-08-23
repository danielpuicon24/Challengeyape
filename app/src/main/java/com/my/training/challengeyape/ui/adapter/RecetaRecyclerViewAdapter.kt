package com.my.training.challengeyape.ui.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.my.training.challengeyape.databinding.RecetarecyclerviewBinding
import com.my.training.challengeyape.domain.model.Receta
import com.my.training.challengeyape.ui.view.DetalleRecetaActivity

class RecetaRecyclerViewAdapter (private var recetas : List<Receta>)
    : RecyclerView.Adapter<RecetaRecyclerViewAdapter.ListaHolder>(){

    var itemClickListener: RecyclerViewItemClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listaRecetas: List<Receta>) {
        recetas = listaRecetas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaHolder {
        val itemView = RecetarecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListaHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListaHolder, position: Int) {
        val receta = recetas[position]
        holder.bind(receta, itemClickListener)
    }

    override fun getItemCount(): Int = recetas.size

    class ListaHolder(val itemBinding : RecetarecyclerviewBinding) : RecyclerView.ViewHolder(itemBinding.root){

        fun bind(receta : Receta, itemClickListener: RecyclerViewItemClickListener?){
            itemBinding.txtNombreReceta.text = receta.nombre
            itemBinding.txtDescripcionReceta.text = receta.descripcion
            Glide.with(itemBinding.root).load(receta.imagen).into(itemBinding.imgReceta)
            itemBinding.star1.visibility = if (receta.calificacion >= 1) View.VISIBLE else View.GONE
            itemBinding.star2.visibility = if (receta.calificacion >= 2) View.VISIBLE else View.GONE
            itemBinding.star3.visibility = if (receta.calificacion >= 3) View.VISIBLE else View.GONE
            itemBinding.star4.visibility = if (receta.calificacion >= 4) View.VISIBLE else View.GONE
            itemBinding.star5.visibility = if (receta.calificacion >= 5) View.VISIBLE else View.GONE
            itemBinding.cardview.setOnClickListener {
               itemClickListener?.onItemClick(receta)
            }
        }
    }
}