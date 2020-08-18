package com.example.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.viewmodel.DetailArticleViewModel
import com.example.newsapp.viewmodel.DetailArticleViewModelFactory
import com.example.newsapp.databinding.FragmentDetailArticleBinding



/**
 * A simple [Fragment] subclass.
 * Use the [DetailArticleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailArticleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailArticleBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner
        val article = DetailArticleFragmentArgs.fromBundle(
            arguments!!
        ).selectedArticle
        val viewModelFactory =
            DetailArticleViewModelFactory(article)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(DetailArticleViewModel::class.java)
        binding.viewModel = viewModel

        return  binding.root
    }

}