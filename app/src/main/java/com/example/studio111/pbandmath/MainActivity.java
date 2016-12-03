package com.example.studio111.pbandmath;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static android.R.attr.value;

public class MainActivity extends AppCompatActivity {

    private Button getStemButton;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mStemDatabaseReference;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    private ListView mStemListView;
    private StemAdapter mStemAdapter;
    private TextView mStemTextView;
    private List<Stem> stemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getStemButton = (Button) findViewById(R.id.getStemButton);
        //Getting access to the database
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        //referencing the specific part of the database  //.child("Stems")
        mStemDatabaseReference = mFirebaseDatabase.getReference().child("Stems");

        // Initialize message ListView and its adapter
        stemList = new ArrayList<>();
        mStemAdapter = new StemAdapter(this, R.layout.stem, stemList);

        mStemDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Stem stem = child.getValue(Stem.class);
                    mStemAdapter.add(stem);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}

