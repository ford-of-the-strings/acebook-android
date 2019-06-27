package com.fots.acebook;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fots.acebook.models.Post;
import com.fots.acebook.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.ArrayList;
import java.util.Date;

public class PostAdapter extends BaseAdapter {

    ArrayList<Post> posts;
    Context context;

    User user;
    String name;

    public static final String POST_BODY = "com.fots.acebook.BODY";
    public static final String POST_ID = "com.fots.acebook.ID";

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    final DatabaseReference postRef = db.getReference("/posts");
    final DatabaseReference userRef = db.getReference("/users");


    LayoutInflater mInflator;
    @Override
    public int getCount() {
        return posts.size();
    }

    public PostAdapter(Context c, ArrayList<Post> ps) {
        context = c;
        posts = ps;
        mInflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflator.inflate(R.layout.content_list_post_view, null);

        TextView timeTextView = v.findViewById(R.id.timeView);
        TextView bodyTextView = v.findViewById(R.id.bodyView);
        TextView usernameTextView = v.findViewById(R.id.usernameView);
        TextView likesDisplay = v.findViewById(R.id.likesText);

        AppCompatImageButton likeButton = v.findViewById(R.id.likeButton);
        AppCompatImageButton deleteButton = v.findViewById(R.id.deleteButton);
        AppCompatImageButton editButton = v.findViewById(R.id.editButton);

        Date time = posts.get(position).getTime();
        String body = posts.get(position).getBody();
        String uid = posts.get(position).getUid();
        String postId = posts.get(position).getPostId();
        int numberOfLikes = posts.get(position).getNumberOfLikes();

        if(uid.equals(Authentication.getUID())) {
            deleteButton.setVisibility(View.VISIBLE);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deletePost(postId);
                }
            });

            editButton.setVisibility(View.VISIBLE);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UpdatePostActivity.class);
                    intent.putExtra(POST_BODY, body);
                    intent.putExtra(POST_ID, postId);
                    context.startActivity(intent);
                }
            });

        }

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postRef.child(postId).child("numberOfLikes").setValue(numberOfLikes + 1);
            }
        });

        likesDisplay.setText(numberOfLikes + " people like this");

        PrettyTime time_display = new PrettyTime();

        timeTextView.setText(time_display.format(time));
        bodyTextView.setText(body);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.child(uid).getValue(User.class);
                name = user.getName();
                usernameTextView.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return v;
    }
    private void deletePost(String id) {
        postRef.child(id).removeValue();
    }
}
