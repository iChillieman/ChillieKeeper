package com.chillieman.chilliekeeper.ui.signin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chillieman.chilliekeeper.R
import com.chillieman.chilliekeeper.ui.theme.ChillieKeeperTheme
import com.chillieman.chilliekeeper.ui.theme.KeeperYellow

@Composable
fun SignInScreen(
    viewModel: SignInViewModel,
    onSignInSuccess: () -> Unit,
    onNavUp: () -> Unit,
) {
    val errorIdList = viewModel.errorState.observeAsState().value
    if (!errorIdList.isNullOrEmpty()) {
        ErrorDialog(
            onConfirmation = { viewModel.consumeErrorState() },
            dialogTitleId = R.string.login_error_title,
            dialogErrors = errorIdList
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(), color = Color.Black
    ) {

        Image(
            painter = painterResource(id = R.drawable.vault_outline_2560),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Logo
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(.7f)
                    .height(60.dp)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.login_black_logo),
                    contentDescription = "Logo"
                )
                Image(
                    painter = painterResource(id = R.drawable.login_black_logo_title),
                    contentDescription = "Logo Title",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))

            //Login
            Text(
                text = stringResource(id = R.string.login),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding()
            )

            Spacer(modifier = Modifier.height(32.dp))

            val focusRequester = remember { FocusRequester() }
            val emailState by remember {
                mutableStateOf(TextFieldState())
            }
            val passwordState by remember {
                mutableStateOf(TextFieldState())
            }

            Email(emailState, onImeAction = { focusRequester.requestFocus() })

            Spacer(modifier = Modifier.height(16.dp))

            val onSubmit = {
                viewModel.signIn(emailState.text, passwordState.text, onSignInSuccess)
            }

            Password(passwordState = passwordState,
                modifier = Modifier.focusRequester(focusRequester),
                onImeAction = { onSubmit() })


            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { onSubmit() },
                shape = RoundedCornerShape(4.dp),
                enabled = emailState.text.isNotEmpty() && passwordState.text.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.login)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        viewModel.outOfScopeError()
                    }
            ) {
                Text(
                    text = stringResource(id = R.string.no_account),
                    color = Color.White
                )
                Text(
                    text = stringResource(id = R.string.create),
                    color = KeeperYellow,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(id = R.string.need_help),
                color = KeeperYellow,
                modifier = Modifier.clickable { viewModel.outOfScopeError() }
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedButton(
                onClick = { viewModel.outOfScopeError() },
                shape = RoundedCornerShape(4.dp),
                border = BorderStroke(1.dp, KeeperYellow),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.use_enterprise_login),
                    color = KeeperYellow
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(id = R.string.copyright),
                color = Color.Gray,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(
    emailState: TextFieldState, imeAction: ImeAction = ImeAction.Next, onImeAction: () -> Unit = {}
) {
    OutlinedTextField(
        value = emailState.text,
        onValueChange = {
            emailState.text = it
        },
        label = {
            Text(
                text = stringResource(id = R.string.email),
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        modifier = Modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodyMedium,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction, keyboardType = KeyboardType.Email
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
        }),
    )
}

@OptIn(ExperimentalMaterial3Api::class) // OutlinedTextField is experimental in m3
@Composable
fun Password(
    passwordState: TextFieldState,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {}
) {
    val showPassword = rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(
        value = passwordState.text,
        onValueChange = {
            passwordState.text = it
        },
        modifier = modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodyMedium,
        label = {
            Text(
                text = stringResource(id = R.string.master_password),
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        trailingIcon = {
            if (showPassword.value) {
                IconButton(onClick = { showPassword.value = false }) {
                    Icon(
                        painter = painterResource(id = R.drawable.eye_on),
                        contentDescription = stringResource(id = R.string.hide_password)
                    )
                }
            } else {
                IconButton(onClick = { showPassword.value = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.eye_off),
                        contentDescription = stringResource(id = R.string.show_password)
                    )
                }
            }
        },
        visualTransformation = if (showPassword.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction, keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
        })
    )
}

@Preview
@Composable
fun SignInPreview() {
    ChillieKeeperTheme {
        SignInScreen(SignInViewModel(), onSignInSuccess = {}, onNavUp = {})
    }
}
