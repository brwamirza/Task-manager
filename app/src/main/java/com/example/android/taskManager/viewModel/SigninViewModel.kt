package com.example.android.taskManager.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.android.taskManager.FirebaseUserLiveData
import kotlinx.android.synthetic.main.activity_signin.*

class SigninViewModel : ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED
    }

    val authenticationState = FirebaseUserLiveData()
        .map { user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }
}
