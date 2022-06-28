package com.example.eventtrackingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEventActivity extends AppCompatActivity {

    private EditText eventName, eventDate, eventInfo;
    private Button add_button;

    private EventDatabase event_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        eventName = findViewById(R.id.eventName);
        eventDate = findViewById(R.id.eventDate);
        eventInfo = findViewById(R.id.eventInfo);
        add_button = findViewById(R.id.add_button);

        event_db = EventDatabase.getInstance(getApplicationContext());

        add_button.setOnClickListener(view -> {

            Event event;

            try {
                event = new Event(eventName.getText().toString(), eventDate.getText().toString(), eventInfo.getText().toString());
                Toast.makeText(AddEventActivity.this, event.toString(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(AddEventActivity.this, "Error creating event", Toast.LENGTH_SHORT).show();
                event = new Event(0, "error", "error", "error");
            }

            EventDatabase db = new EventDatabase(AddEventActivity.this);
            boolean success = db.addEvent(event);
            Toast.makeText(AddEventActivity.this, "Success" + success, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(AddEventActivity.this, EventActivity.class);
            startActivity(intent);

        });

    }
}
