package com.warokdroid.muadzin.presenters

import com.google.gson.Gson
import com.warokdroid.muadzin.apis.ApiRepository
import com.warokdroid.muadzin.apis.BangHasanApi
import com.warokdroid.muadzin.models.CityResponse
import com.warokdroid.muadzin.views.CityMainView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CityMainPresenter(
    private val view: CityMainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getCityList() {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(BangHasanApi.getCity()),
                CityResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showCityList(data.cities)
            }
        }
    }
}