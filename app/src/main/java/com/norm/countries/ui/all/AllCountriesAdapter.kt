package com.norm.countries.ui.all

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.norm.countries.data.remote.CountryModel
import com.norm.countries.databinding.ListItemAllCountriesBinding

class AllCountriesAdapter(
    private val clickListener: OnClickListener
) :
    ListAdapter<CountryModel, AllCountriesAdapter.AllCountriesViewHolder>(object :
        DiffUtil.ItemCallback<CountryModel>() {
        override fun areItemsTheSame(oldItem: CountryModel, newItem: CountryModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CountryModel, newItem: CountryModel): Boolean {
            return oldItem == newItem
        }
    }) {


    class AllCountriesViewHolder private constructor(
        private val binding: ListItemAllCountriesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CountryModel) {
            binding.country = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): AllCountriesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemAllCountriesBinding.inflate(layoutInflater, parent, false)
                return AllCountriesViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCountriesViewHolder {
        return AllCountriesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AllCountriesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        holder.itemView.setOnClickListener {
            clickListener.onClick(item)
        }
    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [CountryModel]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [CountryModel]
     */
    class OnClickListener(val clickListener: (country: CountryModel) -> Unit) {
        fun onClick(country: CountryModel) = clickListener(country)
    }
}