package com.PseudoReddit.UserManagement;

public class UserRelation {

    private User user;
    private UserRelationType userRelationType;

    public UserRelation(User user, UserRelationType userRelationType) {
        this.user = user;
        this.userRelationType = userRelationType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserRelationType getUserRelationType() {
        return userRelationType;
    }

    public void setUserRelationType(UserRelationType userRelationType) {
        this.userRelationType = userRelationType;
    }
}
