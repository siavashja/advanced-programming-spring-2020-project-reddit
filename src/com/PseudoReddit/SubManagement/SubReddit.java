package com.PseudoReddit.SubManagement;

import com.PseudoReddit.PostManagement.Post;

import java.util.ArrayList;

public class SubReddit {
    private String title;

    private ArrayList<Post> posts;

    private static ArrayList<SubReddit> subreddits= new ArrayList<>();

    public static ArrayList<SubReddit> getSubreddits() {
        return subreddits;
    }

    public SubReddit(String title) {
        this.title = title;
        this.posts=new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public boolean searchForPost(String title){
        for(Post post:getPosts()){
            if(post.getTitle().equals(title)){
                return true;
            }
        }
        return false;
    }

    public static SubReddit searchForSub(Post post){
        for(SubReddit subReddit: subreddits){
            if(subReddit.searchForPost(post.getTitle())){
                return subReddit;
            }
        }
        return null;
    }

    public boolean searchSub(String title){
        for(SubReddit subReddit: subreddits){
            if(subReddit.searchForPost(title)){
                return true;
            }
        }
        return false;
    }

    public void showSubReddit(){
        System.out.println(title);
    }
}
