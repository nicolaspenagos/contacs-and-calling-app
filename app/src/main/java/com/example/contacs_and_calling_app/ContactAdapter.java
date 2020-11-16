package com.example.contacs_and_calling_app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.contacs_and_calling_app.model.Contact;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {

    private ArrayList<Contact> contacts;
    private String currentBranch;

    public void addContact(Contact contact){

        contacts.add(contact);
        notifyDataSetChanged();

    }

    public void clear(){

        contacts.clear();
        notifyDataSetChanged();

    }

    public ContactAdapter(){
        contacts = new ArrayList<Contact>();
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Gives a UI to an arrayList
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row, null);

        Contact contact = contacts.get(position);

        TextView rowName = view.findViewById(R.id.nameRow);
        TextView rowNumber = view.findViewById(R.id.numberRow);
        ImageView rowCall = view.findViewById(R.id.callButton);
        ImageView rowDelete = view.findViewById(R.id.deleteButton);

        rowDelete.setOnClickListener(

                (v)->{

                    String id = contact.getId();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(currentBranch).child(id);
                    ref.setValue(null);

                }

        );

        rowCall.setOnClickListener(

                (v)->{

                   Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+contact.getPhoneNumber()));

                   if(ActivityCompat.checkSelfPermission(parent.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                       Log.e("debug", "Permission denied");

                       return;
                   }

                   parent.getContext().startActivity(intent);

                }

        );

        rowName.setText(contact.getName());
        rowNumber.setText(contact.getPhoneNumber());

        return view;

    }




    public String getCurrentBranch() {
        return currentBranch;
    }

    public void setCurrentBranch(String currentBranch) {
        this.currentBranch = currentBranch;
    }

}
