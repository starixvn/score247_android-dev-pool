package com.nexcel.nexcellivescore.feature.scorepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexcel.nexcellivescore.R
import com.nexcel.nexcellivescore.viewmodel.MatchViewModel
import kotlinx.android.synthetic.main.fragment_score_page.calendarNavigationList
import java.util.Date

class ScorePageFragment : Fragment(), CalendarBarAdapter.ItemSelectedListener {

    lateinit var matchViewModel: MatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_score_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarNavigationList.apply {
            adapter = CalendarBarAdapter(Date(), this@ScorePageFragment)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { activity ->
            matchViewModel = ViewModelProviders.of(activity).get(MatchViewModel::class.java)
            matchViewModel.addFootballMatchListObserver(
                this,
                onMatchListChanged = { footballMatchList ->
                    footballMatchList.forEach { match ->
                        Toast.makeText(context, "Match: $match", Toast.LENGTH_SHORT).show()
                    }
                })
            matchViewModel.updateCurrentMatchList()
        }
    }

    override fun selectLiveItem() {

    }

    override fun selectCalendar() {

    }

    override fun selectDate(date: Date) {

    }
}