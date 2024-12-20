package com.example.pr5mybd.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pr5mybd.R
import com.example.pr5mybd.adapters.NewsAdapter


class FavouritesFragment : Fragment(R.layout.fragment_favourites) {
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var newsViewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = (activity as MainActivity).newsViewModel
        setupFavouritesRecycler(view) // Pass the view to the function

        newsViewModel.getFavoriteNews().observe(viewLifecycleOwner) { articles ->
            newsAdapter.differ.submitList(articles)
        }
    }

    private fun setupFavouritesRecycler(view: View) { // Added view parameter
        newsAdapter = NewsAdapter()
        view.findViewById<RecyclerView>(R.id.recyclerFavourites)?.apply { // Use findViewById and null check
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}

