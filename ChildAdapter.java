package com.example.reddit;


//public class ChildAdapter extends ArrayAdapter<ChildPost>{
//    private Context mContext;
//    private List<ChildPost> posts;
//
//
//
//    public ChildAdapter(Context context, ArrayList<ChildPost> posts)
//    {
//        mContext = context;
//        this.posts = posts;
//
//    }
//
////    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
////    {
////
////        View listItem = convertView;
////        final int posit = position;
////        // Associates the list with the XML Layout file "contact_view"
////        if(listItem == null)
////            listItem = LayoutInflater.from(mContext).inflate(R.layout.child_comment, parent,false);
////
////        // Individually handles each Contact in the contactList
////        Message currentMessage = posts.get(position);
////
////        // Extracts the name of the Contact
////        // need user to type stuff
////
////
////        final TextView message = (TextView) listItem.findViewById(R.id.childComment);
////        message.setText(currentMessage.getMessage());
////
////        // Extracts the phone number of the Contact
////        TextView id = (TextView) listItem.findViewById(R.id.idTextView);
////        id.setText(currentMessage.getId());
////
////        TextView votes = (TextView) listItem.findViewById(R.id.voteCount);
////        votes.setText(Integer.toString(currentMessage.getVotes()));
////
////        Button reportButton = (Button) listItem.findViewById(R.id.ReportButton);
////        reportButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                myRef.child(posts.get(posit).getId()).removeValue();
////                posts.remove(posts.get(posit));
////            }
////        });
////
////
////        return listItem;
////    }
//
////    public void addChild(int position)
////    {
////        View listItem = convertView;
////        //myRef.child(posts.get(position).getId()).getValue();
////        String keyid = myRef.getKey();
////        EditText input_message = (EditText) listItem.findViewById(R.id.ReplyTextBox);
////        String message = input_message.getText().toString();
////        Message m = new Message(message, 0, keyid);
////        myRef.child(posts.get(position).getId()).setValue(m);
////
////    }
//}
//
