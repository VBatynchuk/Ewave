package com.batynchuk.ewave.screens.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.batynchuk.ewave.R
import com.batynchuk.ewave.common.base.BaseFragment
import com.batynchuk.ewave.data.country.CountryDto
import javax.inject.Inject

import kotlinx.android.synthetic.main.fragment_countries.*
import kotlinx.android.synthetic.main.item_country.view.*
import androidx.recyclerview.widget.DividerItemDecoration



class CountriesFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: CountriesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_countries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCountries.layoutManager = LinearLayoutManager(safeContext)
        rvCountries.addItemDecoration(DividerItemDecoration(safeContext, RecyclerView.VERTICAL))
        observeEvents()
        viewModel.loadAllCountries()
    }

    private fun observeEvents() {
        viewModel.countriesList.observe(viewLifecycleOwner, Observer { countriesList ->
            if (countriesList != null) {
                rvCountries.adapter = CountriesAdapter(countriesList) { viewModel.onCountryClick(it) }
            }
        })
        viewModel.exceptionLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Toast.makeText(safeContext, R.string.error_occurred, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner, Observer { isLoading ->
            pbLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

    }
}


class CountriesAdapter(
    private val countries: List<CountryDto?>,
    private val onCountryClick: ((Int) -> Unit)?
) : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false))
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.itemView.tvCountryName.text = country?.nativeName
    }

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onCountryClick?.invoke(adapterPosition) }
        }
    }
}