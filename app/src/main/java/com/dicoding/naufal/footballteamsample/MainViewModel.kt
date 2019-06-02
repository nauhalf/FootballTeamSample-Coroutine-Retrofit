package com.dicoding.naufal.footballteamsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.naufal.footballteamsample.data.RetrofitFactory
import com.dicoding.naufal.footballteamsample.data.remote.FootballTeamServices
import com.dicoding.naufal.footballteamsample.data.remote.team.TeamResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO

    private val scope = CoroutineScope(coroutineContext)

    private val service: FootballTeamServices = RetrofitFactory.footballServices()

    val isLoading: MutableLiveData<Boolean>

    val teams: MutableLiveData<List<TeamResponse.Team>>

    init {
        isLoading = MutableLiveData()
        isLoading.postValue(false)

        teams = MutableLiveData()
    }

    fun getData(){
        scope.launch {
            withContext(Dispatchers.Main){
                isLoading.postValue(true)
            }

            teams.postValue(service.getLookupAllTeams().await().body()?.listTeam)

            withContext(Dispatchers.Main){
                isLoading.postValue(false)
            }
        }
    }



    override fun onCleared() {
        parentJob.cancel()
        super.onCleared()
    }
}