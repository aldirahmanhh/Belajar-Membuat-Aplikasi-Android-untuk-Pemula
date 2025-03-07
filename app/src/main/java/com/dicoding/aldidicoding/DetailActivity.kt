package com.dicoding.aldidicoding

import android.content.Intent
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationIcon(R.drawable.back_arrow)
        toolbar.setNavigationContentDescription("Back")
        toolbar.setNavigationOnClickListener {
            finish()
        }

        supportActionBar?.title = "Halaman Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val article = intent.getParcelableExtra<Article>("article")

        val detailImageView: ImageView = findViewById(R.id.detailImageView)
        val detailTitleTextView: TextView = findViewById(R.id.detailTitleTextView)
        val detailAuthorTextView: TextView = findViewById(R.id.author)
        val detailDescriptionTextView: TextView = findViewById(R.id.detailDescriptionTextView)
        val detailDateTextView: TextView = findViewById(R.id.publishDate)
        val detailDescription: TextView = findViewById(R.id.detailDescriptionTextView)
        val shareButton: Button = findViewById(R.id.share)

        article?.let {
            detailImageView.setImageResource(it.imageResId)
            detailTitleTextView.text = it.title
            detailAuthorTextView.text = it.author
            detailDescriptionTextView.text = "Detailed information about ${it.title}"
            detailDescription.text = it.description
            detailDateTextView.text = "Published on ${it.publishedDate}"

            shareButton.setOnClickListener{
                shareArticle(article)
            }

        }
    }

    private fun shareArticle(article: Article) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "${article.title}\n${article.description}")
            type = "text/plain"
        }

        startActivity(Intent.createChooser(shareIntent, "Share Artikel dengan"))
        Toast.makeText(this, "Sharing Artikel ${article.title}", Toast.LENGTH_SHORT).show()
    }
}
