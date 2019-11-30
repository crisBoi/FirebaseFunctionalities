package com.example.xymmetryprototype_0

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth

public class Login(
    val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    val providers: List<AuthUI.IdpConfig> = arrayListOf(
        AuthUI.IdpConfig.GoogleBuilder().build()
    ),
    val RC_SIGN_IN: Int = 1
) {


    public fun getCurrentUser(): String? {
        val currentUser = auth.currentUser
        return if (currentUser != null) currentUser.email!!.replace("@.","_")
        else null
    }

    public fun signInWithGoogle(): Intent = AuthUI.getInstance().
        createSignInIntentBuilder().
        setAvailableProviders(providers).
        setIsSmartLockEnabled(false).
        build()

    public fun validateGoogleSignIn(requestCode: Int, resultCode: Int): Boolean {
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                return true
            }
        }

        return false
    }

   /* public fun signInWithEmail(): Intent = {
//        val acs  = ActionCodeSettings.newBuilder()
        val provider = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )


    }*/

    public fun signOut(c: Context) {
//        AuthUI.getInstance().signOut(c)
        AuthUI.getInstance().signOut(c).addOnCompleteListener {  }
    }
}