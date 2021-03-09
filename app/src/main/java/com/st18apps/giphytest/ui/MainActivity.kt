package com.st18apps.giphytest.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.st18apps.giphytest.R
import com.st18apps.giphytest.data.Result
import com.st18apps.giphytest.data.model.Data
import com.st18apps.giphytest.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var adapter: DataAdapter

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView.setOnQueryTextListener(this)
        initRecycler()
    }

    private fun searchForGifs(query: String) {
        viewModel.search(query = query).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Result.Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE

                        resource.data?.gifData?.let { gifsList ->
                            noFoundBox.visibility = if (gifsList.isEmpty()) View.VISIBLE else View.GONE
                            recyclerView.scrollToPosition(0)
                            setDataToAdapter(gifsList)
                        }

                    }
                    Result.Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Result.Status.LOADING -> {
                        recyclerView.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun initRecycler() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = DataAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setDataToAdapter(data: List<Data>) {
        adapter.apply {
            addData(data)
            notifyDataSetChanged()
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchForGifs(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (!newText.isNullOrEmpty()) {
            searchForGifs(newText)
        }
        return true
    }
}