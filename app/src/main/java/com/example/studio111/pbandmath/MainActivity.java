package com.example.studio111.pbandmath;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.value;

public class MainActivity extends AppCompatActivity {


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mStemDatabaseReference;
   // private ChildEventListener mChildEventListener;

    private ListView mStemListView;
    private StemAdapter mStemAdapter;
    private TextView mStemTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting access to the database
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        //initialize auth object
       // mFirebaseAuth = FirebaseAuth.getInstance();

        //referencing the specific part of the database
        mStemDatabaseReference = mFirebaseDatabase.getReference().child("Stems");

        // Initialize message ListView and its adapter
        List<Stem> stemList = new ArrayList<>();
        mStemAdapter = new StemAdapter(this, R.layout.stem, stemList);
        mStemListView.setAdapter(mStemAdapter);

// Write a message to the database
       // FirebaseDatabase database = FirebaseDatabase.getInstance();
       // DatabaseReference myRef = database.getReference("message");

       // myRef.setValue("Hello, World!");

        // Read from the database
        mStemDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                Stem stem = dataSnapshot.getValue(Stem.class);
                mStemAdapter.add(stem);

                Log.d("Get data", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Get Data", "Failed to read value.", error.toException());
            }
        });
    }


}
