package com.example.reddit;

import java.util.ArrayList;
public class Message implements Comparable<Message>{

    private String message;
    private int votes;
    private String id;
    private ArrayList<Message> m;

    public Message()
    {
        message = "";
        votes = 0;
        id = "";
        m = new ArrayList<Message>();

    }

    public Message(String message)
    {
        this.message = message;
        this.votes = 0;
        id = "";
        m = new ArrayList<Message>();
    }

    public Message(String message, int votes)
    {
        this.message = message;
        this.votes = votes;
        id = "";
        m = new ArrayList<Message>();

    }

    public Message(String message, int votes, String id)
    {
        this.message = message;
        this.votes = votes;
        this.id = id;
        m = new ArrayList<Message>();
    }

    public String getId() { return id; }

    public String getMessage() {
        return message;
    }

    public int getVotes() { return votes; }

    public ArrayList<Message> getM() { return m; }

    public void setMessage(String m)
    {
        message = m;
    }

    public void setVotes(int i)
    {
        votes = i;
    }

    public void setM(ArrayList<Message> m)
    {
        this.m = m;
    }

    public void setId(String id)
    {
        this.id = id;
    }
    public String toString()
    {
        return ("message: " + message );
    }

    @Override
    public int compareTo(Message m)
    {
        int comparevotes = ((Message)m).getVotes();
        return this.votes-comparevotes;
    }
}
