package br.com.rbueno.chucknorrisfacts.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.rbueno.chucknorrisfacts.R

class HomeAdapter(private val items: MutableList<String>, private val clickListener: (category: String) -> Unit) :
    RecyclerView.Adapter<HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_category_item, parent, false),
            clickListener
        )


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    fun setItems(items: List<String>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}

class HomeViewHolder(private val view: View, private val clickListener: (category: String) -> Unit) :
    RecyclerView.ViewHolder(view) {

    private val textCategory by lazy { view.findViewById<TextView>(R.id.text_category) }

    fun bindView(category: String) {
        view.setOnClickListener { clickListener(category) }
        textCategory.text = category
    }

}