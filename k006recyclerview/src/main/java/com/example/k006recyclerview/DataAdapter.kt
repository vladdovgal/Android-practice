package com.example.k006recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class DataAdapter(context: Context?, private val phones: List<Phone>) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    // повертає об'єкт ViewHolder, який буде зберігати дані за одним Phone
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    // виконує прив'язку об'єкта ViewHolder до об'єкта Phone за визначеною позицією
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (name, company, image) = phones[position]
        holder.imageView.setImageResource(image)
        holder.nameView.text = name
        holder.companyView.text = company
    }

    // повертаєє кількість телефонів
    override fun getItemCount(): Int {
        return phones.size
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView
        val nameView: TextView
        val companyView: TextView

        init {
            imageView = view.findViewById<View>(R.id.image) as ImageView
            nameView = view.findViewById<View>(R.id.name) as TextView
            companyView = view.findViewById<View>(R.id.company) as TextView
        }
    }

}