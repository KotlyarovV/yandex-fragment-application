package com.example.vitaly.yandexapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Vitaly on 09.04.2018.
 */

public class ListFragment extends Fragment {

    public static final int REQUEST_CODE_CREATE_NOTE = 1;
    public static final int REQUEST_CODE_REDACT_NOTE = 2;

    public static final String REDACTED_NOTE = "REDACTED_NOTE";
    public static final String REDACTED_NOTE_NUMBER = "REDACTED_NOTE_NUMBER";
    public static final String ITEMS = "ITEMS";

    private ListView listView;
    private ArrayList<ListNote> items;
    private ListNoteAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.list_fragment, container, false);

        listView = (ListView) rootView.findViewById(R.id.listView);

        items = new ArrayList<>();
        items.add(new ListNote(0, "fff", "fff", new Date()));
        adapter = new ListNoteAdapter(getContext(), items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(handleItemClick(getContext()));


        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(handleAddButtonClick(getContext()));

        return rootView;
    }



    private AdapterView.OnItemClickListener handleItemClick(final Context context) {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListNote listNote = items.get(i);
                NoteEditorFragment noteEditorFragment = new NoteEditorFragment();

                Bundle bundle = new Bundle();
                bundle.putSerializable(REDACTED_NOTE, listNote);
                noteEditorFragment.setArguments(bundle);
                launchNoteEditor(noteEditorFragment);
            }
        };
    }

    private void launchNoteEditor(NoteEditorFragment noteEditorFragment) {

        noteEditorFragment.setTargetFragment(ListFragment.this, REQUEST_CODE_CREATE_NOTE);

        getFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, noteEditorFragment)
                .commit();
    }

    private View.OnClickListener handleAddButtonClick(final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoteEditorFragment noteEditorFragment = new NoteEditorFragment();
                launchNoteEditor(noteEditorFragment);
            }
        };
    }

    public static ListFragment newInstance() {
        ListFragment myFragment = new ListFragment();
        Bundle args = new Bundle();
        myFragment.setArguments(args);
        return myFragment;
    }
}
