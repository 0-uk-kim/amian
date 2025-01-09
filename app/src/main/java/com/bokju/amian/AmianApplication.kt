package com.bokju.amian

import android.app.Application
import com.bokju.amian.di.initKoin
import org.koin.android.ext.koin.androidContext

class AmianApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@AmianApplication)
        }
    }
}