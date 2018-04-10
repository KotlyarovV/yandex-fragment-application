package com.example.vitaly.yandexapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items = new ArrayList<>();
        adapter = new ListNoteAdapter(getContext(), items);
        if (savedInstanceState != null) {
            List<ListNote> items = (ArrayList<ListNote>) savedInstanceState.getSerializable(ITEMS);
            this.items.addAll(items);
            adapter.notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.list_fragment, container, false);

        ((MainActivity) getActivity()).setActionBar(R.color.colorPrimary, getString(R.string.app_name));
        listView = (ListView) rootView.findViewById(R.id.listView);
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
                bundle.putInt(REDACTED_NOTE_NUMBER, i);
                noteEditorFragment.setArguments(bundle);
                launchNoteEditor(noteEditorFragment, REQUEST_CODE_REDACT_NOTE);
            }
        };
    }

    private void launchNoteEditor(NoteEditorFragment noteEditorFragment, int code) {

        noteEditorFragment.setTargetFragment(ListFragment.this, code);

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
                launchNoteEditor(noteEditorFragment, REQUEST_CODE_CREATE_NOTE);
            }
        };
    }

    public static ListFragment newInstance() {
        ListFragment myFragment = new ListFragment();
        Bundle args = new Bundle();
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CREATE_NOTE && resultCode == RESULT_OK) {
            ListNote listNote = (ListNote) data.getSerializableExtra(NoteEditorFragment.NODE_EDITOR_DATA);
            items.add(listNote);
            adapter.notifyDataSetChanged();
        }

        if (requestCode == REQUEST_CODE_REDACT_NOTE && resultCode == RESULT_OK) {
            ListNote listNote = (ListNote) data.getSerializableExtra(NoteEditorFragment.NODE_EDITOR_DATA);
            int i = data.getIntExtra(REDACTED_NOTE_NUMBER, 0);
            items.set(i, listNote);
            adapter.notifyDataSetChanged();
        }

        if (requestCode == REQUEST_CODE_REDACT_NOTE && resultCode == NoteEditorFragment.DELETE_RESULT) {
            int i = data.getIntExtra(REDACTED_NOTE_NUMBER, 0);
            items.remove(i);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ITEMS, items);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null && items.size() == 0) {
            List<ListNote> items = (ArrayList<ListNote>) savedInstanceState.getSerializable(ITEMS);
            this.items.addAll(items);
            adapter.notifyDataSetChanged();
        }
    }
}
