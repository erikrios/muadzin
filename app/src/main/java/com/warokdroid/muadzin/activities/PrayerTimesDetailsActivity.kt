package com.warokdroid.muadzin.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.warokdroid.muadzin.R
import com.warokdroid.muadzin.apis.ApiRepository
import com.warokdroid.muadzin.models.City
import com.warokdroid.muadzin.models.DataPrayerTime
import com.warokdroid.muadzin.presenters.PrayerTimeMainPresenter
import com.warokdroid.muadzin.views.PrayerTimeMainView
import java.text.SimpleDateFormat
import java.util.*

class PrayerTimesDetailsActivity : AppCompatActivity(), PrayerTimeMainView {

    private lateinit var tvCityNameTitle: TextView
    private lateinit var tvDateToday: TextView
    private lateinit var tvSubuhTime: TextView
    private lateinit var tvTerbitTime: TextView
    private lateinit var tvDhuhaTime: TextView
    private lateinit var tvZuhurTime: TextView
    private lateinit var tvAsarTime: TextView
    private lateinit var tvMagribTime: TextView
    private lateinit var tvIsyaTime: TextView
    private lateinit var tvImsakTime: TextView
    private lateinit var progressBar: ProgressBar

    private var prayerTime: DataPrayerTime = DataPrayerTime(null, null)
    private lateinit var city: City
    private lateinit var presenter: PrayerTimeMainPresenter

    private lateinit var dateToday: String
    private lateinit var simpleDateFormat: SimpleDateFormat

    companion object {
        const val DATA_CITY_KEY = "data_city_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prayer_times_details)

        initViews()
        city = getDataIntent()
        setDate()
        val gson = Gson()
        val request = ApiRepository()
        presenter = PrayerTimeMainPresenter(this, request, gson)
        city.id?.let {
            presenter.getPrayerTimeList(it, dateToday)
        }
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showPrayerTimeList(data: DataPrayerTime?) {
        prayerTime = DataPrayerTime(data?.status, data?.data)
        bindViews()
    }

    private fun initViews() {
        tvCityNameTitle = findViewById(R.id.tv_city_name_title)
        tvDateToday = findViewById(R.id.tv_date_today)
        tvSubuhTime = findViewById(R.id.tv_subuh_time)
        tvTerbitTime = findViewById(R.id.tv_terbit_time)
        tvDhuhaTime = findViewById(R.id.tv_dhuha_time)
        tvZuhurTime = findViewById(R.id.tv_zuhur_time)
        tvAsarTime = findViewById(R.id.tv_asar_time)
        tvMagribTime = findViewById(R.id.tv_magrib_time)
        tvIsyaTime = findViewById(R.id.tv_isya_time)
        tvImsakTime = findViewById(R.id.tv_imsak_time)
        progressBar = findViewById(R.id.progress_bar)
    }

    private fun bindViews() {
        tvCityNameTitle.text = city.name
        tvDateToday.text = prayerTime.data?.date
        tvSubuhTime.text = prayerTime.data?.subuh
        tvTerbitTime.text = prayerTime.data?.terbit
        tvDhuhaTime.text = prayerTime.data?.dhuha
        tvZuhurTime.text = prayerTime.data?.dhuhur
        tvAsarTime.text = prayerTime.data?.ashar
        tvMagribTime.text = prayerTime.data?.maghrib
        tvIsyaTime.text = prayerTime.data?.isya
        tvImsakTime.text = prayerTime.data?.imsak
    }

    private fun getDataIntent(): City {
        return intent.getParcelableExtra(DATA_CITY_KEY)
    }

    @SuppressLint("SimpleDateFormat")
    private fun setDate() {
        simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        dateToday = simpleDateFormat.format(Date())
    }
}
