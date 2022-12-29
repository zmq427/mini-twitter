package com.mq.pojo;

public class UserWithStatus extends User{
    private Integer followed;

    public Integer getFollowed() {
        return followed;
    }

    public void setFollowed(Integer followed) {
        this.followed = followed;
    }

    @Override
    public String toString() {
        return "UserWithStatus{" +
                "followed=" + followed +
                "} " + super.toString();
    }
}
