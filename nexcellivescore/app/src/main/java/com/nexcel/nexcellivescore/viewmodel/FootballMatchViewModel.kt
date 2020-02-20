package com.nexcel.nexcellivescore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nexcel.nexcellivescore.entity.FootballMatch
import com.nexcel.nexcellivescore.usecase.GetFootballMatchListUseCase
import com.nexcel.nexcellivescore.usecase.GetFootballMatchListUseCaseImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.Date

class FootballMatchViewModel : ViewModel() {
    private val matchesDataSource = MutableLiveData<List<FootballMatch>>()

    private val getFootballMatchListUseCase: GetFootballMatchListUseCase =
        GetFootballMatchListUseCaseImpl()
    private val compositeDisposable = CompositeDisposable()

    var currentDate: Date = Date()
        set(value) {
            field = value
            updateCurrentMatchList()
        }

    private fun updateCurrentMatchList() {
        getFootballMatchListUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = { footBallMatchList ->
                matchesDataSource.postValue(footBallMatchList)
            }, onError = {
                Timber.d("Failed to update match list with error: $it")
            }).addTo(compositeDisposable)
    }

    fun addFootballMatchListObserver(
        owner: LifecycleOwner,
        onMatchListChanged: (matchList: List<FootballMatch>) -> Unit
    ) {
        matchesDataSource.observe(owner, Observer {
            onMatchListChanged.invoke(it.orEmpty())
        })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}