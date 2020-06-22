// BUILD APK under build -> build APK
// forget to attach button to appropriate function
// set real time database to read write to TRUE
package com.example.reddit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;


// currently working
// parent post, delete, upvote, downvote, sorting

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("key1");
    private PostAdapter postAdapter;

    private ArrayList<Message> posts = new ArrayList<Message>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postAdapter = new PostAdapter(this, posts);
        ListView postListView = (ListView) findViewById(R.id.postListView);
        postListView.setAdapter(postAdapter);

        updatePostsOnView();

        Button addPostButton = (Button) findViewById(R.id.addPostButton);
        addPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMessage();
                // myRef.child(keyid).setValue(message);
                // successful dialog optional
            }
        });




    }



    public void addMessage()
    {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("key1").push();
        String keyid = myRef.getKey();
        EditText input_message = findViewById(R.id.inputMessage);
        String message = input_message.getText().toString();
        Message m = new Message(message, 0, keyid);
        myRef.setValue(m);
        //updatePostsOnView();


    }


    public void updatePostsOnView()
    {


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                EditText input_message = findViewById(R.id.inputMessage);
                input_message.setText("");
                Message current_message = dataSnapshot.getValue(Message.class);
                postAdapter.add(current_message);
                Collections.sort(posts, Collections.<Message>reverseOrder());
                postAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                // new list
                ArrayList<Message> newlist = new ArrayList<Message>(posts);
                // clear old one
                postAdapter.clear();
                for (Message m: newlist)
                {
                    String id = dataSnapshot.getValue(Message.class).getId();
                    // if id is not the one deleted, then add back to list
                    if (m.getId() != id)
                    {
                        postAdapter.add(m);
                    }
                }
                Collections.sort(posts, Collections.<Message>reverseOrder());
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
