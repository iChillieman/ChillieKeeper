package com.chillieman.chilliekeeper.ui.signin

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun SignInRoute(
    onSignInSuccess: () -> Unit,
    onNavUp: () -> Unit,
) {
    val viewModel = hiltViewModel<SignInViewModel>()
    SignInScreen(
        viewModel,
        onSignInSuccess = onSignInSuccess,
        onNavUp = onNavUp,
    )
}
