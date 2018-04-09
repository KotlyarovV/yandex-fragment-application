package com.example.vitaly.yandexapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vitaly on 17.03.2018.
 */

public class ListNoteAdapter extends BaseAdapter {

    private List<ListNote> notes;
    private LayoutInflater layoutInflater;

    public ListNoteAdapter(Context context, List<ListNote> notes) {
        this.notes = notes;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int i) {
        return notes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_layout, viewGroup, false);
        }

        ListNote note = getListNote(i);

        TextView textViewCaption = (TextView) view.findViewById(R.id.caption);
        TextView textViewDescription = (TextView) view.findViewById(R.id.description);
        TextView textViewDate = (TextView) view.findViewById(R.id.date);
        TextView textViewTime = (TextView) view.findViewById(R.id.time);
        ImageView imageView = (ImageView) view.findViewById(R.id.color);

        textViewCaption.setText(note.getCaption());
        textViewDescription.setText(note.getDescription());
        textViewDate.setText(note.getDate());
        textViewTime.setText(note.getTime());
        imageView.setBackgroundColor(note.getColor());

        return view;
    }

    private ListNote getListNote(int i) {
        return (ListNote) getItem(i);
    }
}
