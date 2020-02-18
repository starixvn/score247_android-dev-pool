package com.nexcel.nexcellivescore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nexcel.nexcellivescore.entity.FootballMatch

class MatchViewModel(application: Application) : AndroidViewModel(application) {
    private val matchesDataSource = MutableLiveData<List<FootballMatch>>()

    fun addFootballMatchsObserver(
        owner: LifecycleOwner,
        onMatchListChanged: (matchList: List<FootballMatch>) -> Unit
    ) {
        matchesDataSource.observe(owner, Observer {
            onMatchListChanged.invoke(it.orEmpty())
        })
    }
}