package com.example.android.todo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.android.todo.FirebaseUserLiveData

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
