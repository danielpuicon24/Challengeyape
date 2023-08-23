package com.my.training.challengeyape.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.my.training.challengeyape.databinding.IngredienteRecyclerViewBinding
import com.my.training.challengeyape.domain.model.Ingrediente

class IngredienteRecyclerViewAdapter(private var ingredientes : List<Ingrediente>)
    :  RecyclerView.Adapter<IngredienteRecyclerViewAdapter.ListaHolder>(){

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listaIngredientes: List<Ingrediente>) {
        ingredientes = listaIngredientes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaHolder {
        val itemView = IngredienteRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListaHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListaHolder, position: Int) {
        val ingrediente = ingredientes[position]
        holder.bind(ingrediente)
    }

    override fun getItemCount(): Int = ingredientes.size

    class ListaHolder(private val itemBinding: IngredienteRecyclerViewBinding) : RecyclerView.ViewHolder(itemBinding.root){

        fun bind(ingrediente: Ingrediente){
            Glide.with(itemBinding.root).load(ingrediente.imagen).into(itemBinding.imgIngrediente)
            itemBinding.txtIngrediente.text = ingrediente.descripcion
        }

    }
}