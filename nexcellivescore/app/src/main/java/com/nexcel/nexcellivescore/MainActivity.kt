package com.nexcel.nexcellivescore

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexcel.nexcellivescore.viewmodel.MatchViewModel
import kotlinx.android.synthetic.main.activity_main.calendarNavigationList
import java.util.Date

class MainActivity : AppCompatActivity(), CalendarBarAdapter.ItemSelectedListener {

    lateinit var matchViewModel: MatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        matchViewModel.addFootballMatchListObserver(
            this,
            onMatchListChanged = { footballMatchList ->
                footballMatchList.forEach { match ->
                    Toast.makeText(this@MainActivity, "Match: $match", Toast.LENGTH_SHORT).show()
                }
            })
        setUpScorePage()
    }

    override fun onResume() {
        super.onResume()
        matchViewModel.updateCurrentMatchList()
    }

    override fun selectCalendar() {

    }

    override fun selectLiveItem() {

    }

    override fun selectDate(date: Date) {

    }

    private fun setUpScorePage() {
        calendarNavigationList.apply {
            adapter = CalendarBarAdapter(Date(), this@MainActivity)
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL, false
            )
        }
    }
}
