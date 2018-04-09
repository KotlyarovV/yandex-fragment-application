package com.example.vitaly.yandexapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Vitaly on 09.04.2018.
 */

public class NoteEditorFragment extends Fragment {

    public static final String NODE_EDITOR_DATA = "node_editor_data";
    public static final int DELETE_RESULT = 1;

    private boolean redactionMode = false;
    private ColorProvider colorProvider;
    private ListNote note;

    private EditText descriptionEditor;
    private EditText captionEditor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.note_editor_fragment, container, false);

        descriptionEditor = (EditText) rootView.findViewById(R.id.description_editor);
        captionEditor = (EditText) rootView.findViewById(R.id.caption_editor);

        return rootView;
    }
    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_Holo_Light);
    }
    */
}
