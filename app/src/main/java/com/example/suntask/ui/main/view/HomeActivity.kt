package com.example.suntask.ui.main.view

import android.content.Intent
import android.graphics.Movie
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.suntask.data.model.MoviesModel
import com.example.suntask.ui.main.base.MyApplication
import com.example.suntask.data.model.NetworkResult
import com.example.suntask.data.repository.SessionManager
import com.example.suntask.databinding.ActivityHomeBinding
import com.example.suntask.ui.main.adapter.MoviesAdapter
import com.example.suntask.ui.main.adapter.PagerAdapter
import com.example.suntask.ui.main.base.BaseActivity
import com.example.suntask.ui.main.viewmodel.CommonViewModel
import com.example.suntask.ui.utils.Constants
import com.example.suntask.ui.utils.gone
import com.example.suntask.ui.utils.showToast
import com.example.suntask.ui.utils.visible
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class HomeActivity : BaseActivity<CommonViewModel, ActivityHomeBinding>() {
    @Inject
    lateinit var sessionManager: SessionManager

    var topRatedAdapter = MoviesAdapter().apply { onClick = ::onClickMovie }
    var nowsPlayingAdapter = MoviesAdapter().apply { onClick = ::onClickMovie }
    var popularAdapter = MoviesAdapter().apply { onClick = ::onClickMovie }
    var upcomingAdapter = MoviesAdapter().apply { onClick = ::onClickMovie }
    var pagerAdapter = PagerAdapter().apply { onClick = ::onClickMovie }

    override fun getViewBinding(): ActivityHomeBinding =
        ActivityHomeBinding.inflate(layoutInflater)

    override fun initViews() {
        (application as MyApplication).component.inject(this)
        initListeners()
        initRecyclerview()
        setUpViewpager()

        observe()
        getPopularMovies()
        getTopRatedMovies()
        getNowsPlayingMovies()
        getUpcomingMovies()

    }

    fun getPopularMovies() {
        viewModel.apiRequest(Constants.REQ_POPULAR_MOVIES, hashMapOf())
    }


    fun getTopRatedMovies() {
        viewModel.apiRequest(Constants.REQ_TOP_RATED_MOVIES, hashMapOf())
    }


    fun getNowsPlayingMovies() {
        viewModel.apiRequest(Constants.REQ_NOWS_PLAYING_MOVIES, hashMapOf())
    }


    fun getUpcomingMovies() {
        viewModel.apiRequest(Constants.REQ_UPCOMING_MOVIES, hashMapOf())
    }

    fun initListeners() {

    }

    fun observe() {
        viewModel.liveData.observe(this) {
            when (it) {
                is NetworkResult.Success -> {

                    when (it.code) {
                        Constants.REQ_POPULAR_MOVIES -> {
                            popularAdapter.list = (it.data as MoviesModel).results

                        }

                        Constants.REQ_TOP_RATED_MOVIES -> {
                            topRatedAdapter.list = (it.data as MoviesModel).results

                        }

                        Constants.REQ_NOWS_PLAYING_MOVIES -> {
                            nowsPlayingAdapter.list = (it.data as MoviesModel).results

                        }

                        Constants.REQ_UPCOMING_MOVIES -> {
                            upcomingAdapter.list = (it.data as MoviesModel).results
                            pagerAdapter.list = (it.data as MoviesModel).results

                        }
                    }
                }

                is NetworkResult.Error -> {
                    this.showToast(it.message.toString())
                    binding.groupNoInternet.visible()
                    binding.clMain.gone()
                }

            }
        }
    }

    fun initRecyclerview() {
        binding.rvNowsPlaying.layoutManager = getLinearLayoutManager()
        binding.rvNowsPlaying.adapter = nowsPlayingAdapter

        binding.rvTopRated.layoutManager = getLinearLayoutManager()
        binding.rvTopRated.adapter = topRatedAdapter

        binding.rvPopular.layoutManager = getLinearLayoutManager()
        binding.rvPopular.adapter = popularAdapter

    }

    fun getLinearLayoutManager() = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    fun onClickMovie(model: MoviesModel.Results) {

        startActivity(
            Intent(this, ExoPlayerActivity::class.java).putExtra(
                Constants.MOVIES_MODEL,
                model
            )
        )

    }

    fun setUpViewpager() {
        binding.pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.pager.adapter = pagerAdapter

    }


}