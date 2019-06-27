package com.fots.acebook;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

public class Authentication {

    public static Intent requestLoginIntent() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build()
        );

        Intent loginIntent = AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build();
        return loginIntent;
    }

    public static void requestLogout(Context context) {

        AuthUI.getInstance()
                .signOut(context)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }
                });
    }

    public static String getUID() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

}
