package com.example.suntask.ui.main.view

import android.R.attr.tag
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.util.Log
import android.widget.ImageButton
import com.example.suntask.R
import com.example.suntask.data.model.MoviesModel
import com.example.suntask.data.model.NetworkResult
import com.example.suntask.databinding.ActivityExoPlayerBinding
import com.example.suntask.ui.main.base.BaseActivity
import com.example.suntask.ui.main.viewmodel.ExoPlayerViewModel
import com.example.suntask.ui.utils.Constants
import com.example.suntask.ui.utils.showToast


class ExoPlayerActivity : BaseActivity<ExoPlayerViewModel, ActivityExoPlayerBinding>() {
    var videoLink = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

    override fun getViewBinding(): ActivityExoPlayerBinding =
        ActivityExoPlayerBinding.inflate(layoutInflater)

    override fun initViews() {
        setListeners()
        binding.videoView.player = viewModel.initializePlayer()
        prepareVideo(videoLink)
       // getMoviesDetails()
     //   observe()
    }

    fun setListeners() {
        binding.root.findViewById<ImageButton>(R.id.exo_back).setOnClickListener {
            if (isLandScape())
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            else
                onBackPressed()
        }

        binding.root.findViewById<ImageButton>(R.id.exo_fullscreen).setOnClickListener {
            if (isLandScape()) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
            else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        }
    }


    fun prepareVideo(videoId: String) {
        viewModel.prepare(videoId)
    }

    public override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        viewModel.play()
    }

    override fun onPause() {
        super.onPause()
        viewModel.pause()
    }

    fun isLandScape() =
        getString(R.string.is_lands).equals("true")

    fun getData() = intent.getParcelableExtra<MoviesModel.Results>(Constants.MOVIES_MODEL)
}