/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.contacs_and_calling_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

/*
* This class controls the screen where the user enters their username at first.
 */
public class MainActivity extends AppCompatActivity {

    // -------------------------------------
    // Database
    // -------------------------------------
    private FirebaseDatabase database;

    // -------------------------------------
    // XML references
    // -------------------------------------
    private EditText usernameEditText;
    private Button nextButton;

    // -------------------------------------
    // Android methods
    // -------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(

                (view)->{

                    String username = usernameEditText.getText().toString();
                    if(checkUsername(username)){

                        Intent intent = new Intent(this, ContactsActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);

                    }

                }

        );

        database = FirebaseDatabase.getInstance();

    }

    @Override
    protected void onResume() {
        super.onResume();
        usernameEditText.setText("");
    }

    // -------------------------------------
    // Methods
    // -------------------------------------
    public boolean checkUsername(String username){
        return ( username == null || username.equals("")) ? false : true;
    }

}