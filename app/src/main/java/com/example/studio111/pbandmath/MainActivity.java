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

        //referencing the specific part of the database
        mStemDatabaseReference = mFirebaseDatabase.getReference().child("stems");

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
        // Initialize progress bar
      //  mProgressBar.setVisibility(ProgressBar.INVISIBLE);

        // ImagePickerButton shows an image picker to upload a image for a message
//        mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Fire an intent to show an image picker
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/jpeg");
//                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
//                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
//            }
//        });

        // Enable Send button when there's text to send
//        mMessageEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (charSequence.toString().trim().length() > 0) {
//                    mSendButton.setEnabled(true);
//                } else {
//                    mSendButton.setEnabled(false);
//                }
//            }

//            @Override
//            public void afterTextChanged(Editable editable) {
//            }
//        });
//        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
//
//        // Send button sends a message and clears the EditText
//        mSendButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Creating a FriendlyMessage object
//                FriendlyMessage friendlyMessage = new FriendlyMessage(mMessageEditText.getText().toString(), mUsername, null);
//
//                //push message to database
//                mMessagesDatabaseReference.push().setValue(friendlyMessage);
//
//                // Clear input box
//                mMessageEditText.setText("");
//            }
//        });

//        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                //check if user is signed in
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    //user is signed in
//                    onSignedInInitialize(user.getDisplayName());
//                } else {
//                    //user is signed out
//                    onSignedOutCleanup();
//                    startActivityForResult(
//                            AuthUI.getInstance()
//                                    .createSignInIntentBuilder()
//                                    .setIsSmartLockEnabled(false)
//                                    .setProviders(
//                                            AuthUI.EMAIL_PROVIDER,
//                                            AuthUI.GOOGLE_PROVIDER)
//                                    .build(),
//                            RC_SIGN_IN);
//                }
//            }
//        };
        //Set up remote config
//        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(BuildConfig.DEBUG).build();
//        mFirebaseRemoteConfig.setConfigSettings(configSettings);
//
//        Map<String, Object> defaultConfigMap = new HashMap<>();
//        defaultConfigMap.put(FRIENDLY_MSG_LENGTH_KEY, DEFAULT_MSG_LENGTH_LIMIT);
//        mFirebaseRemoteConfig.setDefaults(defaultConfigMap);
//        fetchConfig();

    }

//    private void fetchConfig() {
//        long cacheExpiration = 3600;
//
//        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()){
//            cacheExpiration = 0;
//        }
//        mFirebaseRemoteConfig.fetch(cacheExpiration).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                mFirebaseRemoteConfig.activateFetched();
//                applyRetrievedLengthLimit();
//            }
//        })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error fetching config", e);
//                        applyRetrievedLengthLimit();
//                    }
//                });
//    }

//    private void applyRetrievedLengthLimit(){
//        Long friendly_msg_length = mFirebaseRemoteConfig.getLong(FRIENDLY_MSG_LENGTH_KEY);
//        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(friendly_msg_length.intValue())});
//        Log.d(TAG, FRIENDLY_MSG_LENGTH_KEY + " = " + friendly_msg_length);
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.sign_out_menu:
//                //sign out
//                AuthUI.getInstance().signOut(this);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RC_SIGN_IN) {
//            if (resultCode == RESULT_OK) {
//                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
//            } else if (resultCode == RESULT_CANCELED) {
//                Toast.makeText(this, "Signed in canceled", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        } else if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
//            Uri selectedImageUri = data.getData();
//
//            //Get a reference to store file at chat_photos/<FILENAME>
//            StorageReference photoRef = mChatPhotosStorageReference.child(selectedImageUri.getLastPathSegment());
//
//            //upload file to Firebase storage
//            photoRef.putFile(selectedImageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                    FriendlyMessage friendlyMessage = new FriendlyMessage(null, mUsername, downloadUrl.toString());
//                    mMessagesDatabaseReference.push().setValue(friendlyMessage);
//                }
//            });
//        }
    //}

//    @Override
//    protected void onResume() {
//        super.onResume();
//        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (mAuthStateListener != null) {
//            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
//        }
//        detachDatabaseReadListener();
//        mMessageAdapter.clear();
//    }
//
//    private void onSignedInInitialize(String username) {
//        mUsername = username;
//        attachDatabaseReadListener();
//    }

//    private void onSignedOutCleanup() {
//        mUsername = ANONYMOUS;
//        mMessageAdapter.clear();
//        detachDatabaseReadListener();
//    }

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
