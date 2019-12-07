package com.example.realmapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.item.view.*


class Adapter(
    private val context: Context,
    private val List: OrderedRealmCollection<Model>?,
    private val autoUpdate: Boolean
) : RealmRecyclerViewAdapter<Model, Adapter.ViewHolder>(List, autoUpdate) {

    override fun getItemCount(): Int {
        return List?.size ?: 0
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false)
//        val layoutInflater = LayoutInflater.from(viewGroup!!.context)
//        val binding = LinearLayout.inflate(context, R.layout.item, viewGroup)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = List?.get(position)
        holder.textView.text = person?.title
        //holder.binding.textView.text = person?.text

        //holder.binding.cellLayout.setBackgroundColor(if (position % 2 == 0) Color.LTGRAY else Color.WHITE)
    }

//    class ViewHolder(
//        val binding: LinearLayout
//    ) : RecyclerView.ViewHolder(binding.rootView)


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.textView
    }
}