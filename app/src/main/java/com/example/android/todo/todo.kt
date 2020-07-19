package com.example.android.todo

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class todo : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}