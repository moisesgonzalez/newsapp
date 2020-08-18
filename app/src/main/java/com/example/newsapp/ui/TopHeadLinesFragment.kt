package com.example.newsapp.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentTopHeadLinesBinding
import com.example.newsapp.domain.Article
import com.example.newsapp.viewmodel.TopHeadLinesViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [TopHeadLinesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TopHeadLinesFragment : Fragment() {

    private var viewModelAdapter: NewsAdapter? = null
    private lateinit var navController:NavController

    private val viewModel: TopHeadLinesViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this,
            TopHeadLinesViewModel.Factory(activity.application)
        ).
            get(TopHeadLinesViewModel::class.java)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.articles.observe(viewLifecycleOwner, Observer<List<Article>> {articles ->
            articles.apply {
                viewModelAdapter?.articles = articles
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTopHeadLinesBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        navController = findNavController()

        viewModelAdapter =
            NewsAdapter(NewsAdapter.OnClickListener {
                viewModel.displayArticleDetails(it)
            })
        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                navController.navigate(
                    TopHeadLinesFragmentDirections.actionTopHeadLinesFragmentToDetailArticleFragment(
                        it
                    )
                )
                viewModel.displayArticleDetailsComplete()
            }
        })

        binding.root.findViewById<RecyclerView>(R.id.list_top_news).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }


        setHasOptionsMenu(true)

        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        navController.navigate(R.id.action_topHeadLinesFragment_to_searchFragment)

        return super.onOptionsItemSelected(item)
    }


}
