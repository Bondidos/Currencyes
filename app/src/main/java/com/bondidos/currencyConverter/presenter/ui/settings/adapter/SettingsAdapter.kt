package com.bondidos.currencyConverter.presenter.ui.settings.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bondidos.currencyConverter.databinding.SettingsItemBinding
import com.bondidos.currencyConverter.domain.entityes.Currencies
import java.util.*

class SettingsAdapter(
    private val action: (String, Boolean) -> Unit
) : RecyclerView.Adapter<SettingsViewHolder>(), (Int,Int) -> Boolean {

    private val adapterData = mutableListOf<Currencies>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val binding = SettingsItemBinding.inflate(LayoutInflater.from(parent.context))
        return SettingsViewHolder(binding, action)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        holder.bind(adapterData[position])
    }

    override fun getItemCount(): Int = adapterData.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Currencies>) {
        adapterData.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun invoke(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            (fromPosition until toPosition).forEach { index ->
                Collections.swap(adapterData, index, index + 1)
                Log.d("UseCAse","from $fromPosition to $toPosition")
            }
        } else {
            (fromPosition downTo  toPosition+1).forEach { index ->
                Collections.swap(adapterData, index, index - 1)
                Log.d("UseCAse","from $fromPosition to $toPosition")
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }
}

