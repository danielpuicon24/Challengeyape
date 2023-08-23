package com.my.training.challengeyape.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.my.training.challengeyape.databinding.ActivityDetalleRecetaBinding
import com.my.training.challengeyape.ui.adapter.IngredienteRecyclerViewAdapter
import com.my.training.challengeyape.ui.viewmodel.RecetaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetalleRecetaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleRecetaBinding
    private val recetaViewModel : RecetaViewModel by viewModels()
    private var adapter = IngredienteRecyclerViewAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleRecetaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idReceta = intent.getIntExtra("idReceta", 0)

        recetaViewModel.searchRecetaById(idReceta)

        binding.recyclerView.adapter = adapter

        recetaViewModel.detalleRecetaModel.observe(this, Observer{ receta ->
            binding.txtNombreReceta.text = receta.nombre
            binding.txtDescripcionReceta.text = receta.descripcion
            binding.txtTitulo.text = "Información acerca de ${receta.nombre}".uppercase()

            Glide.with(binding.root).load(receta.imagen).into(binding.imageView)
            binding.star1.visibility = if (receta.calificacion >= 1) View.VISIBLE else View.GONE
            binding.star2.visibility = if (receta.calificacion >= 2) View.VISIBLE else View.GONE
            binding.star3.visibility = if (receta.calificacion >= 3) View.VISIBLE else View.GONE
            binding.star4.visibility = if (receta.calificacion >= 4) View.VISIBLE else View.GONE
            binding.star5.visibility = if (receta.calificacion >= 5) View.VISIBLE else View.GONE

            binding.btnVerMapa.setOnClickListener {
                val intent = Intent(this, MapaRecetaActivity::class.java)
                intent.putExtra("idReceta", receta.id)
                intent.putExtra("latitud", receta.latitud)
                intent.putExtra("longitud", receta.longitud)
                intent.putExtra("nombre", receta.nombre)
                intent.putExtra("descripcion", receta.descripcion)
                startActivity(intent)
            }
        })

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        recetaViewModel.ingredientesModel.observe(this, Observer{ ingrediente ->
            binding.recyclerView.adapter = adapter
            adapter.setData(ingrediente!!)
            adapter.notifyDataSetChanged()
        })
    }

}