package com.example.smoothe.Navigation

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavHostController

private val LocalBackPressedDispatcher = staticCompositionLocalOf<OnBackPressedDispatcherOwner?> { null }

private class ComposableBackNavigationHandler(enabled: Boolean) : OnBackPressedCallback(enabled){
    lateinit var onBackPressed: ()-> Unit
    override fun handleOnBackPressed() {
        onBackPressed()
    }

}
@Composable
internal fun ComposableHandler(
    enabled: Boolean = true,
    onBackPressed: () -> Unit
) {
    val dispatcher = (LocalBackPressedDispatcher.current ?: return).onBackPressedDispatcher

    val handler = remember { ComposableBackNavigationHandler(enabled) }

    DisposableEffect(dispatcher) {
        dispatcher.addCallback(handler)


        onDispose { handler.remove() }
    }

    LaunchedEffect(enabled) {
        handler.isEnabled = enabled
        handler.onBackPressed = onBackPressed
    }
}
@Composable
internal fun SystemBackButtonHandler(navController: NavHostController) {
    val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val activity = LocalContext.current as? Activity // Get Activity context

    DisposableEffect(dispatcher) {
        val backCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (navController.currentBackStackEntry?.destination?.route != "login_screen") { // Replace with your actual start destination
                    navController.popBackStack()
                } else {
                    activity?.finish() // Close the app if on the start destination
                }
            }
        }
        dispatcher?.addCallback(backCallback)

        onDispose {
            backCallback.remove()
        }
    }
//    CompositionLocalProvider(
//        LocalBackPressedDispatcher provides LocalLifecycleOwner.current as ComponentActivity
//    ) {
//        ComposableHandler {
//            onBackPressed()
//        }
//    }
}