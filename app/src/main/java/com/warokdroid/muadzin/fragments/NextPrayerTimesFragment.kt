package com.warokdroid.muadzin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.gson.Gson
import com.warokdroid.muadzin.R
import com.warokdroid.muadzin.apis.ApiRepository
import com.warokdroid.muadzin.models.DataPrayerTime
import com.warokdroid.muadzin.presenters.PrayerTimeMainPresenter
import com.warokdroid.muadzin.views.PrayerTimeMainView

class NextPrayerTimesFragment : DialogFragment(), PrayerTimeMainView {

    private lateinit var tvDate: TextView
    private lateinit var tvSubuhTime: TextView
    private lateinit var tvTerbitTime: TextView
    private lateinit var tvDhuhaTime: TextView
    private lateinit var tvZuhurTime: TextView
    private lateinit var tvAsarTime: TextView
    private lateinit var tvMagribTime: TextView
    private lateinit var tvIsyaTime: TextView
    private lateinit var tvImsakTime: TextView
    private lateinit var progressBar: ProgressBar

    private lateinit var root: View

    private lateinit var cityId: String
    private lateinit var date: String

    private lateinit var presenter: PrayerTimeMainPresenter

    private var prayerTime: DataPrayerTime = DataPrayerTime(null, null)

    companion object {
        const val DATA_CITY_ID_KEY = "data_city_id_key"
        const val DATA_NEXT_DATE_KEY = "data_next_date_key"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (arguments != null) {
            cityId = arguments?.getString(DATA_CITY_ID_KEY).toString()
            date = arguments?.getString(DATA_NEXT_DATE_KEY).toString()
        }
        root = inflater.inflate(R.layout.fragment_next_prayer_times, container, false)
        initViews()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gson = Gson()
        val request = ApiRepository()
        presenter = PrayerTimeMainPresenter(this, request, gson)
        presenter.getPrayerTimeList(cityId, date)
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
        tvDate = root.findViewById(R.id.tv_date)
        tvSubuhTime = root.findViewById(R.id.tv_subuh_time)
        tvTerbitTime = root.findViewById(R.id.tv_terbit_time)
        tvDhuhaTime = root.findViewById(R.id.tv_dhuha_time)
        tvZuhurTime = root.findViewById(R.id.tv_zuhur_time)
        tvAsarTime = root.findViewById(R.id.tv_asar_time)
        tvMagribTime = root.findViewById(R.id.tv_magrib_time)
        tvIsyaTime = root.findViewById(R.id.tv_isya_time)
        tvImsakTime = root.findViewById(R.id.tv_imsak_time)
        progressBar = root.findViewById(R.id.progress_bar)
    }

    private fun bindViews() {
        tvDate.text = prayerTime.data?.date
        tvSubuhTime.text = prayerTime.data?.subuh
        tvTerbitTime.text = prayerTime.data?.terbit
        tvDhuhaTime.text = prayerTime.data?.dhuha
        tvZuhurTime.text = prayerTime.data?.dhuhur
        tvAsarTime.text = prayerTime.data?.ashar
        tvMagribTime.text = prayerTime.data?.maghrib
        tvIsyaTime.text = prayerTime.data?.isya
        tvImsakTime.text = prayerTime.data?.imsak
    }
}