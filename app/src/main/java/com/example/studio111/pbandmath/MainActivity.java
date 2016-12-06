package com.example.studio111.pbandmath;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.action;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final String FRIENDLY_MSG_LENGTH_KEY = "friendly_msg_length";


    //add sign in
    public static final int RC_SIGN_IN = 1;
    private static final int RC_PHOTO_PICKER = 2;

    private ListView mStemListView;
    private StemAdapter mStemAdapter;
    private ProgressBar mProgressBar;
    private ImageButton mPhotoPickerButton;
    private EditText mMessageEditText;
    private Button mSendButton;

    private String mUsername;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mStemDatabaseReference;
    private ChildEventListener mChildEventListener;
//    private FirebaseAuth mFirebaseAuth;
//    private FirebaseAuth.AuthStateListener mAuthStateListener;
//    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    //storage
//    private FirebaseStorage mFirebaseStorage;
//    private StorageReference mChatPhotosStorageReference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = ANONYMOUS;


        //Getting access to the database
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        //initialize auth object
        // mFirebaseAuth = FirebaseAuth.getInstance();

        //referencing the specific part of the database .child("stems")
        mStemDatabaseReference = mFirebaseDatabase.getReference();

        //initialize Firebase storage
        //  mFirebaseStorage = FirebaseStorage.getInstance();

        //referencing the specific folder in storage
        // mChatPhotosStorageReference = mFirebaseStorage.getReference().child("chat_photos");

        //Initialize remote config
        // mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        // Initialize references to views
        //  mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mStemListView = (ListView) findViewById(R.id.messageListView);
//        mPhotoPickerButton = (ImageButton) findViewById(R.id.photoPickerButton);
//        mMessageEditText = (EditText) findViewById(R.id.messageEditText);
//        mSendButton = (Button) findViewById(R.id.sendButton);

        // Initialize message ListView and its adapter
        List<Stem> friendlyMessages = new ArrayList<>();
        mStemAdapter = new StemAdapter(this, R.layout.stem, friendlyMessages);
        mStemListView.setAdapter(mStemAdapter);
        if (mChildEventListener == null) {
            //set up database listener
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    //gets just the new message added and desserializes it based on
                    //the FriendlyMessage class
                    Stem stem = dataSnapshot.getValue(Stem.class);
                    mStemAdapter.add(stem);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            mStemDatabaseReference.addChildEventListener(mChildEventListener);
        }
        attachDatabaseReadListener();
    }

    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            //set up database listener
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    //gets just the new message added and desserializes it based on
                    //the FriendlyMessage class
                    Stem stem = dataSnapshot.getValue(Stem.class);
                    mStemAdapter.add(stem);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            mStemDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    private void detachDatabaseReadListener() {
        if (mChildEventListener != null) {
            mStemDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }
}
