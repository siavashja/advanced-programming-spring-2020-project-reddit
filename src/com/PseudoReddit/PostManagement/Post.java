package com.PseudoReddit.PostManagement;

import com.PseudoReddit.SubManagement.SubReddit;
import com.PseudoReddit.UserManagement.User;
import com.PseudoReddit.UserManagement.UserRelation;

import java.util.ArrayList;
import java.util.Comparator;

public class Post {

    private String title;
    private String text;
    private String link;

    private ArrayList<Interaction> interactions;

    public Post(String title, String text) {
        this.title = title;
        this.text = text;
        this.link=null;
        this.interactions=new ArrayList<>();
    }

    public Post(String title, String text, String link) {
        this.title = title;
        this.text = text;
        this.link = link;
        this.interactions=new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
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

    public ArrayList<Interaction> getInteractions() {
        return interactions;
    }

    public void setInteractions(ArrayList<Interaction> interactions) {
        this.interactions = interactions;
    }

    // Count votes
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


    // Show Post
    public void showPost() {
        System.out.print("r/" + SubReddit.searchForSub(this).getTitle() + "    ");
        System.out.println("u/" + User.searchUserForPost(this).getUserName());
        System.out.println(title+ ": ");
        System.out.println(text);
        if(!(link==null)){
            System.out.println("link: " + link);
        }
        System.out.println(this.countVotes() + " Votes " + this.countComments() + " Comments");
    }

    //add interaction
    public void addInteraction(Interaction interaction) {
        interactions.add(interaction);
    }

    // posts for user

    public static ArrayList<Post> postsForUser(User user) {

        ArrayList<Post> userPosts = new ArrayList<>(user.getPosts());

        for(UserRelation userRelation : user.getUserRelations()) {
            userPosts.addAll(userRelation.getUser().getPosts());
        }


        userPosts.sort(new Comparator<Post>() {
            @Override
            public int compare(Post post1, Post post2) {
                return Integer.compare(post2.countVotes(), post1.countVotes());
            }
        });

        return userPosts;
    }
    //show posts for user

    public static void showPostForUser(User user) {

        ArrayList<Post> userPosts = postsForUser(user);

        if (userPosts.isEmpty()){
            System.out.println("No Posts yet!");
        }

        else {
            int count = 1;
            for(Post post: userPosts) {
                System.out.print(count + ". ");
                post.showPost();
                System.out.println();
                ++count;
            }

        }


    }

    // User Posts
    public static ArrayList<Post> userPosts(User user) {

        ArrayList<Post> userPosts = new ArrayList<>(user.getPosts());

        userPosts.sort(new Comparator<Post>() {
            @Override
            public int compare(Post post1, Post post2) {
                return Integer.compare(post2.countVotes(), post1.countVotes());
            }
        });

        return userPosts;

    }

    // Show User Posts
    public static void showUserPosts(User user) {

        ArrayList<Post> userPosts = userPosts(user);


        if (userPosts.isEmpty()){
            System.out.println("No Posts yet!");
        }

        else {
            int count = 1;
            for(Post post: userPosts){
                System.out.print(count + ". ");
                post.showPost();
                System.out.println();
                ++count;
            }
        }

    }
}
