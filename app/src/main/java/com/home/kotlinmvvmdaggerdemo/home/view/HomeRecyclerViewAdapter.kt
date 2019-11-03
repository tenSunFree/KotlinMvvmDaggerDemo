package com.home.kotlinmvvmdaggerdemo.home.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.home.kotlinmvvmdaggerdemo.R
import com.home.kotlinmvvmdaggerdemo.home.model.HomeBean
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_home_recycler_view_item.*

class HomeRecyclerViewAdapter(
    private val onItemClickListener: RecyclerViewItemClickListener,
    private val results: List<HomeBean.Results.Result>
) : RecyclerView.Adapter<HomeRecyclerViewAdapter.NewsViewHolder>() {

    interface RecyclerViewItemClickListener {
        fun onRecyclerViewItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_home_recycler_view_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(position, results[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    class NewsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        @SuppressLint("SetTextI18n")
        fun bind(
            position: Int,
            resultItem: HomeBean.Results.Result,
            recyclerItemListener: RecyclerViewItemClickListener
        ) {
            text_view_regulatory_number.text = resultItem.regulatoryNumber
            text_view_clearing_mechanism.text = resultItem.clearingMechanism
            text_view_work_phone.text = "公司電話 " + resultItem.workPhone
            constraint_layout_root.setOnClickListener {
                recyclerItemListener.onRecyclerViewItemClick(position)
            }
        }
    }
}