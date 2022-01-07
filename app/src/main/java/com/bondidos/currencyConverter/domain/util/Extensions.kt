package com.bondidos.currencyConverter.domain.util

import com.bondidos.currencyConverter.domain.entityes.Currencies

fun List<Currencies>.updateIsShowField(cacheList: List<Currencies>){
    if(cacheList.isNotEmpty()) {
        this.forEachIndexed { index, currencies ->
            if (currencies.isShow != cacheList[index].isShow)
                currencies.isShow = cacheList[index].isShow
        }
    }
}

