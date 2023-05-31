package com.example.areeba

import NewsAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [english.newInstance] factory method to
 * create an instance of this fragment.
 */
class english : Fragment() {

    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter




    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_english, container, false)

        newsRecyclerView = view.findViewById(R.id.news_recycler_view)
        newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        newsAdapter = NewsAdapter()
        newsRecyclerView.adapter = newsAdapter

        // Call a function to load the news data and update the adapter
        loadNewsData()

        return view

    }

    private fun loadNewsData() {
        // Retrieve your JSON data and parse it into a list of news objects
        val newsList = parseJsonData()

        // Update the adapter with the news list
        newsAdapter.setNewsList(newsList)
    }

    private fun parseJsonData(): List<News> {
        val newsList = mutableListOf<News>()

        val jsonString = """
     [
       {
         "title": "Google AI Introduces LaMDA, a Language Model for Dialog Applications",
         "description": "Google AI today announced LaMDA, a new language model for dialog applications. LaMDA is trained on a massive dataset of text and code, and can generate text, translate languages, write different kinds of creative content, and answer your questions in an informative way.",
         "image_url": "https://ichef.bbci.co.uk/news/1024/branded_news/B08D/production/_125379154_aigettyimages-1160995648.jpg",
         "date": "2023-05-30"
       },
       {
         "title": "Elon Musk Buys Twitter for ${'$'}44 Billion",
         "description": "Elon Musk has reached an agreement to buy Twitter for ${'$'}44 billion. The deal, which is expected to close later this year, would give Musk control of one of the world's most popular social media platforms.",
         "image_url": "https://d2eehagpk5cl65.cloudfront.net/img/c1200x675-w1200-q80/uploads/2022/04/thumbnail-9-3-1200x675.jpg.webp",
         "date": "2023-04-25"
       },
       {
         "title": "Russia Invades Ukraine",
         "description": "Russia has invaded Ukraine, launching a full-scale military attack on its neighbor. The invasion has caused widespread death and destruction, and has led to a humanitarian crisis in Ukraine.",
         "image_url": "https://warontherocks.com/wp-content/uploads/2022/02/russia-2022-1024x654.jpg",
         "date": "2022-02-24"
       }
     ]
    """.trimIndent()

        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val title = jsonObject.getString("title")
            val description = jsonObject.getString("description")
            val imageUrl = jsonObject.getString("image_url")
            val date = jsonObject.getString("date")

            val news = News(title, description, imageUrl, date)
            newsList.add(news)
        }

        return newsList
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment english.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            english().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
