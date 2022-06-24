package ru.ozon.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ozon.ozon.R
import dagger.android.AndroidInjection


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
    }
}