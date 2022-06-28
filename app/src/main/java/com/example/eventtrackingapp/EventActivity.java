package com.example.eventtrackingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class EventActivity extends AppCompatActivity {

    private Button removeButton;
    private FloatingActionButton addButton;
    ListView listView;

    ArrayAdapter eventArrayAdapter;
    EventDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        addButton = findViewById(R.id.floatingActionButton);
        listView = findViewById(R.id.listView);
        db = new EventDatabase(EventActivity.this);

        ShowAllEventsOnListView(db);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event clickedEvent = (Event) parent.getItemAtPosition(position);
                db.deleteOne(clickedEvent);
                ShowAllEventsOnListView(db);
            }
        });

        addButton.setOnClickListener(view -> {
            Intent intent = new Intent(EventActivity.this, AddEventActivity.class);
            startActivity(intent);
        });

    }

    public void ShowAllEventsOnListView(EventDatabase db_2) {
        eventArrayAdapter = new ArrayAdapter<Event>(EventActivity.this, android.R.layout.simple_list_item_1, db.getAllEvents());
        listView.setAdapter(eventArrayAdapter);
    }

}
