package com.bondidos.currencyConverter.presenter.ui.mainFragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bondidos.currencyConverter.R
import com.bondidos.currencyConverter.databinding.MainFragmentBinding
import com.bondidos.currencyConverter.domain.Resources
import com.bondidos.currencyConverter.presenter.ui.mainFragment.adapter.CurrencyAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private val binding by viewBinding(MainFragmentBinding::bind)

    @Inject
    lateinit var viewModel: MainViewModel
    private val currencyAdapter = CurrencyAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        initRecycler()
    }

    private fun observe() {
        lifecycleScope.launchWhenCreated {
            viewModel.currencies.collect { resources ->
                when(resources){
                    is Resources.Loading -> binding.progressBar.isVisible = true
                    is Resources.Success -> {
                        binding.progressBar.isVisible = false
                        currencyAdapter.setData(resources.data)
                    }
                    is Resources.Error -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(requireContext(), resources.message, Toast.LENGTH_LONG)
                            .show()
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun initRecycler() {
        binding.recycler.apply {
            adapter = currencyAdapter
        }
    }
}