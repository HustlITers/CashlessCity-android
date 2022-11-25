package ru.hustliters.cashlesscity

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SelectedBusinessesAdapter(
    private val list: List<Business>,
    private val callbackOnClick: ((Int, View?) -> Unit)
): RecyclerView.Adapter<SelectedBusinessesAdapter.BusinessViewHolder>() {

    class BusinessViewHolder(view: View, private val callbackOnClick: (Int, View?) -> Unit): RecyclerView.ViewHolder(view), OnClickListener {
        private val name: TextView = view.findViewById(R.id.title)
        private val category: TextView = view.findViewById(R.id.categories)

        init {
            view.setOnClickListener(this)
        }

        fun bind(business: Business) {
            name.text = business.name
            category.text = business.category.joinToString(" • ", limit = 2, transform = { it.name })
        }

        override fun onClick(clickedView: View?) {
            callbackOnClick(adapterPosition, clickedView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.business_item, parent, false)
        return BusinessViewHolder(view, callbackOnClick)
    }

    override fun onBindViewHolder(holder: BusinessViewHolder, position: Int) = holder.bind(list[position])
    override fun getItemCount() = list.size
}