@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.smoothe.Screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.smoothe.Navigation.Screen
import com.example.smoothe.Navigation.SmootheRouter
import com.example.smoothe.R
import com.example.smoothe.data.signupViewModel
import com.example.smoothe.data.UIEvent

@Composable
fun SignupScreen(signupViewModel: signupViewModel = viewModel(),navController: NavHostController) {
    Box(modifier = Modifier, contentAlignment = Alignment.Center){
        Surface (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ){
            Column(modifier = Modifier) {
                NormaltextComponent(value = stringResource(id = R.string.heythere))
                HeadingtextComponent(value = stringResource(id = R.string.create_account))
                Spacer(modifier = Modifier.height(20.dp))
                MyTextField(labelvalue = stringResource(id = R.string.first_name),
                    imageVector = Icons.Outlined.Person,
                    onTextSelected = {
                        signupViewModel.onEvent(UIEvent.FirstNameChange(it))
                    },
                    errorStatus = signupViewModel.RegistrationUIState.value.firstNameError)
                MyTextField(labelvalue = stringResource(id = R.string.last_name),
                    imageVector = Icons.Outlined.Person,
                    onTextSelected = {
                        signupViewModel.onEvent(UIEvent.LastNameChange(it))
                    },
                    errorStatus = signupViewModel.RegistrationUIState.value.lastNameError)
                MyTextField(labelvalue = stringResource(id = R.string.email),
                    imageVector = Icons.Outlined.Email,
                    onTextSelected = {
                        signupViewModel.onEvent(UIEvent.EmailChange(it))
                    },
                    errorStatus = signupViewModel.RegistrationUIState.value.emailError)
                PasswordTextField(labelvalue = stringResource(id = R.string.Password),
                    imageVector = Icons.Outlined.Lock,
                    onTextSelected = {
                        signupViewModel.onEvent(UIEvent.PasswordChange(it))
                    },
                    errorStatus = signupViewModel.RegistrationUIState.value.passwordError)
                CheckboxComponent(value = stringResource(id = R.string.terms_and_conditions),
                    onTextSelected = {
                        navController.navigate("terms_and_conditions")
                    },
                    onCheckedChange = {
                        signupViewModel.onEvent(UIEvent.PrivacyPolicyCheckBoxClicked(it))
                    }
                )
                Spacer(modifier = Modifier.height(80.dp))
                ButtonComponent(value = stringResource(id = R.string.register),
                    onButtonClicked = {
                        if(signupViewModel.allvalidationspassed.value){
                            signupViewModel.signUp()
                            navController.navigate("home_screen")
                        }
                    }, isEnabled = signupViewModel.allvalidationspassed.value)
                DividerTextComponent()
                ClickableLoginTextComponent ( tryingtoLogin = true, onTextSelected = {
                    navController.navigate("login_screen")
                })

            }
        }

        if(signupViewModel.signupinprogress.value){
            CircularProgressIndicator()
        }


    }
    
}

@Composable
fun NormaltextComponent(value:String){
    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        )
    ,color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingtextComponent(value:String){
    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        )
        ,color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(labelvalue: String, imageVector: ImageVector,
                onTextSelected: (String) -> Unit,
                errorStatus:Boolean){

    val textvalue = remember {
        mutableStateOf("")
    }
    val passwordVisible = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.BgColor))
            .clip(RoundedCornerShape(4.dp)),
        label = { Text(text = labelvalue)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.colorPrimary),
            focusedLabelColor = colorResource(id = R.color.colorPrimary),
            cursorColor = colorResource(id = R.color.colorPrimary),
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textvalue.value,
        onValueChange = {
            textvalue.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(imageVector = imageVector, contentDescription = null)
        },
        isError = !errorStatus

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(labelvalue: String,
                      imageVector: ImageVector,
                      onTextSelected: (String) -> Unit,
                      errorStatus: Boolean){

    val localFocusManager = LocalFocusManager.current

    val password = remember {
        mutableStateOf("")
    }
    val passwordVisible = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.BgColor))
            .clip(RoundedCornerShape(4.dp)),
        label = { Text(text = labelvalue)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.colorPrimary),
            focusedLabelColor = colorResource(id = R.color.colorPrimary),
            cursorColor = colorResource(id = R.color.colorPrimary),
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        keyboardActions = KeyboardActions{
            localFocusManager.clearFocus()
        },
        maxLines = 1,
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(imageVector = imageVector, contentDescription = null)
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value){
                Icons.Filled.Visibility
            }else{
                Icons.Filled.VisibilityOff
            }
            var description = if(passwordVisible.value){
                stringResource(id = R.string.hide_password)
            }else{
                stringResource(id = R.string.show_password)
            }
            
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = imageVector, contentDescription = "")
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus
    )
}
@Composable
fun CheckboxComponent(value: String, onTextSelected: (String) -> Unit, onCheckedChange :(Boolean)->Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically) {
        val checkedState = remember{
            mutableStateOf(false)
        }
        Checkbox(checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = !checkedState.value
                onCheckedChange.invoke(it)
            }
        )
        ClickableTextComponent(value = value,onTextSelected)
    }
}
@Composable
fun ClickableTextComponent(value: String, onTextSelected: (String) -> Unit){
    val initialtext = "By continuing you accept our "
    val privacypolicytext = "Privacy Policy"
    val andtext = " and "
    val termsofusetext = "Terms of Use"

    val annotatedString = buildAnnotatedString {
        append(initialtext)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.colorPrimary))){
            pushStringAnnotation(tag = termsofusetext, annotation = termsofusetext)
            append(termsofusetext)
        }
    }
    ClickableText(
        text = annotatedString,
        onClick = { offset -> annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.also {
            Log.d("ClickableTextComponent", "ClickableTextComponent: $it")

            if (it.item == termsofusetext || it.item == privacypolicytext ){
                onTextSelected(it.item)
            }
        }
    }
    )
}
@Composable
fun ButtonComponent(value:String, onButtonClicked: () -> Unit, isEnabled : Boolean){
    Button(onClick = { onButtonClicked.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled
        ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        colorResource(id = R.color.colorSecondary),
                        colorResource(id = R.color.colorPrimary)
                    )
                ),
                shape = RoundedCornerShape(50.dp)
            ),contentAlignment = Alignment.Center) {
            Text(text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun DividerTextComponent(){
    Row (modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically){
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = colorResource(id = R.color.colorGray),
            thickness = 1.dp)
        Text(modifier = Modifier.padding(8.dp), text = "or", fontSize = 18.sp,
            color = colorResource(id = R.color.colorText))
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = colorResource(id = R.color.colorGray),
            thickness = 1.dp)
    }
}
@Composable
fun ClickableLoginTextComponent(tryingtoLogin:Boolean = true, onTextSelected: (String) -> Unit){
    val initialtext = if(tryingtoLogin) "Already have an account? " else "Don't have an account yet? "
    val Logintext = if(tryingtoLogin) "Login" else "Register"

    val annotatedString = buildAnnotatedString {
        append(initialtext)
        withStyle(style = SpanStyle(color = colorResource(id = R.color.colorPrimary))){
            pushStringAnnotation(tag = Logintext, annotation = Logintext)
            append(Logintext)
        }
    }
    ClickableText(
        text = annotatedString,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        onClick = { offset: Int -> annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.also {
            Log.d("ClickableTextComponent", "ClickableTextComponent: $it")

            if (it.item == Logintext){
                onTextSelected(it.item)
            }
        }
        }
    )
}


