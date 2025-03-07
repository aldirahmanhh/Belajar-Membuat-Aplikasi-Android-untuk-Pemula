package com.dicoding.aldidicoding

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.io.File
import java.io.FileOutputStream

class ArticleAdapter(private val articles: List<Article>, private val onClick: (Article) -> Unit) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
        val title: TextView = itemView.findViewById(R.id.title)
        val overview: TextView = itemView.findViewById(R.id.overview)
        val author: TextView = itemView.findViewById(R.id.author)
        val share: TextView = itemView.findViewById(R.id.action2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.thumbnail.setImageResource(article.imageResId)
        holder.title.text = article.title
        holder.author.text = article.author
        holder.overview.text = article.overview

        holder.share.setOnClickListener {
            onClick(article)
        }
        holder.itemView.setOnClickListener {
            onClick(article)
        }
    }

    override fun getItemCount(): Int = articles.size

    private fun shareArticle(article: Article, holder: ArticleViewHolder) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "${article.title}\n${article.overview}")
            type = "text/plain"
        }
        holder.itemView.context.startActivity(Intent.createChooser(shareIntent, "Share article dengan menggunakan"))
    }
}
