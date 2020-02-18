package com.nexcel.nexcellivescore

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.nexcel.nexcellivescore.viewmodel.MatchViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

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
    }

    override fun onResume() {
        super.onResume()
        matchViewModel.updateCurrentMatchList()
    }
}
