package com.nexcel.nexcellivescore.feature.scorepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.nexcel.nexcellivescore.R
import kotlinx.android.synthetic.main.fragment_score_page.calendarNavigationList
import kotlinx.android.synthetic.main.fragment_score_page.dailyScoreList
import java.util.Date

class ScorePageFragment : Fragment(),
    CalendarBarAdapter.ItemSelectedListener,
    OnPageChangeListener {
    private lateinit var scorePagerAdapter: ScorePagerAdapter
    private lateinit var calendarBarAdapter: CalendarBarAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_score_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val today = Date()
        calendarNavigationList.apply {
            calendarBarAdapter = CalendarBarAdapter(today, this@ScorePageFragment)
            adapter = calendarBarAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        dailyScoreList.apply {
            activity?.supportFragmentManager?.let { fragmentManager ->
                scorePagerAdapter = ScorePagerAdapter(fragmentManager, today)
                adapter = scorePagerAdapter
                addOnPageChangeListener(this@ScorePageFragment)
            }
        }
        selectDate(today)
    }

    override fun selectLiveItem() {
        dailyScoreList.setCurrentItem(0, true)
    }

    override fun selectCalendar() {

    }

    override fun selectDate(date: Date) {
        scorePagerAdapter.indexOfDate(date).takeIf { dateIndex -> dateIndex >= 0 }
            ?.let { dateIndex ->
                dailyScoreList.setCurrentItem(dateIndex, true)
            }
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        calendarBarAdapter.currentFocusedPosition = position
    }
}