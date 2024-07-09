package com.example.smoothe

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel : ViewModel(){
    private val TAG = HomeViewModel::class.simpleName

    val isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()

    fun checkForActiveSession() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            Log.d(ContentValues.TAG, "Valid session")
            isUserLoggedIn.value = true
        } else {
            Log.d(ContentValues.TAG, "User is not logged in")
            isUserLoggedIn.value = false
        }
    }
}