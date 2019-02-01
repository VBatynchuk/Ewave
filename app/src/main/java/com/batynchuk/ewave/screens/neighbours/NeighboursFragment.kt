package com.batynchuk.ewave.screens.neighbours

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.batynchuk.ewave.R
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.batynchuk.ewave.common.base.BaseFragment
import com.batynchuk.ewave.screens.countries.CountriesAdapter
import kotlinx.android.synthetic.main.fragment_countries.*
import javax.inject.Inject

class NeighboursFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: NeighbourViewModel

    private lateinit var selectedCountry: String

    companion object {
        const val COUNTRY_ARG = "country_name_arg"
        fun getInstance(selectedCountry: String): NeighboursFragment {
            val bundle = Bundle()
            bundle.putString(COUNTRY_ARG, selectedCountry)
            val fragment = NeighboursFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_neighbours, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        selectedCountry = safeArguments.getString(COUNTRY_ARG)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCountries.layoutManager = LinearLayoutManager(safeContext)
        rvCountries.addItemDecoration(DividerItemDecoration(safeContext, RecyclerView.VERTICAL))
        observeEvents()
        viewModel.loadCountryNeighbours(selectedCountry)
    }

    private fun observeEvents() {
        viewModel.neighboursList.observe(viewLifecycleOwner, Observer { neighboursList ->
            if (neighboursList != null) {
                rvCountries.adapter = CountriesAdapter(neighboursList, null)
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