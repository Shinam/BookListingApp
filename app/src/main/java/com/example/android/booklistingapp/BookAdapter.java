package com.example.android.booklistingapp;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Shinam on 15/07/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        EventViewHolder viewHolder = (EventViewHolder) convertView.getTag();

        if (viewHolder == null) {
            viewHolder = new EventViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.author = (TextView) convertView.findViewById(R.id.author);
            convertView.setTag(viewHolder);
        }

        Book currentBook = getItem(position);

        viewHolder.title.setText(currentBook.getTitle());
        viewHolder.author.setText(currentBook.getAuthors());
        return convertView;
    }

    private class EventViewHolder {
        public TextView title;
        public TextView author;
    }
}