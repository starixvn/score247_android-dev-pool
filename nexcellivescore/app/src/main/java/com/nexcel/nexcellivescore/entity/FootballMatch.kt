package com.nexcel.nexcellivescore.entity

import com.nexcel.nexcellivescore.Constant.Companion.DEFAULT_FOOTBALL_MATCH_DURATION
import timber.log.Timber
import java.util.Date
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

data class FootballMatch(
    val startDateTime: Date,
    val homeTeam: String,
    val awayTeam: String
) {
    private var duration = AtomicInteger(0)

    private var isPlaying = AtomicBoolean(false)
    private var homeTeamScore = AtomicInteger(0)
    private var awayTeamScore = AtomicInteger(0)

    fun startMatch(): Boolean {
        val matchStartingResult = isPlaying.compareAndSet(false, true)
        Timber.d("Match starting result: $matchStartingResult")
        return matchStartingResult
    }

    fun updateDuration(currentDuration: Int): Boolean {
        return if (currentDuration > duration.get()) {
            duration.compareAndSet(duration.get(), currentDuration)
        } else {
            false
        }
    }

    fun updateHomeTeamScore(currentHomeTeamScore: Int): Boolean {
        return if (currentHomeTeamScore > homeTeamScore.get()) {
            homeTeamScore.compareAndSet(homeTeamScore.get(), currentHomeTeamScore)
        } else {
            false
        }
    }

    fun updateAwayTeamScore(currentAwayTeamScore: Int): Boolean {
        return if (currentAwayTeamScore > awayTeamScore.get()) {
            awayTeamScore.compareAndSet(awayTeamScore.get(), currentAwayTeamScore)
        } else {
            false
        }
    }

    fun duration(): Int = duration.get()
    fun homeTeamScore(): Int = homeTeamScore.get()
    fun awayTeamScore(): Int = awayTeamScore.get()

    fun endMatch(): Boolean {
        val matchEndingResult = isPlaying.compareAndSet(true, false) &&
                duration.get() >= DEFAULT_FOOTBALL_MATCH_DURATION
        Timber.d("Match starting result: $matchEndingResult")
        return matchEndingResult
    }
}