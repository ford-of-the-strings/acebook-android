package com.fots.acebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fots.acebook.models.Post;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class CreatePostActivity extends AppCompatActivity {

    private String TAG = "CreatePost";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button submitBtn = (Button) findViewById(R.id.postSubmit);


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference ref = database.getReference("/");

        DatabaseReference dataRef = ref.child("data");
        dataRef.setValue("I'm writing data", new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.out.println("Data could not be saved " + databaseError.getMessage());
                } else {
                    System.out.println("Data saved successfully.");
                }
            }
        });


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText body = (EditText) findViewById(R.id.postBody);


                FirebaseDatabase database = FirebaseDatabase.getInstance();

                final DatabaseReference myRef = database.getReference("/posts");

                String key = myRef.child("posts").push().getKey();
                Date currentDateTime = new Date();

//                        DateFormat.getDateTimeInstance().format(new Date());

                Post post = new Post(body.getText().toString(), currentDateTime);

                myRef.child(key).setValue(post);
                Log.i(TAG, myRef.toString());

                Intent showListPostsActivity = new Intent(getApplicationContext(), ListPostsActivity.class);
                startActivity(showListPostsActivity);

            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
