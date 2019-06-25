package com.fots.acebook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fots.acebook.models.Post;
import com.fots.acebook.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class PostAdapter extends BaseAdapter {

    ArrayList<Post> posts;

    User user;
    String name;

    LayoutInflater mInflator;
    @Override
    public int getCount() {
        return posts.size();
    }

    public PostAdapter(Context c, ArrayList<Post> ps) {
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

        TextView timeTextView = (TextView) v.findViewById(R.id.timeView);
        TextView bodyTextView = (TextView) v.findViewById(R.id.bodyView);
        TextView usernameTextView = (TextView) v.findViewById(R.id.usernameView);

        Date time = posts.get(position).getTime();
        String body = posts.get(position).getBody();
        String uid = posts.get(position).getUid();

        PrettyTime time_display = new PrettyTime();

        timeTextView.setText(time_display.format(time));
        bodyTextView.setText(body);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = db.getReference("/users");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
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
}
