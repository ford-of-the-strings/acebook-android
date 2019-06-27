package com.fots.acebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.fots.acebook.models.Post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class CreatePostActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button submitBtn = findViewById(R.id.postSubmit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText body = findViewById(R.id.postBody);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference postRef = database.getReference("/posts");
                String key = postRef.child("posts").push().getKey();
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                Date currentDateTime = new Date();
                Post post = new Post(body.getText().toString(), currentDateTime, uid);

                postRef.child(key).setValue(post);

                Intent showListPostsActivity = new Intent(getApplicationContext(), ListPostsActivity.class);
                startActivity(showListPostsActivity);
            }
        });
    }

}
