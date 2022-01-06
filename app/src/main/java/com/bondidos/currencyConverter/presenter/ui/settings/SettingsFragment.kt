package com.bondidos.currencyConverter.presenter.ui.settings

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bondidos.currencyConverter.R
import com.bondidos.currencyConverter.databinding.SettingsFragmentBinding
import com.bondidos.currencyConverter.domain.util.Resources
import com.bondidos.currencyConverter.presenter.ui.settings.adapter.SettingsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.settings_fragment) {

    private val binding by viewBinding(SettingsFragmentBinding::bind)

    @Inject
    lateinit var viewModel: SettingsViewModel
    private val currencyAdapter: SettingsAdapter by lazy { SettingsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun observe() {
        lifecycleScope.launchWhenCreated {
            viewModel.cachedList.collect { resources ->
                when (resources) {
                    is Resources.Loading -> binding.progressBar.isVisible = true
                    is Resources.Success -> {
                        currencyAdapter.setData(resources.data)
                        setUpRecycler()
                        binding.progressBar.isVisible = false
                    }
                    is Resources.Error -> Toast.makeText(
                        requireContext(),
                        resources.message,
                        Toast.LENGTH_LONG
                    ).show()
                    else -> Unit
                }
            }
        }
    }

    private fun setUpRecycler() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = currencyAdapter
        }
    }
}