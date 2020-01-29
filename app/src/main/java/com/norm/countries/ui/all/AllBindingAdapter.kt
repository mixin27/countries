package com.norm.countries.ui.all

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.norm.countries.data.remote.CountryModel

@BindingAdapter("sourceListData")
fun countriesListItem(recyclerView: RecyclerView, data: List<CountryModel>?) {
    val adapter = recyclerView.adapter as AllCountriesAdapter
    adapter.submitList(data)
}