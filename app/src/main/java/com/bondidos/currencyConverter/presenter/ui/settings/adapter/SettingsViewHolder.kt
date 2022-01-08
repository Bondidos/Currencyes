package com.bondidos.currencyConverter.presenter.ui.settings.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bondidos.currencyConverter.databinding.SettingsItemBinding
import com.bondidos.currencyConverter.domain.entityes.Currencies

class SettingsViewHolder(
    private val binding: SettingsItemBinding,
    private val action: (String, Boolean) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Currencies) {
        with(binding) {
            title.text = data.curAbbreviation
            subTitle.text = "${data.curScale} ${data.curName}"
            switcher.isChecked = data.isShow
            switcher.setOnClickListener {
//                Log.d("UseCAse","Clicked on the ${data.curAbbreviation} & switcher is ${switcher.isChecked}")
                action(data.curAbbreviation, switcher.isChecked)
            }
        }
    }
}