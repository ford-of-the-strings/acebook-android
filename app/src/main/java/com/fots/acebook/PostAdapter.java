package com.fots.acebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fots.acebook.models.Post;

import java.util.ArrayList;
import java.util.Collections;

public class PostAdapter extends BaseAdapter {

    ArrayList<Post> posts;

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

        TextView titleTextView = (TextView) v.findViewById(R.id.titleView);
        TextView bodyTextView = (TextView) v.findViewById(R.id.bodyView);

        String title = posts.get(position).getTitle();
        String body = posts.get(position).getBody();

        titleTextView.setText(title);
        bodyTextView.setText(body);

        return v;
    }
}
