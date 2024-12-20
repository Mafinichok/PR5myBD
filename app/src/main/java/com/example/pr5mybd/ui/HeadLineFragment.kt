package com.example.pr5mybd.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pr5mybd.R
import com.example.pr5mybd.adapters.NewsAdapter

class HeadLineFragment : Fragment(R.layout.fragment_headline) {
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var newsViewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = (activity as MainActivity).newsViewModel
        setupHeadlinesRecycler(view)
        newsViewModel.headlines.observe(viewLifecycleOwner) { response ->
            response.data?.let { newsResponse ->
                newsAdapter.differ.submitList(newsResponse.articles.toList())
            }
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = newsAdapter.differ.currentList[position]
                newsViewModel.addToFavorites(article)
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(view.findViewById(R.id.recyclerHeadlines)) // Использование findViewById
        }
    }

    private fun setupHeadlinesRecycler(view: View) { // Добавлена передача view
        newsAdapter = NewsAdapter()
        view.findViewById<RecyclerView>(R.id.recyclerHeadlines).apply { // Использование findViewById
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}

