package com.warokdroid.muadzin.views

import com.warokdroid.muadzin.models.DataPrayerTime

interface PrayerTimeMainView {

    fun showLoading()
    fun hideLoading()
    fun showPrayerTimeList(data: DataPrayerTime?)
}