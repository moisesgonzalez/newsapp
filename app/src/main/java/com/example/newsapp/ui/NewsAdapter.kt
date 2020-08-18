package com.example.newsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.NewsItemBinding
import com.example.newsapp.domain.Article

class NewsAdapter(private  val  onClickListener: OnClickListener):RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    var articles:List<Article> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        return articles.size
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val withDataBinding:NewsItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            NewsViewHolder.LAYOUT,
            parent,
            false)
        return NewsViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(viewHolder: NewsViewHolder, position: Int) {
       val article = articles[position]
       viewHolder.viewDataBinding.also {
           it.article = article
       }

        viewHolder.itemView.setOnClickListener {
            onClickListener.onClick(article)
        }
    }
    class NewsViewHolder(val viewDataBinding: NewsItemBinding):
        RecyclerView.ViewHolder(viewDataBinding.root){
        companion object{
            @LayoutRes
            val LAYOUT = R.layout.news_item
        }

    }
    class OnClickListener(val clickListener:(article:Article) -> Unit){
        fun onClick(article: Article) = clickListener(article)
    }
}