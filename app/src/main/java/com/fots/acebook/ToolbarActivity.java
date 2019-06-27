package com.fots.acebook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ToolbarActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_messenger) {
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.facebook.orca");
            try {
                startActivity(intent);
            }
            catch (android.content.ActivityNotFoundException ex){
                Toast.makeText(getApplicationContext(), "Please Install Facebook Messenger", Toast.LENGTH_LONG).show();
            }
        } else if (id == R.id.action_logout) {
            requestLogout();
        }

        return super.onOptionsItemSelected(item);
    }

    public void requestLogout() {

        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });
    }
}
