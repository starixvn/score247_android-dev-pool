package com.nexcel.nexcellivescore.feature.scorepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.nexcel.nexcellivescore.Constant.Companion.TIME
import com.nexcel.nexcellivescore.utils.decrease
import com.nexcel.nexcellivescore.utils.increase
import com.nexcel.nexcellivescore.utils.isTheSameDay
import java.util.Date

class ScorePagerAdapter(
    fragmentManager: FragmentManager,
    private val today: Date
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val dateList = arrayListOf<Date>().apply {
        add(Date())
    }

    init {
        for (index in 1 .. TOTAL_ITEMS) {
            val currentDate = getDateOfPosition(index)
            dateList.add(currentDate)
        }
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> LiveFragment()
            else -> {
                MatchListFragment().apply {
                    arguments = Bundle().apply {
                        putLong(TIME, dateList[position].time)
                    }
                }
            }
        }
    }

    private fun getDateOfPosition(position: Int): Date {
        return when {
            position > TODAY_INDEX -> today.increase(position - TODAY_INDEX)
            position < TODAY_INDEX -> today.decrease(TODAY_INDEX - position)
            else -> today
        }
    }

    override fun getCount(): Int = TOTAL_ITEMS

    fun indexOfDate(date: Date): Int {
        return dateList.indexOfLast {
            it.isTheSameDay(date)
        }
    }

    companion object {
        private const val TOTAL_ITEMS = 6
        private const val TODAY_INDEX = TOTAL_ITEMS / 2
    }
}