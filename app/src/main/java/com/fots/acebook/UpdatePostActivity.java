package com.fots.acebook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class UpdatePostActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_post);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button updateBtn = findViewById(R.id.postUpdate);
        EditText body = findViewById(R.id.updateBody);

        Intent intent = getIntent();
        String postBody = intent.getStringExtra(PostAdapter.POST_BODY);
        String postId = intent.getStringExtra(PostAdapter.POST_ID);
        body.setText(postBody);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = db.getReference("/posts");
                myRef.child(postId).child("body").setValue(body.getText().toString());

                Intent showListPostsActivity = new Intent(getApplicationContext(), ListPostsActivity.class);
                startActivity(showListPostsActivity);
            }
        });
    }
}
