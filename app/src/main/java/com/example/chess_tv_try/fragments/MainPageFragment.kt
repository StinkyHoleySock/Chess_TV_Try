package com.example.chess_tv_try.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chess_tv_try.MainActivity
import com.example.chess_tv_try.News
import com.example.chess_tv_try.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_main_page.*
import kotlinx.android.synthetic.main.news_item.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

class MainPageFragment : Fragment() {


    private var newsRecyclerView = news_recyclerView
    private lateinit var adapter: NewsAdapter

    private val listNews = mutableListOf<News>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Установка title в actionBar
        (activity as MainActivity?)
            ?.setActionBarTitle("Главная")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main_page, container, false)

        newsRecyclerView = view.findViewById(R.id.news_recyclerView) as RecyclerView
        newsRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter = NewsAdapter()
        newsRecyclerView.adapter = adapter


        // Запуск корутины
        GlobalScope.launch {
            getData()
        }
        
        return view
    }


    // Парсинг новостей с помощью Jsoup
    private fun getData(): MutableList<News> {

        try {
            val url = "https://ria.ru/chess/"
            val doc: Document = Jsoup.connect(url).get()

            val news: Elements = doc.select("div[class=list-item]") // Проверить
            val newsSize: Int = news.size

            for (i in 0 until newsSize) {
                val title = news.select("a.list-item__title.color-font-hover-only")
                    .eq(i)
                    .text()
                val imageLink = news.select("a.list-item__image")
                    .select("picture")
                    .select("img")
                    .eq(i)
                    .attr("src")
                val date = news.select("div[class=list-item__info]")
                    .select("div[class=list-item__date]")
                    .eq(i)
                    .text()
                val linkToNews = news.select("a.list-item__title.color-font-hover-only")
                    .eq(i)
                    .attr("href")
                listNews.add(News(i, title, imageLink, date, linkToNews))
            }

            GlobalScope.launch(Dispatchers.Main) {
                adapter.set(listNews)
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return listNews
    }

    // Создаём холдер
    private inner class NewsHolder(view: View): RecyclerView.ViewHolder(view){

        val textTitle: TextView = itemView.findViewById(R.id.tv_news_title)
        val date: TextView = itemView.findViewById(R.id.tv_date)
        val imageTitle: ImageView = itemView.findViewById(R.id.iv_news_title)

        fun bind(news: News) {

            textTitle.text = news.title
            date.text = news.date

            Picasso.with(itemView.context)
                .load(news.imageLink)
                .into(imageTitle)
        }

    }

    // Создаём адаптер
    private inner class NewsAdapter: RecyclerView.Adapter<NewsHolder>(){

        private val listNews = mutableListOf<News>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
            val view = layoutInflater.inflate(R.layout.news_item, parent, false)
            return NewsHolder(view)
        }

        override fun onBindViewHolder(holder: NewsHolder, position: Int) {

            holder.bind(listNews[position])
        }

        override fun getItemCount() = listNews.size


        fun set(list: MutableList<News>) {
            this.listNews.clear()
            this.listNews.addAll(list)

            notifyDataSetChanged()

        }

    }


}