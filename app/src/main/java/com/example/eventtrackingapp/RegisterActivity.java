package com.example.eventtrackingapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private Button register;
    private EditText full_name, email, phone_number, username, password;
    private boolean EmptyHolder;

    private UserDatabase mydb;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        full_name = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        phone_number = findViewById(R.id.phone_number);
        username = findViewById(R.id.user_name);
        password = findViewById(R.id.pass_word);
        register = findViewById(R.id.create);

        mydb = UserDatabase.getInstance(getApplicationContext());

        // Register button listener
        register.setOnClickListener(view -> {

            User user;
            String message = checkEditTextNotEmpty();

            if(!EmptyHolder) {
                try {
                    user = new User(full_name.getText().toString(), email.getText().toString(),
                            phone_number.getText().toString(), username.getText().toString(),
                            password.getText().toString());
                } catch(Exception e) {
                    Toast.makeText(RegisterActivity.this, "Error creating user", Toast.LENGTH_LONG).show();
                    user = new User(0, "error", "error", "error", "error", "error");
                }

                boolean checkUser = mydb.checkUserExists(user);
                if(checkUser == false) {
                    mydb = new UserDatabase(RegisterActivity.this);
                    mydb.addUser(user);
                    Intent intent = new Intent(RegisterActivity.this, EventActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                }
            } else {
                emptyEditTextAfterRegisterClicked();
                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
            }

        });

    }

    public String checkEditTextNotEmpty() {
        String message = "";
        String fullName = full_name.getText().toString().trim();
        String email_ = email.getText().toString().trim();
        String phoneNumber = phone_number.getText().toString().trim();
        String userName = username.getText().toString().trim();
        String passWord = password.getText().toString().trim();

        if (fullName.isEmpty()) {
            full_name.requestFocus();
            EmptyHolder = true;
            message = "Name is Empty";
        } else if (email_.isEmpty()){
            email.requestFocus();
            EmptyHolder = true;
            message = "Email is Empty";
        } else if (phoneNumber.isEmpty()){
            phone_number.requestFocus();
            EmptyHolder = true;
            message = "Phone Number is Empty";
        } else if (userName.isEmpty()) {
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


    public void emptyEditTextAfterRegisterClicked(){
        full_name.getText().clear();
        email.getText().clear();
        phone_number.getText().clear();
        username.getText().clear();
        password.getText().clear();
    }

}
