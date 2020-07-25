package com.PseudoReddit.PostManagement;

import com.PseudoReddit.UserManagement.User;

public class Vote extends Interaction {

    private VoteType voteType;

    public Vote(User user, VoteType voteType) {
        super(user);
        this.voteType = voteType;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    //for post

    // upVote
    public static Vote upVote(User user, Post post) {

        Vote like = new Vote(user,VoteType.up);
        if(post.getLink()!=null) {
            int karma = User.searchUserForPost(post).getKarma();
            ++karma;
            User.searchUserForPost(post).setKarma(karma);
        }
        post.addInteraction(like);

        return like;

    }
    // downVote
    public static Vote downVote(User user, Post post) {

        Vote like = new Vote(user,VoteType.down);
        if(post.getLink()!=null) {
            int karma = User.searchUserForPost(post).getKarma();
            --karma;
            User.searchUserForPost(post).setKarma(karma);
        }
        post.addInteraction(like);

        return like;

    }


    // unVote
    public static void unVote(User user, Post post) {

        for(Interaction interaction : post.getInteractions()) {
            if (interaction instanceof Vote) {
                if (interaction.getUser().getUserName().equals(user.getUserName())){
                    if(((Vote) interaction).getVoteType().equals(VoteType.up)){
                        int karma = User.searchUserForPost(post).getKarma();
                        --karma;
                        User.searchUserForPost(post).setKarma(karma);
                    }
                    else{
                        int karma = User.searchUserForPost(post).getKarma();
                        ++karma;
                        User.searchUserForPost(post).setKarma(karma);
                    }
                    post.getInteractions().remove(interaction);
                    return ;
                }
            }
        }

    }

    //for comment

    // upVote
    public static Vote upVoteForComment(User user, Comment comment) {

        Vote like = new Vote(user,VoteType.up);

        comment.addInteraction(like);
        int karma=comment.getUser().getKarma();
        ++karma;
        comment.getUser().setKarma(karma);
        return like;

    }
    // downVote
    public static Vote downVoteForComment(User user, Comment comment) {

        Vote like = new Vote(user,VoteType.down);

        comment.addInteraction(like);
        int karma=comment.getUser().getKarma();
        --karma;
        comment.getUser().setKarma(karma);
        return like;

    }


    // unVote
    public static void unVoteForComment(User user, Comment comment) {

        for(Interaction interaction : comment.getInteractions()) {
            if (interaction instanceof Vote) {
                if (interaction.getUser().getUserName().equals(user.getUserName())){
                    if(((Vote) interaction).getVoteType().equals(VoteType.up)){
                        int karma=comment.getUser().getKarma();
                        --karma;
                        comment.getUser().setKarma(karma);
                    }
                    else{
                        int karma=comment.getUser().getKarma();
                        ++karma;
                        comment.getUser().setKarma(karma);
                    }
                    comment.getInteractions().remove(interaction);
                    return ;
                }
            }
        }

    }
}

