package com.example.suntask.data.dependency

import com.example.suntask.ui.main.view.ExoPlayerActivity
import com.example.suntask.ui.main.view.HomeActivity
import com.example.suntask.ui.main.viewmodel.CommonViewModel
import com.example.suntask.ui.main.viewmodel.ExoPlayerViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface ApplicationComponent {

    fun inject(homeActivity: HomeActivity)
    fun inject(homeActivity: ExoPlayerActivity)
    fun inject(commonViewModel: CommonViewModel)
    fun inject(exoPlayerViewModel: ExoPlayerViewModel)

  }