package com.bondidos.currencyConverter.presenter.ui.mainFragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bondidos.currencyConverter.databinding.CurrencyItemBinding
import com.bondidos.currencyConverter.databinding.SettingsItemBinding
import com.bondidos.currencyConverter.domain.entityes.Currencies

class MainAdapter : RecyclerView.Adapter<CurrencyViewHolder>() {

    private val adapterData = mutableListOf<Currencies>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = CurrencyItemBinding.inflate(LayoutInflater.from(parent.context))
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(adapterData[position])
    }

    override fun getItemCount(): Int = adapterData.size

    fun setData(data: List<Currencies>) {
        adapterData.apply {
            clear()
            addAll(data)
        }
    }
}

class CurrencyViewHolder(private val binding: CurrencyItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Currencies) {
        with(binding) {
            title.text = data.curAbbreviation
            subTitle.text = "${data.curScale} ${data.curName}"
            if (data.alternativeDate > data.todayDate) {
                firstValue.text = data.todayCurOfficialRate.toString()
                secondValue.text = data.altCurOfficialRate.toString()
            } else {
                firstValue.text = data.altCurOfficialRate.toString()
                secondValue.text = data.todayCurOfficialRate.toString()
            }
        }
    }
}
