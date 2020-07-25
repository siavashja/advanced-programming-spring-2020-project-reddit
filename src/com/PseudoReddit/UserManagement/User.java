package com.PseudoReddit.UserManagement;

import com.PseudoReddit.PostManagement.Post;
import com.PseudoReddit.SubManagement.SubReddit;

import java.util.ArrayList;

public class User {

    private String userName;
    private String passWord;
    private String email;
    private int karma;

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    private ArrayList<UserRelation> userRelations;
    private ArrayList<Post> posts;
    private ArrayList<SubReddit> subReddits;

    public ArrayList<SubReddit> getSubReddits() {
        return subReddits;
    }

    public void setSubReddits(ArrayList<SubReddit> subReddits) {
        this.subReddits = subReddits;
    }

    private static ArrayList<User> users = new ArrayList<>();


    public User(String userName, String passWord, String email) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.userRelations= new ArrayList<>();
        this.posts=new ArrayList<>();
        this.subReddits= new ArrayList<>();
        this.karma=0;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<UserRelation> getUserRelations() {
        return userRelations;
    }

    public void setUserRelations(ArrayList<UserRelation> userRelations) {
        this.userRelations = userRelations;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    //signup

    public static User signup(String username,String password,String email){
        for(User user:users){
            if(!(user.getUserName().equals(username) && user.getEmail().equals(email))){
                User newuser= new User(username, password, email);
                users.add(newuser);
                return newuser;
            }
        }
        return null;
    }

    //login

    public static User login(String username,String password){
        for(User user:users){
            if(!(user.getUserName().equals(username) && user.getPassWord().equals(password))){
                return user;
            }
        }
        return null;
    }

    //search

    public static User searchUser(String userName){
        for(User user:users){
            if(user.getUserName().equals(userName)){
                return user;
            }
        }
        return null;
    }

    //follow

    public void follow(String username){
        User user = User.searchUser(username);

        if(user==null){
            //exception
        }
        else{
            UserRelation userRelation=new UserRelation(user,UserRelationType.follow);
            userRelations.add(userRelation);
        }
    }

    //unfollow

    public void unfollow(String username){
        for(UserRelation userRelation : userRelations) {
            if (userRelation.getUserRelationType().equals(UserRelationType.follow) && userRelation.getUser().getUserName().equals(username)){
                userRelations.remove(userRelation);
                return ;
            }
        }
    }

    //block

    public void block(String username){
        User user = User.searchUser(username);

        if(user==null){
            //exception
        }
        else{
            unfollow(username);
            user.unfollow(this.getUserName());
            UserRelation userRelation=new UserRelation(user,UserRelationType.block);
            userRelations.add(userRelation);
        }
    }

    //unblock

    public void unblock(String username){
        for(UserRelation userRelation : userRelations) {
            if (userRelation.getUserRelationType().equals(UserRelationType.block) && userRelation.getUser().getUserName().equals(username)){
                userRelations.remove(userRelation);
                return ;
            }
        }
    }

    //hasfollowed

    public boolean hasFollowed(User user) {

        for(UserRelation userRelation : userRelations){
            if (userRelation.getUserRelationType().equals(UserRelationType.follow) && userRelation.getUser().getUserName().equals(user.getUserName())){
                return true;
            }
        }

        return false;
    }

    //show followers
    public void showFollowers(){
        int count=1;
        for(UserRelation userRelation: userRelations){
            if(userRelation.getUserRelationType().equals(UserRelationType.follow)){
                System.out.println(count + ". " + userRelation.getUser().getUserName());
                ++count;
            }
        }
    }

    //show followings
    public void showFollowings(){
        int count=1;
        for(User user: users){
            if(user.hasFollowed(this)){
                System.out.println(count + ". " + user.getUserName());
                ++count;
            }
        }
    }

    //hasblocked

    public boolean hasBlocked(User user) {

        for(UserRelation userRelation : userRelations){
            if (userRelation.getUserRelationType().equals(UserRelationType.block) && userRelation.getUser().getUserName().equals(user.getUserName())){
                return true;
            }
        }

        return false;
    }

    //show blocks

    public void showBlocks(){
        int count=1;
        for(UserRelation userRelation: userRelations){
            if(userRelation.getUserRelationType().equals(UserRelationType.block)){
                System.out.println(count + ". " + userRelation.getUser().getUserName());
                ++count;
            }
        }
    }


    //simple post
    public void createSimplepost(String title,String text,String sub){
        for(SubReddit subReddit: SubReddit.getSubreddits()){
            if(subReddit.getTitle().equals(sub)){
                Post post= new Post(title,text);
                posts.add(post);
                subReddit.getPosts().add(post);
            }
        }
    }

    //link post
    public void createLinkepost(String title,String text,String link,String sub){
        for(SubReddit subReddit: SubReddit.getSubreddits()){
            if(subReddit.getTitle().equals(sub)){
                Post post= new Post(title,text,link);
                posts.add(post);
                subReddit.getPosts().add(post);
            }
        }
    }

    //search for post

    public boolean searchForPost(String title){
        for(Post post:getPosts()){
            if(post.getTitle().equals(title)){
                return true;
            }
        }
        return false;
    }

    //search user for post

    public static User searchUserForPost(Post post){

        for(User user: users){
            if(user.searchForPost(post.getTitle())){
                return user;
            }
        }
        return null;
    }

    // create subreddit

    public SubReddit createSubReddit(String title){
        SubReddit subReddit = new SubReddit(title);
        if(subReddit.searchSub(subReddit.getTitle())){
            return null;
        }
        return subReddit;
    }

    public void showSubs(){
        int count=1;

        for(SubReddit subReddit:getSubReddits()){
            System.out.print(count);
            subReddit.showSubReddit();
            ++count;
        }
    }

    public void showKarma(){
        System.out.println(karma);
    }

}
