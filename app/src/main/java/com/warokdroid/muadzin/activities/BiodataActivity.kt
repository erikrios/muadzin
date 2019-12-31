package com.warokdroid.muadzin.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.warokdroid.muadzin.R

class BiodataActivity : AppCompatActivity() {

    private lateinit var imgFacebook: ImageView
    private lateinit var imgGithub: ImageView
    private lateinit var imgInstagram: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biodata)

        initViews()
        viewList()
    }

    private fun initViews() {
        imgFacebook = findViewById(R.id.img_facebook)
        imgGithub = findViewById(R.id.img_github)
        imgInstagram = findViewById(R.id.img_instagram)
    }

    private fun viewList() {
        val socialMediaList = listOf<View>(imgFacebook, imgGithub, imgInstagram)
        for (item in socialMediaList) {
            setOnClick(item)
        }
    }

    private fun setOnClick(view: View) {
        when (view.id) {
            R.id.img_facebook -> view.setOnClickListener {
                setUrlIntent("https://www.facebook.com/erik.rio.setiawan")
            }
            R.id.img_github -> view.setOnClickListener {
                setUrlIntent("https://github.com/erikrios")
            }
            R.id.img_instagram -> view.setOnClickListener {
                setUrlIntent("https://www.instagram.com/erikriosetiawan/")
            }
        }
    }

    private fun setUrlIntent(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
