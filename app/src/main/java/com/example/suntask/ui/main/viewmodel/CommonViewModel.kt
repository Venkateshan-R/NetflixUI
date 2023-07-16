package com.example.suntask.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.suntask.ui.main.base.MyApplication
import com.example.suntask.R
import com.example.suntask.data.model.NetworkResult
import com.example.suntask.data.repository.Repository
import com.example.suntask.ui.utils.CommonMethods
import com.example.suntask.ui.utils.Constants.REQ_MOVIE_DETAILS
import com.example.suntask.ui.utils.Constants.REQ_NOWS_PLAYING_MOVIES
import com.example.suntask.ui.utils.Constants.REQ_POPULAR_MOVIES
import com.example.suntask.ui.utils.Constants.REQ_TOP_RATED_MOVIES
import com.example.suntask.ui.utils.Constants.REQ_UPCOMING_MOVIES
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class CommonViewModel(var myApplication: Application) : AndroidViewModel(myApplication) {

    var liveData: MutableLiveData<NetworkResult<Any>> = MutableLiveData()

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var commonMethods: CommonMethods

    init {
        (myApplication as MyApplication).component.inject(this)
    }

    fun apiRequest(reqCode: Int, hashMap: HashMap<String, String>) {
        if (commonMethods.isInternetConnected(myApplication)) {
            viewModelScope.launch {
                var response = when (reqCode) {
                    REQ_POPULAR_MOVIES -> {
                        repository.getPopularMovies()
                    }

                    REQ_NOWS_PLAYING_MOVIES -> {
                        repository.getNowsPlaying()
                    }

                    REQ_TOP_RATED_MOVIES -> {
                        repository.getTopRated()
                    }

                    REQ_UPCOMING_MOVIES -> {
                        repository.getUpcoming()
                    }

                    else -> return@launch
                }
                var result = handleResponse(reqCode, response as Response<Any>)
                liveData.value = result
            }
        } else {
            liveData.value = NetworkResult.Error(
                reqCode,
                myApplication.getString(R.string.please_make_sure_you_have_internet_connection)
            )
        }
    }

    fun handleResponse(reqCode: Int, response: Response<Any>): NetworkResult<Any> {
        if (response.isSuccessful) {
            return NetworkResult.Success(reqCode, response.body() as Any)
        } else {
            return NetworkResult.Error(reqCode, response.errorBody()?.string())
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}