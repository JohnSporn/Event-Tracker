package com.example.eventtrackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username, password;
    private Button login, createAccount;
    boolean EmptyHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        createAccount = findViewById(R.id.register);
        login = findViewById(R.id.login);


        login.setOnClickListener(view -> {
            String message = checkEditTextNotEmpty();

            if(!EmptyHolder) {
                Intent intent = new Intent(MainActivity.this, EventActivity.class);
                startActivity(intent);
            }

        });

        createAccount.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    public String checkEditTextNotEmpty() {
        String message = "";
        String userName = username.getText().toString().trim();
        String passWord = password.getText().toString().trim();

        if (userName.isEmpty()) {
            username.requestFocus();
            EmptyHolder = true;
            message = "Username is Empty";
        } else if(passWord.isEmpty()) {
            password.requestFocus();
            EmptyHolder = true;
            message = "Password is empty";
        } else {
            EmptyHolder = false;
        }
        return message;
    }

    public void emptyEditTextAfterLoginClicked(){
        username.getText().clear();
        password.getText().clear();
    }

}