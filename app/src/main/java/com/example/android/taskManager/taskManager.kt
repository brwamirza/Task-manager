package com.example.android.taskManager

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class taskManager : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}