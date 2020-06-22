package com.example.reddit;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostAdapter extends ArrayAdapter<Message>{
    private Context mContext;
    private List<Message> posts;
    private ChildEventListener childEventListener;
    private DatabaseReference myRef;


    public PostAdapter(Context context, ArrayList<Message> posts)
    {
        super( context, 0, posts);
        mContext = context;
        this.posts = posts;
        myRef = FirebaseDatabase.getInstance().getReference("key1");
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {

        View listItem = convertView;

        final int posit = position;
        // Associates the list with the XML Layout file "contact_view"
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.post_adapter, parent,false);

        // Individually handles each Contact in the contactList
        final Message currentMessage = posts.get(position);


        // Extracts the name of the Contact
        // need user to type stuff


        TextView message = (TextView) listItem.findViewById(R.id.parentTextView);
        message.setText(currentMessage.getMessage());

        // Extracts the phone number of the Contact
        TextView id = (TextView) listItem.findViewById(R.id.idTextView);
        id.setText(currentMessage.getId());

        TextView votes = (TextView) listItem.findViewById(R.id.voteCount);
        votes.setText(Integer.toString(currentMessage.getVotes()));

        final View li = listItem;
        ImageButton upVoteButton = (ImageButton) listItem.findViewById(R.id.upVoteButton);
        upVoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentMessage.setVotes(currentMessage.getVotes() + 1);
                TextView votes = (TextView) li.findViewById(R.id.voteCount);
                votes.setText(Integer.toString(currentMessage.getVotes()));
                myRef.child(posts.get(posit).getId()).child("votes").setValue(currentMessage.getVotes());
                Collections.sort(posts, Collections.<Message>reverseOrder());
                notifyDataSetChanged();

            }
        });

        ImageButton downVoteButton = (ImageButton) listItem.findViewById(R.id.downVoteButton);
        downVoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMessage.setVotes(currentMessage.getVotes() - 1);
                TextView votes = (TextView) li.findViewById(R.id.voteCount);
                votes.setText(Integer.toString(currentMessage.getVotes()));
                myRef.child(posts.get(posit).getId()).child("votes").setValue(currentMessage.getVotes());
                Collections.sort(posts, Collections.<Message>reverseOrder());
                notifyDataSetChanged();
            }
        });

        Button deleteButton = (Button) listItem.findViewById(R.id.ReportButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(posts.get(posit).getId()).removeValue();
                posts.remove(posts.get(posit));
            }
        });

        Button replyButton = (Button) listItem.findViewById(R.id.replyButton);
        replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View listItem = li;
                //myRef.child(posts.get(position).getId()).getValue();
                String keyid = myRef.getKey();
                EditText input_message = (EditText) listItem.findViewById(R.id.ReplyEditText);
                String message = input_message.getText().toString();
                Message m = new Message(message, 0, keyid);
                myRef.child(posts.get(posit).getId()).child("m").setValue(m);
            }
        });

        return listItem;
    }

//        public void addChild(int position)
//        {
//            View listItem = convertView;
//            //myRef.child(posts.get(position).getId()).getValue();
//            String keyid = myRef.getKey();
//            EditText input_message = (EditText) listItem.findViewById(R.id.ReplyTextBox);
//            String message = input_message.getText().toString();
//            Message m = new Message(message, 0, keyid);
//            myRef.child(posts.get(position).getId()).setValue(m);
//
//        }



}
