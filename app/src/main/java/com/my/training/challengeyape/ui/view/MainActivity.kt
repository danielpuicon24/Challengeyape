package com.my.training.challengeyape.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.my.training.challengeyape.databinding.ActivityMainBinding
import com.my.training.challengeyape.domain.model.Receta
import com.my.training.challengeyape.ui.adapter.RecetaRecyclerViewAdapter
import com.my.training.challengeyape.ui.adapter.RecyclerViewItemClickListener
import com.my.training.challengeyape.ui.viewmodel.RecetaViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RecyclerViewItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private var adapter = RecetaRecyclerViewAdapter(emptyList())

    private val recetaViewModel : RecetaViewModel by viewModels()
    private val handler = Handler(Looper.getMainLooper())

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recetaViewModel.onCreate()

        binding.recyclerView.adapter = adapter

        recetaViewModel.isLoading.observe(this, Observer{
            binding.progress.isVisible = it
        })

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        binding.swipeRefresh.setOnRefreshListener {
            handler.postDelayed({
                recetaViewModel.onCreate()
                binding.swipeRefresh.isRefreshing = false
            }, 2000)
        }

        binding.search.setOnQueryTextListener(object  : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                if(text.isNullOrBlank()){
                    binding.layout.visibility = View.GONE
                    recetaViewModel.onCreate()
                }else{
                    MainScope().launch {
                        if(text.isNotEmpty()){
                            recetaViewModel.searchReceta(text)
                        }
                    }
                }
                return true
            }
        })

        recetaViewModel.recetaModel.observe(this, Observer{ receta ->
            binding.recyclerView.adapter = adapter
            adapter.setData(receta!!)
            adapter.itemClickListener = this
            adapter.notifyDataSetChanged()
        })

        recetaViewModel.recetaSearchModel.observe(this, Observer{ recetasSearch ->
            if(recetasSearch.isNullOrEmpty()){
                adapter.setData(emptyList())
                binding.layout.visibility = View.VISIBLE
            }else{
                adapter.setData(recetasSearch)
                adapter.itemClickListener = this
                binding.layout.visibility = View.GONE
            }
            adapter.notifyDataSetChanged()
        })

    }

    override fun onItemClick(data: Receta) {
        val intent = Intent(this, DetalleRecetaActivity::class.java)
        intent.putExtra("idReceta", data.id)
        startActivity(intent)
    }


}