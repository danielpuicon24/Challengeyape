package com.my.training.challengeyape.ui.adapter

import com.my.training.challengeyape.domain.model.Receta

interface RecyclerViewItemClickListener {
    fun onItemClick(data : Receta)
}