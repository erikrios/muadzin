package com.warokdroid.muadzin.presenters

import com.google.gson.Gson
import com.warokdroid.muadzin.apis.ApiRepository
import com.warokdroid.muadzin.apis.BangHasanApi
import com.warokdroid.muadzin.models.DataPrayerTimeResponse
import com.warokdroid.muadzin.views.PrayerTimeMainView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PrayerTimeMainPresenter(
    private val view: PrayerTimeMainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getPrayerTimeList(cityId: String, date: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(BangHasanApi.getPrayerTime(cityId, date)),
                DataPrayerTimeResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showPrayerTimeList(data.jadwal)
            }
        }
    }
}