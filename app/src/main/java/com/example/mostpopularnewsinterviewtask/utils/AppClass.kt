package com.example.mostpopularnewsinterviewtask.utils

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class AppClass: Application() {

    @Override
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}