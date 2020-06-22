package com.example.reddit;

public class ChildPost {

    private String comment;

    public ChildPost()
    {
        comment = "";
    }

    public ChildPost(String comment)
    {
        this.comment = comment;
    }

    public String getComment() {return comment; }

    public void setComment(String c) { comment = c; }

}
