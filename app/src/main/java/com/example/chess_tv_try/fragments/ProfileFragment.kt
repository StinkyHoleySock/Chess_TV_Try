package com.example.chess_tv_try.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chess_tv_try.MainActivity
import com.example.chess_tv_try.R
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private var profileRecyclerView = profile_recyclerView
    private var adapter: ProfileItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as MainActivity?)
            ?.setActionBarTitle("Профиль")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        profileRecyclerView = view.findViewById(R.id.profile_recyclerView) as RecyclerView
        profileRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        return view
    }

    private fun updateUI() {
        val profileItems = ArrayList<String>()
        profileItems.add("Сообщения")
        profileItems.add("Профиль")
        profileItems.add("Конфиденциальность")
        profileItems.add("Подписки")
        profileItems.add("Статистика")
        profileItems.add("Настройки")
        adapter = ProfileItemAdapter(profileItems)
        profileRecyclerView.adapter = adapter
    }

    private inner class ProfileItemHolder(view: View): RecyclerView.ViewHolder(view){

        val itemTextView: TextView = itemView.findViewById(R.id.tv_item_title)
    }

    private inner class ProfileItemAdapter(var items: List<String>)
        : RecyclerView.Adapter<ProfileItemHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileItemHolder {
            val view = layoutInflater.inflate(R.layout.custom_profile_item, parent, false)
            return ProfileItemHolder(view)
        }

        override fun onBindViewHolder(holder: ProfileItemHolder, position: Int) {
            val item = items[position]
            holder.itemTextView.text = item
        }

        override fun getItemCount() = items.size
        }

    }