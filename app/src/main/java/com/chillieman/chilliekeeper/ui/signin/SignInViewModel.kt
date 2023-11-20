package com.chillieman.chilliekeeper.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chillieman.chilliekeeper.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
@Inject constructor() : ViewModel() {
    private val _errorState = MutableLiveData<List<Int>>(emptyList())
    val errorState: LiveData<List<Int>>
        get() = _errorState

    fun consumeErrorState() {
        _errorState.value = emptyList()
    }

    fun outOfScopeError() {
        _errorState.value = listOf(R.string.login_error_out_of_scope)
    }

    fun signIn(
        email: String,
        password: String,
        onSignInComplete: () -> Unit,
    ) {
        // Validate Input
        val isEmailValid = validateEmail(email)
        val isPasswordValid = validatePassword(password)

        if(isEmailValid && isPasswordValid) {
            // Consider any sign in attempt as successful if input is valid
            onSignInComplete()
        } else {
            // There was an error and at least one error message must be displayed
            val errorResIds = mutableListOf<Int>()
            if(!isEmailValid) {
                errorResIds.add(R.string.login_error_email)
            }
            if(!isPasswordValid) {
                errorResIds.add(R.string.login_error_password)
            }
            _errorState.value = errorResIds
        }
    }

    private fun validateEmail(email: String): Boolean {
        return Regex(REQUIRED_EMAIL_REGEX).matches(email)
    }

    private fun validatePassword(password: String): Boolean {
        return password.length >= REQUIRED_PASSWORD_LENGTH
    }

    companion object {
        const val REQUIRED_PASSWORD_LENGTH = 6
        const val REQUIRED_EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$"
    }
}
