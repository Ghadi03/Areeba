package com.example.areeba

import NewsAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [english.newInstance] factory method to
 * create an instance of this fragment.
 */
class Arabic : Fragment() {

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
        val newsList = parseJsonData()

        // Update the adapter with the news list
        newsAdapter.setNewsList(newsList)
    }

    private fun parseJsonData(): List<News> {
        val newsList = mutableListOf<News>()

        val jsonString = """
        [
    {
      "title": "اكتشاف كوكب جديد في مجموعتنا الشمسية",
      "description": "تم اكتشاف كوكب جديد في مجموعتنا الشمسية بواسطة علماء الفلك. يُعتقد أن الكوكب الجديد يشترك في صفات مشابهة لتلك للأرض، حيث يتمتع بغلاف جوي ومياه سائلة. قد يُمثل هذا الاكتشاف نقطة تحول في البحث عن حياة خارج كوكب الأرض. ستستمر الدراسات العلمية لمعرفة المزيد عن هذا الكوكب الجديد وإمكانية وجود حياة عليه.",
      "image_url": "https://media.cnn.com/api/v1/images/stellar/prod/220712093712-james-webb-first-image-0711-no-bars.jpg?c=original&q=w_1280,c_fill",
      "date": "2023-05-30"
    },
    {
      "title": "اكتشاف علاج جديد للسرطان",
      "description": "أعلن فريق من الباحثين عن اكتشاف علاج مبتكر للسرطان يعد خطوة هامة في مجال الطب. يعمل العلاج على تثبيط نمو الخلايا السرطانية بشكل فعال وتقليل تأثيرات العلاجات التقليدية. يأمل العلماء أن يكون هذا العلاج الجديد نقلة نوعية في مجال مكافحة السرطان وتحسين نسب الشفاء للمرضى.",
      "image_url": "https://scx2.b-cdn.net/gfx/news/2020/2-cancer.jpg",
      "date": "2023-05-29"
    },
    {
      "title": "تقنية الذكاء الاصطناعي تحقق تقدمًا كبيرًا",
      "description": "حققت تقنية الذكاء الاصطناعي تقدمًا كبيرًا في السنوات الأخيرة. تم تطوير نماذج جديدة تستطيع التعلم والتفاعل بشكل مشابه للبشر. قد تؤثر هذه التقنية على مختلف المجالات مثل الطب، والتجارة، والترفيه، وتوفير حلول فعالة للمشاكل المعقدة. تستمر الأبحاث والتطوير في هذا المجال لاستكشاف مزيد من الإمكانيات والتطبيقات المستقبلية.",
      "image_url": "https://assets.gatesnotes.com/8a5ac0b3-6095-00af-c50a-89056fbe4642/11eeb7f9-7512-49aa-abdc-a27001dd123e/AI_20230215_article-hero_1200x564.jpg",
      "date": "2023-05-28"
    },
    {
      "title": "افتتاح مطعم جديد بمفهوم فريد",
      "description": "تم افتتاح مطعم جديد يقدم تجربة طعام فريدة من نوعها. يتميز المطعم بتصميمه العصري وقائمة طعام متنوعة تجمع بين الأطباق التقليدية والابتكارات الجديدة. يستخدم المطعم المكونات العضوية والمحلية لتقديم وجبات ذوق رفيع. يُعتبر هذا المطعم وجهة مثالية لعشاق الطعام والباحثين عن تجارب جديدة.",
      "image_url": "https://i0.wp.com/www.restaurantmanifesto.com/wp-content/uploads/2020/02/New-Restaurants-e1582739226528.jpg?fit=650%2C400&ssl=1",
      "date": "2023-05-27"
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
