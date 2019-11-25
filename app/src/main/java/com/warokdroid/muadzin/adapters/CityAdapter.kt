package com.warokdroid.muadzin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.warokdroid.muadzin.R
import com.warokdroid.muadzin.models.City


class CityAdapter(
    private val context: Context,
    private val cities: MutableList<City>
) :
    RecyclerView.Adapter<CityAdapter.ViewHolder>(), Filterable {

    private val citiesFull: List<City> = cities

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(context).inflate(
            R.layout.city_item, parent, false
        )
    )

    override fun getItemCount(): Int = cities.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(cities[position]) {
            Toast.makeText(context, "${it.name} clicked!", Toast.LENGTH_SHORT).show()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvCityFirstLetter: TextView = view.findViewById(R.id.tv_city_first_letter)
        private val tvName: TextView = view.findViewById(R.id.tv_city_name)

        fun bindItems(
            city: City,
            listener: (City) -> Unit
        ) {

            tvCityFirstLetter.text = city.name?.first().toString()
            tvName.text = city.name?.toLowerCase()?.capitalize()

            itemView.setOnClickListener { listener(city) }
        }
    }

    override fun getFilter(): Filter = searchFilter

    private val searchFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: MutableList<City> = mutableListOf()

            if (constraint == null || constraint.isEmpty())
                filteredList.addAll(citiesFull)
            else {
                val filterPattern = constraint.toString().toLowerCase().trim()

                for (item in citiesFull) {
                    if (item.name?.let { it.toLowerCase().contains(filterPattern) }!!)
                        filteredList.add(item)
                }
            }

            val results = FilterResults()
            results.values = filteredList

            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            cities.clear()
            cities.addAll(results?.values as MutableList<City>)
            notifyDataSetChanged()
        }

    }
}