package com.example.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.SearchFragmentBinding
import com.example.newsapp.viewmodel.SearchViewModel


class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private var viewModelAdapter: NewsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SearchFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        viewModel.articles.observe(viewLifecycleOwner, Observer {articles->
            viewModelAdapter?.articles = articles
        })

        viewModelAdapter =
            NewsAdapter(NewsAdapter.OnClickListener {
                viewModel.displayArticleDetails(it)
            })
        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer {
            if(it != null){
                val navController = findNavController()
                navController.navigate(
                    SearchFragmentDirections.actionSearchFragmentToDetailArticleFragment(
                        it
                    )
                )
                viewModel.displayArticleDetailsComplete()
            }
        })
        binding.listQueryNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.onSearch(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

        })

        return binding.root
    }

}
