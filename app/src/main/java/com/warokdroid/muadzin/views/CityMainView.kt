package com.warokdroid.muadzin.views

import com.warokdroid.muadzin.models.City

interface CityMainView {

    fun showLoading()
    fun hideLoading()
    fun showCityList(data: List<City>?)
}