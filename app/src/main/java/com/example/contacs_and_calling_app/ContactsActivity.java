/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.contacs_and_calling_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.contacs_and_calling_app.model.Contact;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

/*
 * This class shows the contacts list of each user stored on the database
 */
public class ContactsActivity extends AppCompatActivity {

    // -------------------------------------
    // Database
    // -------------------------------------
    private FirebaseDatabase database;

    // -------------------------------------
    // XML references
    // -------------------------------------
    private EditText nameEditText;
    private EditText phonenumberEditText;
    private Button addButton;

    // -------------------------------------
    // XML references
    // -------------------------------------
    private String currentBrach;

    // -------------------------------------
    // Android methods
    // -------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_contacts);

        nameEditText = findViewById(R.id.nameEditText);
        phonenumberEditText = findViewById(R.id.phonenumberEditText);
        addButton = findViewById(R.id.addButton);

        database = FirebaseDatabase.getInstance();
        currentBrach = getIntent().getExtras().getString("username", "NO_USERNAME");

        addButton.setOnClickListener(

                (view) -> {

                    String id = UUID.randomUUID().toString();
                    String name = nameEditText.getText().toString();
                    String phoneNumber = phonenumberEditText.getText().toString();

                    DatabaseReference reference = database.getReference().child(currentBrach).child(id);

                    Contact contact = new Contact(
                        id,
                        phoneNumber,
                        name
                    );

                    reference.setValue(contact);

                    nameEditText.setText("");
                    phonenumberEditText.setText("");

                }

        );

    }
}