package com.example.suntask.ui.main.viewmodel

import android.app.Application
import android.net.Uri
import android.util.Log
import android.util.SparseArray
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.suntask.R
import com.example.suntask.data.model.NetworkResult
import com.example.suntask.data.repository.Repository
import com.example.suntask.ui.main.base.MyApplication
import com.example.suntask.ui.utils.CommonMethods
import com.example.suntask.ui.utils.Constants
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


class ExoPlayerViewModel(var myapplication: Application) : AndroidViewModel(myapplication) {
    private var player: ExoPlayer? = null
    private var mediaSource: MediaSource? = null

    fun initializePlayer(): ExoPlayer {
        return player ?: ExoPlayer.Builder(myapplication).build().also {
            player = it
        }
    }

    fun prepare(url: String) {
        if (mediaSource == null) {
            val dataSourceFactory = DefaultDataSourceFactory(myapplication, "User-Agent")
            mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(url))
            player?.prepare(mediaSource!!)
        }
    }

    fun play() {
        player?.play()
    }

    fun pause() {
        player?.pause()
    }

    override fun onCleared() {
        super.onCleared()
        releasePlayer()
    }

    fun releasePlayer() {
        player?.release()
    }

}