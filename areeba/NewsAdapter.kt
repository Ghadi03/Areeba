import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.areeba.ImageZoomActivity
import com.example.areeba.News
import com.example.areeba.R
import com.squareup.picasso.Picasso

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var newsList: List<News> = emptyList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news_card, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews = newsList[position]

        holder.bind(currentNews)


        Glide.with(holder.itemView)
            .load(currentNews.imageUrl)
            .into(holder.newsImage)

        holder.newsImage.setOnClickListener {
            val intent = Intent(holder.itemView.context, ImageZoomActivity::class.java)
            intent.putExtra("imageUrl", currentNews.imageUrl)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setNewsList(newsList: List<News>) {
        this.newsList = newsList
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsImage: ImageView = itemView.findViewById(R.id.news_image)
        private val newsTitle: TextView = itemView.findViewById(R.id.news_title)
        private val newsDescription: TextView = itemView.findViewById(R.id.news_description)
        private val newsDate: TextView = itemView.findViewById(R.id.news_date)

        fun bind(news: News) {
            newsTitle.text = news.title
            newsDescription.text = news.description
            newsDate.text = news.date
            Picasso.get().load(news.imageUrl)
                .placeholder(R.drawable.news)
                .into(newsImage)
        }
    }
}
