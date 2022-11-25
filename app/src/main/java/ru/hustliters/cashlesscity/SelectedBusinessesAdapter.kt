package ru.hustliters.cashlesscity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SelectedBusinessesAdapter(private val list: List<Business>): RecyclerView.Adapter<SelectedBusinessesAdapter.BusinessViewHolder>() {

    class BusinessViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.title)
        private val address: TextView = view.findViewById(R.id.address)
        private val category: TextView = view.findViewById(R.id.categories)

        fun bind(business: Business) {
            name.text = business.name
            address.text = business.address
            category.text = business.category.joinToString(" • ", transform = { it.name })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.business_item, parent, false)
        return BusinessViewHolder(view)
    }

    override fun onBindViewHolder(holder: BusinessViewHolder, position: Int) = holder.bind(list[position])
    override fun getItemCount() = list.size
}