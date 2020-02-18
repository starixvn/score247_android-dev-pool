package com.nexcel.nexcellivescore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nexcel.nexcellivescore.viewmodel.MatchViewModel

class MainActivity : AppCompatActivity() {

    lateinit var matchViewModel: MatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
