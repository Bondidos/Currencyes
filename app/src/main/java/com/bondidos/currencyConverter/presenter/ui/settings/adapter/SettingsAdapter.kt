package com.bondidos.currencyConverter.presenter.ui.settings.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bondidos.currencyConverter.databinding.SettingsItemBinding
import com.bondidos.currencyConverter.domain.entityes.Currencies

class SettingsAdapter(private val action: (String, Boolean) -> Unit) : RecyclerView.Adapter<SettingsViewHolder>() {

    private val adapterData = mutableListOf<Currencies>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val binding = SettingsItemBinding.inflate(LayoutInflater.from(parent.context))
        return SettingsViewHolder(binding,action)
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
}

class SettingsViewHolder(private val binding: SettingsItemBinding, private val action: (String,Boolean) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Currencies) {
        with(binding) {
            title.text = data.curAbbreviation
            subTitle.text = "${data.curScale} ${data.curName}"
            switcher.isChecked = data.isShow
            switcher.setOnClickListener {
//                Log.d("UseCAse","Clicked on the ${data.curAbbreviation} & switcher is ${switcher.isChecked}")
                action(data.curAbbreviation,switcher.isChecked)
            }
        }
    }
}
