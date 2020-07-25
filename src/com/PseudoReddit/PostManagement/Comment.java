package com.PseudoReddit.PostManagement;

import com.PseudoReddit.UserManagement.User;

import java.util.ArrayList;

public class Comment extends Interaction{

    private String  text;
    private String link;

    private ArrayList<Interaction> interactions;

    public Comment(User user, String text) {
        super(user);
        this.text = text;
        this.interactions=new ArrayList<>();
    }

    public Comment(User user, String text, String link) {
        super(user);
        this.text = text;
        this.link = link;
        this.interactions=new ArrayList<>();
    }

    public String getText() {
        return text;
    }

    public ArrayList<Interaction> getInteractions() {
        return interactions;
    }

    public void setInteractions(ArrayList<Interaction> interactions) {
        this.interactions = interactions;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    //add interaction
    public void addInteraction(Interaction interaction) {
        interactions.add(interaction);
    }
    // Comment for post
    public static Comment commentForPost(User user, Post post, String text) {

        Comment comment = new Comment(user, text);

        post.addInteraction(comment);

        return comment;
    }

    //count votes
    public int countVotes() {

        int count = 0;
        for(Interaction interaction : interactions) {
            if (interaction instanceof Vote) {
                if(((Vote) interaction).getVoteType().equals(VoteType.up)) {
                    ++count;
                }
                else if(((Vote) interaction).getVoteType().equals(VoteType.down)){
                    --count;
                }
            }
        }

        return count;

    }


    // Count Comments
    public int countComments() {

        int count = 0;
        for(Interaction interaction: interactions){
            if (interaction instanceof Comment) {
                ++count;
            }
        }

        return count;
    }

    // Comment for comment
    public static Comment commentForComment(User user, Comment comment, String text) {

        Comment newComment = new Comment(user, text);
        comment.addInteraction(newComment);

        return comment;
    }

    // Show Comment
    public void showComment() {
        System.out.println("u/ " +getUser().getUserName());
        System.out.println(text);
        if(!(link==null)){
            System.out.println("link: " + link);
        }
        System.out.println(this.countVotes() + " Votes " + this.countComments() + " Comments");
    }
}
