package com.example.v_mipark.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    private ArrayAdapter<NoteInfo> mAdapteNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteListActivity.this, NoteActivity.class));
            }
        });

        initalizeDisplayContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapteNote.notifyDataSetChanged();
    }

    private void initalizeDisplayContent() {
        final ListView listNotes = (ListView) findViewById(R.id.list_notes);

        List<NoteInfo> notes = DataManager.getInstance().getNotes();
        mAdapteNote =  new ArrayAdapter<NoteInfo>(this, android.R.layout.simple_list_item_1, notes);

        listNotes.setAdapter(mAdapteNote);

        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);
//                    NoteInfo note = (NoteInfo) listNotes.getItemAtPosition(position);
                    intent.putExtra(NoteActivity.NOTE_POSITION, position);
                    startActivity(intent);
                }
            }
        );
    }

}
