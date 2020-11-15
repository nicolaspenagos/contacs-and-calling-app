/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.contacs_and_calling_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.contacs_and_calling_app.model.Contact;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

/*
 * This class shows the contacts list of each user stored on the database
 */
public class ContactsActivity extends AppCompatActivity {

    // -------------------------------------
    // Global Assets
    // -------------------------------------
    private FirebaseDatabase database;
    private ContactAdapter adapter;
  //  private ArrayList<Contact> contacts;
  //  private ArrayAdapter<Contact> adapter;

    // -------------------------------------
    // XML references
    // -------------------------------------
    private EditText nameEditText;
    private EditText phonenumberEditText;
    private Button addButton;
    private ListView contactsListView;
    private Button goBackButton;

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
        contactsListView = findViewById(R.id.contactsListView);
        goBackButton = findViewById(R.id.goBackButton);

        database = FirebaseDatabase.getInstance();
        currentBrach = getIntent().getExtras().getString("username", "NO_USERNAME");

        adapter = new ContactAdapter();
        adapter.setCurrentBranch(currentBrach);
        contactsListView.setAdapter(adapter);

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

        goBackButton.setOnClickListener(

                (view)->{

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }

        );

        loadDatabase();

    }


    // -------------------------------------
    // Methods
    // -------------------------------------
    public void loadDatabase(){

        DatabaseReference ref = database.getReference().child(currentBrach);

        ref.addValueEventListener(

                new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot data) {

                        adapter.clear();
                        for(DataSnapshot child: data.getChildren()){

                            Contact contact = child.getValue(Contact.class);
                            adapter.addContact(contact);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                }

        );

    }


}